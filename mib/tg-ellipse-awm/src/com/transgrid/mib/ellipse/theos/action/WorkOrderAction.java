package com.transgrid.mib.ellipse.theos.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.soa.esb.actions.ActionProcessingFaultException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import com.mincom.enterpriseservice.ellipse.ErrorMessageDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrder;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.ArrayOfWorkOrderTaskServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.ArrayOfWorkOrderTaskServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.EnterpriseServiceOperationException;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTask;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceReadReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceReadRequestDTO;
import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;
import com.mincom.mib.ellipse.EllipseBaseAction;

/**
 * Custom work order / work order task action for TransGrid.  
 */
public class WorkOrderAction extends EllipseBaseAction {

    //private static final String SI_TABLE_TYPE = "SI";
    private static final String MAINT_ORDER_HEADER_MANDATORY_ERROR = "ERROR: MaintenanceOrderHeader is mandatory.";
    private static final String MAINT_ORDER_HEADER_DOC_ID_MANDATORY_ERROR = "ERROR: MaintenanceOrderHeader DocumentID is mandatory.";
    private static final String PLANNED_TIME_PERIOD_ERROR = "ERROR: PlannedTimePeriod StartDateTime must be earlier than EndDateTime.";
    private static final String MAINT_ORDER_LINE_OP_ID_MANDATORY_ERROR = "ERROR: MaintenanceOrderLine OperationID is mandatory.";
    //private static final String E10004_ERROR_CODE  = "core.E10004"; 
    //private ConfigTree configuration;
    private String workOrderTasksBeanName;

    public WorkOrderAction(ConfigTree config) {
        super(config);
        //configuration = config;
        workOrderTasksBeanName = config.getAttribute(RESULT_BEAN_ID, "workOrderTasks");
    }

    /**
     * This method does all the work order / work order task validation and processing required for the TransGrid
     * SyncMaintenanceOrder (ONLY modify) message. Since this just supports the modify case, the given work order and 
     * work order task is assumed to already exist. This implementation does validate though that the work order and 
     * work order task numbers aren't empty. An error will be sent back if they are empty, we won't do any create
     * processing. 
     */
    public Message process(Message message) throws ActionProcessingFaultException {
    	logger.info("WorkOrderAction.process()...");
        EWSClientConversation conversation = getConversation(message);
        OperationContext operationContext = getOperationContext(message);

        try {
            
            @SuppressWarnings("unchecked")
            List<WorkOrderTaskServiceModifyRequestDTO> workOrderTaskServiceModifyRequestDTOList = (List<WorkOrderTaskServiceModifyRequestDTO>) getDTOObject(message, workOrderTasksBeanName);

            validate(conversation, operationContext, workOrderTaskServiceModifyRequestDTOList);
            
            modifyTasks(message, conversation, operationContext, workOrderTaskServiceModifyRequestDTOList);           
            
           
        } catch (Exception ex) {
        	logger.error(ex.getMessage(), ex);
            logger.error("TransGrid WorkOrderAction::process(Message) - Exception: " + ex.getMessage());
            message = storeExceptionInResponse(message, "Update WorkOrder Task", ex);
        }

        return message;
    }

    /*
     * Modifies the work order tasks (and possibly the work order header) only if they have actually been
     * modified (see the THEOS DDD document). 
     */
    private void modifyTasks(Message message, EWSClientConversation conversation, OperationContext operationContext,
    		
        List<WorkOrderTaskServiceModifyRequestDTO> workOrderTaskServiceModifyRequestDTOList) throws Exception {
        WorkOrderTask workOrderTaskService = createService(conversation, WorkOrderTask.class);
        
        // do a read on the tasks so that we can compare them with the tasks from TransGrid
        ArrayOfWorkOrderTaskServiceReadReplyDTO arrayOfWorkOrderTaskServiceReadReplyDTO = 
            readWorkOrderTasks(conversation, operationContext, workOrderTaskService, workOrderTaskServiceModifyRequestDTOList);
        
        List<WorkOrderTaskServiceReadReplyDTO> workOrderTaskServiceReadReplyDTOList = 
            arrayOfWorkOrderTaskServiceReadReplyDTO.getWorkOrderTaskServiceReadReplyDTO();
        
        WorkOrder workOrderService = createService(conversation, WorkOrder.class);
        
        for (int i = 0; i < workOrderTaskServiceModifyRequestDTOList.size(); ++i) {
            WorkOrderTaskServiceModifyRequestDTO woTaskModifyRequestDTO = workOrderTaskServiceModifyRequestDTOList.get(i);
            WorkOrderTaskServiceReadReplyDTO woTaskReplyDTO = workOrderTaskServiceReadReplyDTOList.get(i);
            
            // modify only if they're different
            if (isModified(woTaskModifyRequestDTO, woTaskReplyDTO)) {
                doModify(workOrderService, workOrderTaskService, operationContext, woTaskModifyRequestDTO, woTaskReplyDTO);
                addResultToResponse(message, "OK", "Update workorder task details", "Updated workorder task details for WO number : " + woTaskModifyRequestDTO.getWorkOrder().getNo());
            } else {
            	addResultToResponse(message, "WARN", "", "WorkOrder task was not updated since the request & existing Start/Finish datetime are equal for WO number : " + woTaskModifyRequestDTO.getWorkOrder().getNo());
            }
        }
    }

    /*
     * Modifies the work order (optional, depending on whether the dates on the work order need to be modified)
     * and work order task (required, at this point we've already determined that the task needs to be modified). 
     */
    private void doModify(WorkOrder workOrderService, WorkOrderTask workOrderTaskService,
        OperationContext operationContext, WorkOrderTaskServiceModifyRequestDTO woTaskModifyRequestDTO,
        WorkOrderTaskServiceReadReplyDTO woTaskReplyDTO) throws Exception {
        boolean isModifyWoMustStartInd = false;
        boolean isModifyWoTaskMustStartInd = false;
        String originalWoMustStartInd = null;
        WorkOrderServiceReadReplyDTO woReplyDTO = null;
        ErrorMessageDTO errorMessageDTO = null;
          
        try {
             
        	ClonedWOTaskRequest clonedWOTaskRequest = (ClonedWOTaskRequest) new ClonedWOTaskRequest(woTaskModifyRequestDTO).clone();
            // if the work order task planned dates aren't empty then we may need to modify the work order
            if (woTaskModifyRequestDTO.getPlanStrDate() != null) {
                // read the work order so we can compare the planned dates to determine if a modify on it is needed 
                WorkOrderServiceReadRequestDTO woRequestDTO = new WorkOrderServiceReadRequestDTO();
                woRequestDTO.setDistrictCode(woTaskModifyRequestDTO.getDistrictCode());
                woRequestDTO.setIncludeTasks(false);
                WorkOrderDTO woDTO = new WorkOrderDTO();
                woDTO.setNo(woTaskModifyRequestDTO.getWorkOrder().getNo());
                woDTO.setPrefix(woTaskModifyRequestDTO.getWorkOrder().getPrefix());
                woRequestDTO.setWorkOrder(woDTO);
                woReplyDTO = workOrderService.read(operationContext, woRequestDTO);
                logger.debug("Work order id {} for update task deatils.. ",woDTO.getNo() );
                logger.debug("Current task details -  Safety instr {} ",woTaskModifyRequestDTO.getSafetyInstr());
                boolean isModifyWo = false;
                
                originalWoMustStartInd = woReplyDTO.getMustStartInd();
                WorkOrderServiceModifyRequestDTO woModifyRequestDTO = new WorkOrderServiceModifyRequestDTO();
                logger.debug("Current work order details - Planned Start DateTime {} Planned Finish DateTime {} ",woReplyDTO.getPlanStrDate()+":"+woReplyDTO.getPlanStrTime(), woReplyDTO.getPlanFinDate()+":"+woReplyDTO.getPlanFinTime());
                logger.debug("Existing WorkOrder MustStart Indicator is {} & WorkOrderTask MustStart Indicator is {} ",originalWoMustStartInd, woTaskReplyDTO.getMustStartInd());
                
                String replyPlanStartDate = woReplyDTO.getPlanStrDate() + woReplyDTO.getPlanStrTime();
                String reqPlanStartDate = woTaskModifyRequestDTO.getPlanStrDate() + woTaskModifyRequestDTO.getPlanStrTime();
                
                if (StringUtils.isEmpty(woReplyDTO.getPlanStrDate()) || StringUtils.isEmpty(woReplyDTO.getPlanStrTime()) 
                    || reqPlanStartDate.compareTo(replyPlanStartDate) < 0 ){
              
                    logger.debug("Set Work Order Planned Start Date/Time {} ", woTaskModifyRequestDTO.getPlanStrDate()+":"+woTaskModifyRequestDTO.getPlanStrTime());
                    woModifyRequestDTO.setPlanStrDate(woTaskModifyRequestDTO.getPlanStrDate());
                    woModifyRequestDTO.setPlanStrTime(woTaskModifyRequestDTO.getPlanStrTime());
                    if(StringUtils.equals(originalWoMustStartInd, "E")){
                        woModifyRequestDTO.setMustStartInd("W");
                        isModifyWoMustStartInd = true;
                    }                    
                    isModifyWo = true;                    
                }
                
                String replyPlanFinDate = woReplyDTO.getPlanFinDate() + woReplyDTO.getPlanFinTime();
                String reqPlanFinDate = woTaskModifyRequestDTO.getPlanFinDate() + woTaskModifyRequestDTO.getPlanFinTime();
                
                if (StringUtils.isEmpty(woReplyDTO.getPlanFinDate()) || StringUtils.isEmpty(woReplyDTO.getPlanFinTime()) 
                    || reqPlanFinDate.compareTo(replyPlanFinDate) > 0 ) {
                    logger.debug("Set Work Order Planned Finish Date/Time {} ", woTaskModifyRequestDTO.getPlanFinDate()+":"+woTaskModifyRequestDTO.getPlanFinTime());
                    woModifyRequestDTO.setPlanFinDate(woTaskModifyRequestDTO.getPlanFinDate());
                    woModifyRequestDTO.setPlanFinTime(woTaskModifyRequestDTO.getPlanFinTime());
                    isModifyWo = true;
                }
                
                /*Issue register fix for 2113 - Should not throw error while updating task planned start date&time
                 * when workorder header CalculatedDurateFlag is set & MustStart indicator set to Error.
                 */
                if(StringUtils.equals(originalWoMustStartInd, "E") && woReplyDTO.isCalculatedDurationsFlag()){
                	WorkOrderServiceModifyRequestDTO woModifyMustStartIndDTO = new WorkOrderServiceModifyRequestDTO();
                	woModifyMustStartIndDTO.setMustStartInd("W");
                	woModifyMustStartIndDTO.setWorkOrder(woReplyDTO.getWorkOrder());
                	woModifyMustStartIndDTO.setDistrictCode(woReplyDTO.getDistrictCode());

                    workOrderService.modify(operationContext, woModifyMustStartIndDTO);
                    logger.debug("Workorder MustStart indicator is changed to Warning..");
                }
                /*Issue Register 2113 fix - Task finish date&time cannot be after WO finish date&time
                 * first swipe out task finish date&time, then set the new values and resubmit workorder task.
                 */
                String replyWOTaskFinDate = woTaskReplyDTO.getPlanFinDate() + woTaskReplyDTO.getPlanFinTime();
                logger.info("replyWOTaskFinDate {} reqWOTaskPlanFinDate {} ",replyWOTaskFinDate, reqPlanFinDate);
                if(reqPlanFinDate.compareTo(replyWOTaskFinDate) == 0 && (!woReplyDTO.isCalculatedDurationsFlag())){
                	woTaskModifyRequestDTO.setMustStartInd("W");                                      
                    woTaskModifyRequestDTO.setPlanFinDate("");
                    woTaskModifyRequestDTO.setPlanFinTime("");
                    woTaskModifyRequestDTO.setPlanStrDate(woTaskReplyDTO.getPlanStrDate());
                    woTaskModifyRequestDTO.setPlanStrTime(woTaskReplyDTO.getPlanStrTime());
                    logger.debug("old workorder task start date&time {} {} ",woTaskReplyDTO.getPlanStrDate(),woTaskReplyDTO.getPlanStrTime());
                    workOrderTaskService.modify(operationContext, woTaskModifyRequestDTO);
                    logger.debug("Task finish date&time are swiped out..");
                }
                
                // we finally modify the work order if needed. The field CalculatedDurationsFlag is true, then update only work order task.
                logger.debug("Current work order details - CalculatedDurationsFlag is {} ",woReplyDTO.isCalculatedDurationsFlag());
                if (isModifyWo && (!woReplyDTO.isCalculatedDurationsFlag()) ) {
                    woModifyRequestDTO.setWorkOrder(woReplyDTO.getWorkOrder());
                    woModifyRequestDTO.setDistrictCode(woReplyDTO.getDistrictCode());

                    logger.debug("Modify work order details - Planned Start DateTime {} Planned Finish DateTime {} ",woModifyRequestDTO.getPlanStrDate()+":"+woModifyRequestDTO.getPlanStrTime(), woModifyRequestDTO.getPlanFinDate()+":"+woModifyRequestDTO.getPlanFinTime());
                    workOrderService.modify(operationContext, woModifyRequestDTO);
                    logger.debug("Workorder is successfully updated..");
                }
                
                // keep track of the work order task must start indicator 
                if (!StringUtils.equals(woTaskReplyDTO.getPlanStrDate()+woTaskReplyDTO.getPlanStrTime(), woTaskModifyRequestDTO.getPlanStrDate()+woTaskModifyRequestDTO.getPlanStrTime())){
                    isModifyWoTaskMustStartInd = true;
                }
            }
 
            //logic when both Start/Finish dates are updated for workorder task.
            if (isModifyWoTaskMustStartInd && StringUtils.equals(woTaskReplyDTO.getMustStartInd(), "E")) {
                woTaskModifyRequestDTO.setMustStartInd("W");
                woTaskModifyRequestDTO.setPlanStrDate(woTaskReplyDTO.getPlanStrDate());
                woTaskModifyRequestDTO.setPlanStrTime(woTaskReplyDTO.getPlanStrTime());
                woTaskModifyRequestDTO.setPlanFinDate(woTaskReplyDTO.getPlanFinDate());
                woTaskModifyRequestDTO.setPlanFinTime(woTaskReplyDTO.getPlanFinTime());
                logger.debug("Set WorkorderTask MustStart Indicator to W ");
                workOrderTaskService.modify(operationContext, woTaskModifyRequestDTO);
            }     
            
            logger.debug("WorkOrder MustStart Indicator is {} ",isModifyWoMustStartInd);
            //setting new values back to update workorder task with W flag set        
            logger.debug("Modify work order task details - Planned Start DateTime {} Planned Finish DateTime {} ",clonedWOTaskRequest.getPlanStrDate()+":"+clonedWOTaskRequest.getPlanStrTime(), clonedWOTaskRequest.getPlanFinDate()+":"+clonedWOTaskRequest.getPlanFinTime());
            logger.debug("Modify work order task details - Safety Instr {} ", clonedWOTaskRequest.getSafetyInstr());
            clonedWOTaskRequest.setMustStartInd("W");
            workOrderTaskService.modify(operationContext, clonedWOTaskRequest);
            
            // change back the work order must start indicator if needed
            if (isModifyWoMustStartInd) {
                WorkOrderServiceModifyRequestDTO woModifyRequestDTO = new WorkOrderServiceModifyRequestDTO();
                woModifyRequestDTO.setWorkOrder(woReplyDTO.getWorkOrder());
                woModifyRequestDTO.setDistrictCode(woReplyDTO.getDistrictCode());
                woModifyRequestDTO.setMustStartInd(originalWoMustStartInd);
                logger.debug("Set Workorder MustStart Indicator to E ");
                workOrderService.modify(operationContext, woModifyRequestDTO);
            }
        } catch (EnterpriseServiceOperationException e) {
            errorMessageDTO = e.getFaultInfo().getErrorMessages().getErrorMessageDTO().get(0);
        } catch (com.mincom.enterpriseservice.ellipse.workorder.EnterpriseServiceOperationException e) {
            errorMessageDTO = e.getFaultInfo().getErrorMessages().getErrorMessageDTO().get(0);
        }
        
        if ( errorMessageDTO != null ) {
            throw new Exception("ERROR: Task Update: " + errorMessageDTO.getCode()  + " " + errorMessageDTO.getMessage());
        }
    }

    /*
     * Checks whether the work order task modifyDTO is really different than the replyDTO 
     * (the read work order task dto). Returns true if different and false otherwise.
     */
    private boolean isModified(WorkOrderTaskServiceModifyRequestDTO modifyDTO,
        WorkOrderTaskServiceReadReplyDTO replyDTO) {
        boolean isModified = !StringUtils.equals(modifyDTO.getSafetyInstr(), replyDTO.getSafetyInstr()) ||
                             !StringUtils.equals(modifyDTO.getPlanStrDate(), replyDTO.getPlanStrDate()) ||
                             !StringUtils.equals(modifyDTO.getPlanStrTime(), replyDTO.getPlanStrTime()) ||
                             !StringUtils.equals(modifyDTO.getPlanFinDate(), replyDTO.getPlanFinDate()) ||
                             !StringUtils.equals(modifyDTO.getPlanFinTime(), replyDTO.getPlanFinTime());
        
        return isModified;
    }

    /*
     * Does a read using the given list of work order tasks and returns all the read work order tasks (reply dtos).
     */
    private ArrayOfWorkOrderTaskServiceReadReplyDTO readWorkOrderTasks(EWSClientConversation conversation, OperationContext operationContext,
        WorkOrderTask workOrderTaskService,
        List<WorkOrderTaskServiceModifyRequestDTO> workOrderTaskServiceModifyRequestDTOList) throws Exception {
    	//logger.info("Start --- WorkOrderAction::readWorkOrderTasks..");
        ArrayOfWorkOrderTaskServiceReadRequestDTO arrayOfWorkOrderTaskServiceReadRequestDTO = new ArrayOfWorkOrderTaskServiceReadRequestDTO();
        List<WorkOrderTaskServiceReadRequestDTO> workOrderTaskServiceReadRequestDTOList = arrayOfWorkOrderTaskServiceReadRequestDTO.getWorkOrderTaskServiceReadRequestDTO();
        for (WorkOrderTaskServiceModifyRequestDTO workOrderTaskServiceModifyRequestDTO : workOrderTaskServiceModifyRequestDTOList) {
            WorkOrderTaskServiceReadRequestDTO dto = new WorkOrderTaskServiceReadRequestDTO();
            dto.setDistrictCode(workOrderTaskServiceModifyRequestDTO.getDistrictCode());
            dto.setWorkOrder(workOrderTaskServiceModifyRequestDTO.getWorkOrder());
            dto.setWOTaskNo(workOrderTaskServiceModifyRequestDTO.getWOTaskNo());
            workOrderTaskServiceReadRequestDTOList.add(dto);
        }
        
        WorkOrderTaskServiceReadReplyCollectionDTO replyDTO = workOrderTaskService.multipleRead(operationContext, arrayOfWorkOrderTaskServiceReadRequestDTO);
        ArrayOfWorkOrderTaskServiceReadReplyDTO arrayOfWorkOrderTaskServiceReadReplyDTO = replyDTO.getReplyElements();
        //logger.info("End --- WorkOrderAction::readWorkOrderTasks..");
        return arrayOfWorkOrderTaskServiceReadReplyDTO;
    }

    /*
     * Validates the work order and work order tasks. Checks for mandatory data and also that planned dates
     * and safety instructions are valid.
     */
    private void validate(EWSClientConversation conversation, OperationContext operationContext,
        List<WorkOrderTaskServiceModifyRequestDTO> workOrderTaskServiceModifyRequestDTOs) throws Exception {
        // validate work order header and task data
        List<String> safetyInstructions = new ArrayList<String>(workOrderTaskServiceModifyRequestDTOs.size());
        for (WorkOrderTaskServiceModifyRequestDTO workOrderTaskServiceModifyRequestDTO : workOrderTaskServiceModifyRequestDTOs) {
            com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderDTO workOrder = workOrderTaskServiceModifyRequestDTO.getWorkOrder();
            
            if (workOrder == null) {
                throw new Exception(MAINT_ORDER_HEADER_MANDATORY_ERROR);
            }
            
            if (workOrder.getPrefix() == null || workOrder.getNo() == null) {
                throw new Exception(MAINT_ORDER_HEADER_DOC_ID_MANDATORY_ERROR);
            }
            
            if (workOrderTaskServiceModifyRequestDTO.getWOTaskNo() == null ) {
                throw new Exception(MAINT_ORDER_LINE_OP_ID_MANDATORY_ERROR);
            }
            
            String planStrDate = workOrderTaskServiceModifyRequestDTO.getPlanStrDate();
            String planStrTime = workOrderTaskServiceModifyRequestDTO.getPlanStrTime();
            String planFinDate = workOrderTaskServiceModifyRequestDTO.getPlanFinDate();
            String planFinTime = workOrderTaskServiceModifyRequestDTO.getPlanFinTime();
            
            // We assume that either they all (the planned dates and times) have values or none at all, 
            // continue with the validation if they do have values. Just need to validate that the start 
            // date is earlier than the finish date. The Smooks mapping before this already validates 
            // that the date values are actual dates.
            if ( planStrDate != null ) {
                String startDate = planStrDate + planStrTime;
                String finishDate = planFinDate + planFinTime;
                logger.debug("startDate {} finishDate {} ",startDate, finishDate);
                if (startDate.compareTo(finishDate) >= 0) {
                    throw new Exception(PLANNED_TIME_PERIOD_ERROR);
                }
            }   
            
            safetyInstructions.add(workOrderTaskServiceModifyRequestDTO.getSafetyInstr());
        }
        
        //validateSafetyInstructions(conversation, operationContext, safetyInstructions);
    }
    
    public class ClonedWOTaskRequest extends WorkOrderTaskServiceModifyRequestDTO implements Cloneable{

		private static final long serialVersionUID = 1L;
		
		WorkOrderTaskServiceModifyRequestDTO dto = null;
    	
    	public ClonedWOTaskRequest(){
    		super();
    	}
    	
    	public ClonedWOTaskRequest(WorkOrderTaskServiceModifyRequestDTO dto){
    		this.dto = dto;
    	}
        protected Object clone() throws CloneNotSupportedException {
        	ClonedWOTaskRequest obj = new ClonedWOTaskRequest();
            obj.setDistrictCode(dto.getDistrictCode());
            obj.setWorkOrder(dto.getWorkOrder());
            com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderDTO workOrderDTO = new com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderDTO();
            workOrderDTO.setNo(dto.getWorkOrder().getNo());
            workOrderDTO.setPrefix(dto.getWorkOrder().getPrefix());
            obj.setWorkOrder(workOrderDTO);
            obj.setWOTaskNo(dto.getWOTaskNo());
            obj.setPlanStrDate(dto.getPlanStrDate());
            obj.setPlanStrTime(dto.getPlanStrTime());
            obj.setPlanFinDate(dto.getPlanFinDate());
            obj.setPlanFinTime(dto.getPlanFinTime());
            obj.setSafetyInstr(dto.getSafetyInstr());
            
            return obj;
        }
    }
}