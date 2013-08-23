/*package com.transgrid.mib.ellipse.success.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jboss.soa.esb.actions.ActionProcessingException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mincom.ellipse.service.m3660.projectmanagementexport.ArrayOfProjectManagementExportDTO;
import com.mincom.ellipse.service.m3660.projectmanagementexport.ArrayOfProjectManagementExportServiceResult;
import com.mincom.ellipse.service.m3660.projectmanagementexport.ProjectManagementExport;
import com.mincom.ellipse.service.m3660.projectmanagementexport.ProjectManagementExportDTO;
import com.mincom.ellipse.service.m3660.projectmanagementexport.ProjectManagementExportServiceResult;
import com.mincom.ellipse.service.m3660.projectmanagementexportitem.ArrayOfProjectManagementExportItemServiceResult;
import com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItem;
import com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO;
import com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemSearchParam;
import com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemServiceResult;
import com.mincom.enterpriseservice.ellipse.ErrorMessageDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ArrayOfContractItemServiceFetchPortMileRequestDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ArrayOfContractItemServiceModifyPortMileRequestDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ContractItem;
import com.mincom.enterpriseservice.ellipse.contractitem.ContractItemServiceFetchPortMileReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ContractItemServiceFetchPortMileReplyDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ContractItemServiceFetchPortMileRequestDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ContractItemServiceFetchPortMileRequiredAttributesDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ContractItemServiceModifyPortMileReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ContractItemServiceModifyPortMileReplyDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ContractItemServiceModifyPortMileRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.ArrayOfEquipmentReqmntsServiceCreateRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.ArrayOfEquipmentReqmntsServiceDeleteRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.ArrayOfEquipmentReqmntsServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.ArrayOfEquipmentReqmntsServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmnts;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceCreateReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceCreateRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceDeleteReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceDeleteRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceModifyReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceRetrieveReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceRetrieveRequestDTO;
import com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmntsServiceRetrieveRequiredAttributesDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.ArrayOfJobEstimateServiceFetchItemRequestDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.ArrayOfJobEstimateServiceModifyItemRequestDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimate;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceFetchItemReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceFetchItemReplyDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceFetchItemRequestDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceFetchItemRequiredAttributesDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceModifyItemReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceModifyItemReplyDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmnts;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceRetrieveReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceRetrieveRequestDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceRetrieveRequiredAttributesDTO;
import com.mincom.enterpriseservice.ellipse.project.ArrayOfProjectServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.ArrayOfProjectServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.ArrayOfProjectServiceScheduleRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.Project;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceActualsReplyDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceActualsRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceModifyReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceModifyReplyDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServicePlanReplyDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServicePlanRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceReadReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceReadRequiredAttributesDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceScheduleReplyDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceScheduleRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ArrayOfResourceReqmntsServiceCreateRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ArrayOfResourceReqmntsServiceDeleteRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ArrayOfResourceReqmntsServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ArrayOfResourceReqmntsServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmnts;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceCreateReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceCreateRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceDeleteReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceDeleteRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceModifyReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceRetrieveReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceRetrieveRequestDTO;
import com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmntsServiceRetrieveRequiredAttributesDTO;
import com.mincom.enterpriseservice.ellipse.workorder.ArrayOfWorkOrderServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workorder.ArrayOfWorkOrderServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrder;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceModifyReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceModifyReplyDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceReadReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceReadRequiredAttributesDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.ArrayOfWorkOrderTaskServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.ArrayOfWorkOrderTaskServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTask;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceModifyReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceModifyReplyDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceReadReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceReadRequiredAttributesDTO;
import com.mincom.enterpriseservice.ellipse.workrequest.EnterpriseServiceOperationException;
import com.mincom.enterpriseservice.ellipse.workrequest.WorkRequest;
import com.mincom.enterpriseservice.ellipse.workrequest.WorkRequestServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.workrequest.WorkRequestServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.workrequest.WorkRequestServiceReadRequiredAttributesDTO;
import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;
import com.mincom.mib.common.StandardError;
import com.mincom.mib.common.StandardResult;
import com.mincom.mib.common.utils.ObjectDataHelper;
import com.mincom.mib.ellipse.EllipseBaseAction;
import com.mincom.mib.ellipse.projectmanagement.ActivityType;

*//**
 * This class handles all web service calls related to Project Schedule Exports.
 * @author Jonathan Kuchar  <jonathan.kuchar@ventyx.abb.com>
 * @version    1.0
 * @since      2012-3-27
 * @see com.mincom.mib.ellipse.EllipseBaseAction
 *//*
public class ProjectScheduleAction extends EllipseBaseAction {

    *//**
     * Logger used for this class.
     * 
     * @see org.slf4j.Logger
     *//*
	public Logger logger = LoggerFactory.getLogger(ProjectScheduleAction.class);
	
	*//**
	 * Name of the project schedule export list inside the message, via the configuration file, {@link com.mincom.mib.ellipse.EllipseBaseAction.REQUEST_BEAN_ID REQUEST_BEAN_ID}.
	 *//*
	private String pseList;
	
	*//**
	 * Name of the project schedule export list inside the return message, via the configuration file, {@link com.mincom.mib.ellipse.EllipseBaseAction.RESULT_BEAN_ID RESULT_BEAN_ID}
	 *//*
	private String pseResult;
    
	*//**
	 * Configuration value, defaulted to false.  If true, this action
	 * will return missing objects as a warning, advising the user to refresh their system.
	 *//*
	private boolean leniency;
	
	*//**
	 * The code returned by ellipse when the EquipmentReqmnts object is not found.
	 * TODO: Ensure this is correct!
	 *//*
//	private static String EQUIP_REQ_MISSING_CODE = "mims.e.5033";
    
    *//**
     * These codes returned by ellipse when the ResourceReqmnts object is not found for the read.
     *//*
    private static String JE_RES_REQ_MISSING_CODE = "mims.e.0011";
    private static String WT_RES_REQ_MISSING_CODE = "mims.e.5031";
	
	*//**
	 * The code returned by ellipse when the ResourceReqmnts Job Estimate object is not found.
	 *//*
//	private static String REQMNTS_JOB_ESTIMATE_MISSING = "mims.e.4949";
	
	*//**
     * Default constructor
     * 
     * @param config - Configuration file to use.
     * @see EllipseBaseAction#EllipseBaseAction(ConfigTree)
	 *//*
	public ProjectScheduleAction(ConfigTree config) {
		super(config);
		pseList = config.getAttribute(REQUEST_BEAN_ID, "exports");
		pseResult = config.getAttribute(RESULT_BEAN_ID, "exportResults");
		leniency = config.getBooleanAttribute("warnIfItemNotFound", false);
	}

	*//**
	 * Process the data within the message. This data will be updated inside
	 *   the project management web service and the corresponding
     *   {@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}
     *   via their respective web services.
	 * 
	 * @param msg - Data to process.
	 * @return Message - Result of process call.
	 * @throws ActioProcessingException Thrown when an error occurs with the server call. 
	 *//*
	@Override
	public Message process(Message msg) throws ActionProcessingException {
		String currentContext = "process";
		
		try {
		    // Pull data from the message:
            @SuppressWarnings("unchecked")
            ArrayList<ProjectSchedule> psList = (ArrayList<ProjectSchedule>) getDTOObject(msg, pseList);
            
            // Start the web service communication:
            EWSClientConversation conversation = getConversation(msg);
            OperationContext oc = getOperationContext(msg);
            
            // Create the Project Management Export service
            ProjectManagementExport exportService = createService(conversation, ProjectManagementExport.class);
            ArrayOfProjectManagementExportDTO readReqList = new ArrayOfProjectManagementExportDTO();
            for(ProjectSchedule ps: psList) {
                ProjectManagementExportDTO readReq = ps.getEllipseDTO();
                readReqList.getProjectManagementExportDTO().add(readReq);
            }
            ArrayOfProjectManagementExportServiceResult readReply = exportService.multipleRead(oc, readReqList);
            
            inspectReplyForErrors(readReply, "ProjectScheduleAction.get");
            
            msg = modify(msg, psList);
            
		} catch (Exception ex) {
			logger.error("PSActivity::process(Message) - Exception: " + ex.getMessage());
			msg = storeExceptionInResponse(msg, currentContext, ex);
		}
		
		return msg;
	}

    *//**
     * Modify the given data inside the project management web service and the corresponding
     *   {@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}
     *   via their respective web services.
     * 
     * @param msg - Message that contains the hard data.
     * @param projectSched - ArrayList of {@link com.mincom.mib.ellipse.projectmanagement.ProjectSchedule ProjectSchedule} to modify.
     * @return Message - Result of modify call.
     * @throws ActionProcessingException Thrown when an error occurs with the server call.
     *//*
	public Message modify(Message msg, ArrayList<ProjectSchedule> projectSched) throws ActionProcessingException {
	    String currentContext = "modify(Message, ArrayList)";
        ArrayList<ProjectSchedule> retVal = new ArrayList<ProjectSchedule>();
        ArrayList<StandardResult> warnList = new ArrayList<StandardResult>();
        ArrayList<StandardError> errorList = new ArrayList<StandardError>();

	    try {
            // Start the web service communication:
            EWSClientConversation conversation = getConversation(msg);
            OperationContext oc = getOperationContext(msg);
            
            // Pull the user from the conversation/operation context.
            String user = conversation.getCredentials().user;
            if(oc.getRunAs() != null && oc.getRunAs().getUser() != null) {
                user = oc.getRunAs().getUser();
            }
            
            // Create the Project Management Export Item service
            ProjectManagementExportItem exportItemService = createService(conversation, ProjectManagementExportItem.class);
            
            // Create the services that a ProjectScheduleActivity will use, set them to null for now.  They will be initialized when needed:
            Project projectService = null;
            WorkOrder workOrderService = null;
            WorkOrderTask workOrderTaskService = null;
            ContractItem contractItemService = null;
            JobEstimate jobEstimateService = null;
            EquipmentReqmnts equipReqService = null;
            ResourceReqmnts labReqService = null;

            for(ProjectSchedule ps:projectSched) {
                ArrayList<ProjectScheduleActivity> psResult = new ArrayList<ProjectScheduleActivity>();

                List<ProjectManagementExportItemDTO> projItems = new ArrayList<ProjectManagementExportItemDTO>();
                List<ProjectManagementExportItemDTO> woItems = new ArrayList<ProjectManagementExportItemDTO>();
                List<ProjectManagementExportItemDTO> wotItems = new ArrayList<ProjectManagementExportItemDTO>();
                List<ProjectManagementExportItemDTO> ciItems = new ArrayList<ProjectManagementExportItemDTO>();
                List<ProjectManagementExportItemDTO> jeItems = new ArrayList<ProjectManagementExportItemDTO>();
                
                ArrayList<ProjectScheduleActivity> projActivities = new ArrayList<ProjectScheduleActivity>();
                ArrayList<ProjectScheduleActivity> woActivities = new ArrayList<ProjectScheduleActivity>();
                ArrayList<ProjectScheduleActivity> wotActivities = new ArrayList<ProjectScheduleActivity>();
                ArrayList<ProjectScheduleActivity> ciActivities = new ArrayList<ProjectScheduleActivity>();
                ArrayList<ProjectScheduleActivity> jeActivities = new ArrayList<ProjectScheduleActivity>();
                
                ArrayOfProjectManagementExportItemServiceResult pmeItems = getItems(exportItemService, conversation, oc, ps.getExportID());

                int index = 0;
                for(ProjectScheduleActivity act: ps.getScheduleActivities()) {
                    currentContext = "modify(Message, ArrayList) - Modify items: ID: " + act.getEntityID()
                        + ", estimate number: " + act.getJobEstimateNumber() + ", estimate item number: " + act.getJobEstimateItemID();
                    String estItemID = null;
                    if(act.getJobEstimateItemID() != null) {
                        estItemID = act.getJobEstimateItemID().toString();
                    }
                    // check activity here!
                    ProjectManagementExportItemDTO item = findData(pmeItems, act.getType(), act.getEntityID(), act.getJobEstimateNumber(), estItemID);
                    if(item == null) {
                        if(leniency) {
                            String details = "ProjectScheduleActivity was not found.  See additional information for more details.";
                            String additionalDetails = "ProjectScheduleActivity ID: " + act.getEntityID() + ", Export ID:" + ps.getExportID() + ", Estimate Number: " + act.getJobEstimateNumber();
                            StandardResult res = new StandardResult("Warning","ProjectScheduleAction.modify",details,additionalDetails,index);
                            warnList.add(res);
                        } else {
                            String details = "ProjectScheduleActivity was not found.  See additional information for more details.";
                            String additionalDetails = "ProjectScheduleActivity ID: " + act.getEntityID() + ", Export ID:" + ps.getExportID() + ", Estimate Number: " + act.getJobEstimateNumber();
                            StandardError error = new StandardError("Error","ProjectScheduleAction.modify",details,additionalDetails,index);
                            errorList.add(error);
                        }
                    } else {
                        switch(act.getType()) {
                            case PR: {
                                projItems.add(item);
                                projActivities.add(act);
                            }
                            break;
                            
                            case WO: {
                                woItems.add(item);
                                woActivities.add(act);
                            }
                            break;
                            
                            case WT: {
                                wotItems.add(item);
                                wotActivities.add(act);
                            }
                            break;
                            
                            case CI: {
                                ciItems.add(item);
                                ciActivities.add(act);
                            }
                            break;
                            
                            case JE: {
                                jeItems.add(item);
                                jeActivities.add(act);
                            }
                            break;
                            
                            default:
                                logger.error("ProjectScheduleAction::modify(Message, ArrayList) - Incorrect activity typeproject, work order, work order task, job estimate or contract item required.");
                                throw new Exception("Incorrect activity type: project, work order, work order task, job estimate or contract item required.");
                        }
                    }
                    ++index;
                }
                
                if(equipReqService == null) {
                    equipReqService = createService(conversation, EquipmentReqmnts.class);
                }
                
                if(labReqService == null) {
                    labReqService = createService(conversation, ResourceReqmnts.class);
                }
                
                if(projItems.size() > 0) {
                    currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify Project Data";
                    
                    if(projectService == null) {
                        projectService = createService(conversation, Project.class);
                    }
                    psResult.addAll(modifyProjects(oc,projectService, equipReqService, labReqService, msg, projActivities, projItems));
                }
                
                if(woItems.size() > 0) {
                    currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify WorkOrder Data";
                    if(workOrderService == null) {
                        workOrderService = createService(conversation, WorkOrder.class);
                    }
                    psResult.addAll(modifyWorkOrders(oc, workOrderService, equipReqService, labReqService, msg, woActivities, woItems));
                }
                
                if(wotItems.size() > 0) {
                    currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify WorkOrderTask Data";
                    if(workOrderTaskService == null) {
                        workOrderTaskService = createService(conversation, WorkOrderTask.class);
                    }
                    
                    psResult.addAll(modifyWorkOrderTasks(oc, workOrderTaskService, equipReqService, labReqService, msg, wotActivities, wotItems, user));
                    
                }
                
                if(ciItems.size() > 0) {
                    currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify Contract Item Data";
                    
                    if(contractItemService == null) {
                        contractItemService = createService(conversation, ContractItem.class);
                    }
                    psResult.addAll(modifyContractItems(oc, contractItemService, equipReqService, labReqService, msg, ciActivities, ciItems));
                }
                
                if(jeItems.size() > 0) {
                    currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify Job Estimate Item Data";
                    if(jobEstimateService == null) {
                        jobEstimateService = createService(conversation, JobEstimate.class);
                    }
                    psResult.addAll(modifyJobEstimates(oc, jobEstimateService, equipReqService, labReqService, msg, jeActivities, jeItems));
                }
                
                ProjectSchedule newPS = new ProjectSchedule(ps);
                newPS.setScheduleActivities(psResult);
                retVal.add(newPS);
            }
            

            if(errorList.size() > 0) {
                addErrorsToResponse(msg, errorList);
            }
            
            if(warnList.size() > 0) {
                // TODO will this cause an error?
                for(StandardResult sr: warnList) {
                    addResultToResponse(msg, sr.getCode(), sr.getOperation(), sr.getDetails() + " " + sr.getAdditionalInformation(), sr.getRequestIndex());
                }
            }
            
            if(retVal.size() > 0) {
                String details = "Processed " + retVal.size() + " ProjectManagementExports.";
                addResultToResponse(msg, "OK", "ProcessProjectManagementExports", details);
                storeReplyDTO(msg, pseResult, retVal);
            }
            
        } catch(Exception ex) {
            logger.error(currentContext);
            logger.error("PSActivity::modify(Message, ArrayList) - Exception: " + ex.getMessage());
            msg = storeExceptionInResponse(msg, currentContext, ex);
        }
        return msg;
	}

	static protected long EllipseTotal = 0;
	static protected long EllipseCallTotal = 0;
	
    *//**
     * Retrieve the given data from the project management web service and the corresponding
     *   {@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}
     *   via their respective web services.
     * 
     * @param msg - Message that contains the hard data.
     * @return Message - Result of retrieve call(s).
     * @throws ActionProcessingException Thrown when an error occurs with the server call.
     *//*
	public Message get(Message msg) throws ActionProcessingException {
		String currentContext = "get(Message)";
		long methodStart = Calendar.getInstance().getTimeInMillis();
		long before = 0;
		long after = 0;
		try {
		    // No searchMethod variable for read or the searchItems call for the exportItem.
		    @SuppressWarnings("unchecked")
		    ArrayList<ProjectScheduleSearchRequest> retrieveReq = (ArrayList<ProjectScheduleSearchRequest>) getDTOObject(msg, pseList);
		    
            // Start the web service communication:
            EWSClientConversation conversation = getConversation(msg);
            OperationContext oc = getOperationContext(msg);
		    
            ProjectManagementExport exportService = null;
            ProjectManagementExportItem exportItemService = null;

            before = Calendar.getInstance().getTimeInMillis();
            // create the project schedule service
            exportService = createService(conversation, ProjectManagementExport.class);
            after = Calendar.getInstance().getTimeInMillis();
            EllipseTotal = after - before;
            ++EllipseCallTotal;
            
            // create the project schedule item (activity) service
            before = Calendar.getInstance().getTimeInMillis();
            exportItemService = createService(conversation, ProjectManagementExportItem.class);
            after = Calendar.getInstance().getTimeInMillis();
            EllipseTotal += (after-before);
            ++EllipseCallTotal;
            
            // Set services so the ProjectManagementExportItem can retrieve each item:
            Project projServ = null;
            WorkOrder workServ = null;
            WorkOrderTask workTaskServ = null;
            ContractItem cItemServ = null;
            JobEstimate jobEstServ = null;
            WorkRequest workReqServ = null;
            
            ArrayList<ProjectSchedule> finalList = new ArrayList<ProjectSchedule>();

            Map<ProjectScheduleSearchRequest, ArrayOfProjectManagementExportServiceResult> results
                = new HashMap<ProjectScheduleSearchRequest, ArrayOfProjectManagementExportServiceResult>();
            
            logger.debug("ProjectScheduleAction::get(Message) - For ever retrieve request, call search:");
		    for(ProjectScheduleSearchRequest ps: retrieveReq) {
		        results.put(ps, new ArrayOfProjectManagementExportServiceResult());
		        ProjectManagementExportDTO restart = null;;
                boolean moreData = true;
                int batchCount = 0;
                while(moreData) {
                    logger.debug("ProjectScheduleAction::get(Message) - Calling search:");
    				// call retrieve for this project schedule
                    before = Calendar.getInstance().getTimeInMillis();
                    ArrayOfProjectManagementExportServiceResult reply = exportService.search(oc, ps, restart );
                    after = Calendar.getInstance().getTimeInMillis();
                    EllipseTotal += (after-before);
                    ++EllipseCallTotal;
                    
                    inspectReplyForErrors(reply, "ProjectScheduleAction.get");
                    int readCount = reply.getProjectManagementExportServiceResult().size();
                    logger.debug("ProjectScheduleAction::get(Message) - Search received batch count - {}, returned - {}", batchCount, readCount);
                    
                    if(readCount < oc.getMaxInstances()) {
                        moreData = false;
                    } else {
                        restart = reply.getProjectManagementExportServiceResult().get(readCount-1).getProjectManagementExportDTO();
                        ++batchCount;
                    }
                    results.get(ps).getProjectManagementExportServiceResult().addAll(reply.getProjectManagementExportServiceResult());
                }
		    }
		    
		    for(ProjectScheduleSearchRequest psr: results.keySet()) {
		        if(psr.getReturnType() == null) {
		            psr.setReturnType(PSReturnType.S);
		        }
	            for (ProjectManagementExportServiceResult replyDTO: results.get(psr).getProjectManagementExportServiceResult()) {
	                ProjectSchedule retHeader = new ProjectSchedule();
	                // Convert the result of the retrieve to a return project schedule:
	                retHeader.populate(replyDTO.getProjectManagementExportDTO());
	                
	                if((psr.getReturnType() == PSReturnType.P || psr.getReturnType() == PSReturnType.C)) {
	                    String exportId = replyDTO.getProjectManagementExportDTO().getExportId();
	                    
	                    ArrayOfProjectManagementExportItemServiceResult itemResults = getItems(exportItemService, conversation, oc, exportId);

                        if(psr.getReturnType() == PSReturnType.C) {                        
                            List<ProjectManagementExportItemDTO> projItems = new ArrayList<ProjectManagementExportItemDTO>();
                            List<ProjectManagementExportItemDTO> woItems = new ArrayList<ProjectManagementExportItemDTO>();
                            List<ProjectManagementExportItemDTO> wotItems = new ArrayList<ProjectManagementExportItemDTO>();
                            List<ProjectManagementExportItemDTO> ciItems = new ArrayList<ProjectManagementExportItemDTO>();
                            List<ProjectManagementExportItemDTO> jeItems = new ArrayList<ProjectManagementExportItemDTO>();
                            
                            // Loop through each item, then afterwards, if the array list of each one contains stuff, deal with it there.
                            for (ProjectManagementExportItemServiceResult itemResult: itemResults.getProjectManagementExportItemServiceResult()) {
                                ProjectManagementExportItemDTO item = itemResult.getProjectManagementExportItemDTO();
                                // pull the type out here:
                                // If needed, apply a conversion method here.
                                String type = item.getEntityType();
                                if(StringUtils.isEmpty(type)) {
                                    type=ActivityType.JE.name();
                                }
                                
                                switch (ActivityType.valueOf(type)) {
                                    case PR: {
                                        projItems.add(item);
                                        break;
                                    }
                                    case WO: {
                                        woItems.add(item);
                                        break;
                                    }
                                    case WT: {
                                        wotItems.add(item);
                                        break;                              
                                    }
                                    case CI: {
                                        ciItems.add(item);
                                        break;                              
                                    }
                                    case JE: {
                                        jeItems.add(item);
                                        break;                              
                                    }
                                    default: {
                                        logger.error("ProjectScheduleAction::get(Message) - Incorrect activity typeproject, work order, work order task,"
                                                + " job estimate or contract item required.");
                                        throw new Exception(
                                            "Incorrect activity type: project, work order, work order task, job estimate or contract item required.");
                                    }
                                }
                            }
                            
                            if(projItems.size() > 0) {
                                if (projServ == null)
                                {
                                    before = Calendar.getInstance().getTimeInMillis();
                                    projServ = createService(conversation, Project.class);
                                    after = Calendar.getInstance().getTimeInMillis();
                                    EllipseTotal += (after - before);
                                    ++EllipseCallTotal;
                                }
                                currentContext = "get(Message) - ProjectScheduleActivity: Retrieve Project Data";
                                retHeader.getScheduleActivities().addAll(getProjects(oc, projServ, projItems));
                            }
                            
                            if(woItems.size() > 0) {
                                currentContext = "get(Message) - ProjectScheduleActivity: Retrieve WorkOrder Data";
                                if (workServ == null) {
                                    before = Calendar.getInstance().getTimeInMillis();
                                    workServ = createService(conversation, WorkOrder.class);
                                    after = Calendar.getInstance().getTimeInMillis();
                                    EllipseTotal += (after-before);
                                    ++EllipseCallTotal;
                                }
                                retHeader.getScheduleActivities().addAll(getWorkOrders(oc, workServ, woItems));
                            }
                            
                            if(wotItems.size() > 0) {
                                currentContext = "get(Message) - ProjectScheduleActivity: Retrieve WorkOrderTask Data";
                                if (workTaskServ == null) {
                                    before = Calendar.getInstance().getTimeInMillis();
                                    workTaskServ = createService(conversation, WorkOrderTask.class);
                                    after = Calendar.getInstance().getTimeInMillis();
                                    EllipseTotal += (after - before);
                                    ++EllipseCallTotal;
                                }
                                retHeader.getScheduleActivities().addAll(getWorkOrderTasks(oc, workTaskServ, wotItems));
                            }
                            
                            if(ciItems.size() > 0) {
                                currentContext = "get(Message) - ProjectScheduleActivity: Retrieve Contract Item Data";
                                if (cItemServ == null) {
                                    before = Calendar.getInstance().getTimeInMillis();
                                    cItemServ = createService(conversation, ContractItem.class);
                                    after = Calendar.getInstance().getTimeInMillis();
                                    EllipseTotal += (after - before);
                                    ++EllipseCallTotal;
                                }
                                retHeader.getScheduleActivities().addAll(getContractItems(oc, cItemServ, ciItems));
                            }
                            
                            if(jeItems.size() > 0) {
                                currentContext = "get(Message) - ProjectScheduleActivity: Retrieve Job Estimate Data";
                                if (jobEstServ == null) {
                                    before = Calendar.getInstance().getTimeInMillis();
                                    jobEstServ = createService(conversation, JobEstimate.class);
                                    after = Calendar.getInstance().getTimeInMillis();
                                    EllipseTotal += (after - before);
                                    ++EllipseCallTotal;
                                }
                                retHeader.getScheduleActivities().addAll(getJobEstimates(oc, jobEstServ, jeItems));
                            }
                        
                            currentContext = "get(Message) - ProjectScheduleActivity: Retrieve resource requirements:";
                            retHeader = getResourceRequirements(retHeader, conversation, oc);
                            
                            currentContext = "get(Message) - ProjectScheduleActivity: Retrieve work request data";
                            if (workReqServ == null) {
                                before = Calendar.getInstance().getTimeInMillis();
                                workReqServ = createService(conversation, WorkRequest.class);
                                after = Calendar.getInstance().getTimeInMillis();
                                EllipseTotal += (after - before);
                                ++EllipseCallTotal;
                            }
                            retHeader = getWorkRequestData(workReqServ, oc, retHeader);
                        } else {
                            ArrayList<ProjectScheduleActivity> activityList = new ArrayList<ProjectScheduleActivity>(); 
                            for(ProjectManagementExportItemServiceResult res: itemResults.getProjectManagementExportItemServiceResult()) {
                                ProjectScheduleActivity psa = new ProjectScheduleActivity();
                                psa.setEllipseDTO(res.getProjectManagementExportItemDTO());
                                activityList.add(psa);
                            }
                            
                            retHeader.setScheduleActivities(activityList);
                        }
	                }
	                finalList.add(retHeader);
	            }
	        }
		    
		    String details = "Processed " + finalList.size() + " Project Management Export Retrieves";
		    addResultToResponse(msg, "OK", "RetrieveProjectManagementExports", details);
		    storeReplyDTO(msg, pseResult, finalList);
		} catch (Exception ex) {
			logger.error("ProjectScheduleActivity::get(Message) - Exception: " + ex.getMessage());
			msg = storeExceptionInResponse(msg, currentContext, ex);
		} finally {
	        logger.debug("ProjectScheduleAction: get(Message) - Total Ellipse web service calls: " + EllipseCallTotal);
            logger.debug("ProjectScheduleAction: get(Message) - Total milliseconds inside ellipse calls: " + EllipseTotal);
            logger.debug("ProjectScheduleAction: get(Message) - Total milliseconds inside get method: " + (Calendar.getInstance().getTimeInMillis() - methodStart));
		}
		
		return msg;
	}
	
	*//**
	 * Retrieve the work requests for each ProjectScheduleActivity attached to this ProjectSchedule
	 * 
	 * @param workReqServ - WorkRequest service web service to use.
	 * @param oc - OperationContext to use during the web service calls.
	 * @param retHeader - Data to populate and return.
	 * @return ProjectSchedule - the return data for the get method with work request data attached to it.
	 * @throws EnterpriseServiceOperationException - Error during web service call.
	 *//*
    private ProjectSchedule getWorkRequestData(WorkRequest workReqServ, OperationContext oc, ProjectSchedule retHeader) throws EnterpriseServiceOperationException {
        Map<String, WorkRequestServiceReadReplyDTO> workReqList = new HashMap<String, WorkRequestServiceReadReplyDTO>();
        
        WorkRequestServiceReadRequestDTO request = new WorkRequestServiceReadRequestDTO();
        WorkRequestServiceReadRequiredAttributesDTO reqAttr = new WorkRequestServiceReadRequiredAttributesDTO();
        reqAttr.setReturnRequestId(true);
        reqAttr.setReturnAssignPerson(true);
        reqAttr.setReturnAssignPersonDescription(true);
        reqAttr.setReturnPriorityCode(true);
        reqAttr.setReturnPriorityCodeDescription(true);
        reqAttr.setReturnSource(true);
        reqAttr.setReturnSourceDescription(true);
        
        for(ProjectScheduleActivity psa: retHeader.getScheduleActivities()) {
            if(StringUtils.isNotEmpty(psa.getWorkRequestID())) {
                if(!workReqList.containsKey(psa.getWorkRequestID())) {
                    request.setRequestId(psa.getWorkRequestID());
                    request.setRequiredAttributes(reqAttr);
                    
                    long before = Calendar.getInstance().getTimeInMillis();
                    WorkRequestServiceReadReplyDTO result = workReqServ.read(oc, request);
                    long after = Calendar.getInstance().getTimeInMillis();
                    EllipseTotal += (after-before);
                    ++EllipseCallTotal;
                    if(result != null) {
                        workReqList.put(result.getRequestId(), result);
                        psa.populateRequestFields(result);
                    } else {
                        logger.warn("RequestID " + psa.getWorkRequestID() + " not found");
                    }
                } else {
                    psa.populateRequestFields(workReqList.get(psa.getWorkRequestID()));
                }
            } else {
                logger.warn("Request ID is missing from export " + retHeader.getExportID() + " with item number " + psa.getEntityID());
            }
        }
        
        
        return retHeader;
    }

    *//**
     * Use the given data to retrieve JobEstimate data from the {@link com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimate JobEstimate} web service.
     * @param oc - Context to use for the web service call.
     * @param jobEstServ - JobEstimate web service to use use, should not be null!
     * @param items - {@link java.util.List List}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> data to retrieve.
     * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - Retrieved JobEstimate data as a list of ProjectScheduleActivity.
     * @throws com.mincom.enterpriseservice.ellipse.jobestimate.EnterpriseServiceOperationException - Error during web service call.
     * @throws java.text.ParseException Error during date conversion
     *//*
	protected ArrayList<ProjectScheduleActivity> getJobEstimates(OperationContext oc, JobEstimate jobEstServ, List<ProjectManagementExportItemDTO> items)
			throws com.mincom.enterpriseservice.ellipse.jobestimate.EnterpriseServiceOperationException, java.text.ParseException
	{
	    ArrayOfJobEstimateServiceFetchItemRequestDTO fetchReqList = new ArrayOfJobEstimateServiceFetchItemRequestDTO();
		ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>(); 
	    JobEstimateServiceFetchItemRequiredAttributesDTO reqAttrs = new JobEstimateServiceFetchItemRequiredAttributesDTO();
	    reqAttrs.setReturnEstimateNo(true);
	    reqAttrs.setReturnItemNo(true);
	    reqAttrs.setReturnDesc1(true);
	    reqAttrs.setReturnDesc2(true);
	    reqAttrs.setReturnEstimateStatus(true);
	    reqAttrs.setReturnJobType(true);
	    reqAttrs.setReturnJobTypeDescription(true);
	    reqAttrs.setReturnClass1(true);
	    reqAttrs.setReturnClass2(true);
	    reqAttrs.setReturnClass3(true);
	    reqAttrs.setReturnClass4(true);
	    reqAttrs.setReturnClass5(true);
	    reqAttrs.setReturnClass1Description(true);
	    reqAttrs.setReturnClass2Description(true);
	    reqAttrs.setReturnClass3Description(true);
	    reqAttrs.setReturnClass4Description(true);
	    reqAttrs.setReturnClass5Description(true);
	    reqAttrs.setReturnPlanStrDate(true);
	    reqAttrs.setReturnPlanStrTime(true);
	    reqAttrs.setReturnPlanFinDate(true);
	    reqAttrs.setReturnPlanFinTime(true);
	    reqAttrs.setReturnReqdStartDate(true);
	    reqAttrs.setReturnReqdStartTime(true);
	    reqAttrs.setReturnReqdFinishDate(true);
	    reqAttrs.setReturnReqdFinishTime(true);
	    reqAttrs.setReturnEquipmentRef(true);
	    reqAttrs.setReturnEquipmentNo(true);
	    reqAttrs.setReturnEquipmentNoDescription(true);
	    reqAttrs.setReturnEquipmentNoDescription2(true);
        reqAttrs.setReturnCalculatedEquipmentCost(true);
        reqAttrs.setReturnCalculatedEquipmentFlag(true);
        reqAttrs.setReturnCalculatedLabCost(true);
        reqAttrs.setReturnCalculatedLabHours(true);
        reqAttrs.setReturnCalculatedLabFlag(true);
        reqAttrs.setReturnCalculatedMatCost(true);
        reqAttrs.setReturnCalculatedMatFlag(true);
        reqAttrs.setReturnCalculatedOtherCost(true);
        reqAttrs.setReturnCalculatedOtherFlag(true);
        reqAttrs.setReturnEstimatedDurationsHrs(true);
        reqAttrs.setReturnManEffort(true);
        reqAttrs.setReturnEstimatedEquipmentCost(true);
        reqAttrs.setReturnEstimatedLabCost(true);
        reqAttrs.setReturnEstimatedLabHrs(true);
        reqAttrs.setReturnEstimatedMatCost(true);
        reqAttrs.setReturnEstimatedOtherCost(true);
        reqAttrs.setReturnEstimatedTotalCost(true);
        reqAttrs.setReturnOriginatingKey(true);
        reqAttrs.setReturnOriginatingType(true);
		
		for(ProjectManagementExportItemDTO item: items) {
		    JobEstimateServiceFetchItemRequestDTO jeReq = new JobEstimateServiceFetchItemRequestDTO();

	        jeReq.setEstimateNo(item.getEstimateNumber());

	        if (StringUtils.isNotBlank(item.getJobEstimateItemNumber())) {
	            jeReq.setItemNo(new BigDecimal(StringUtils.trim(item.getJobEstimateItemNumber())));
	        } else {
	            // Should I set this to 0?
	            // This is to get the header record.
	            jeReq.setItemNo(BigDecimal.ZERO);
	        }

	        jeReq.setRequiredAttributes(reqAttrs);
	        fetchReqList.getJobEstimateServiceFetchItemRequestDTO().add(jeReq);
		}

		if(fetchReqList.getJobEstimateServiceFetchItemRequestDTO().size() > 0) {
		    long before = Calendar.getInstance().getTimeInMillis();
    		JobEstimateServiceFetchItemReplyCollectionDTO jeItemRes = jobEstServ.multipleFetchItem(oc, fetchReqList);
    		long after = Calendar.getInstance().getTimeInMillis();
    		EllipseTotal += (after-before);
    		++EllipseCallTotal;
    		
    		int index = 0;
            // Store the required data inside the project schedule activity:
    		for(JobEstimateServiceFetchItemReplyDTO jeItem: jeItemRes.getReplyElements().getJobEstimateServiceFetchItemReplyDTO()) {
    	        ProjectScheduleActivity activity = new ProjectScheduleActivity();
    	        activity.setEllipseDTO(items.get(index));
    	        activity.populateFields(jeItem);
                activity.setExpectedResourceRequirementsExist(true);    	        
                activity.setActualResourceRequirementsExist(true);    	        
    	        retVal.add(activity);
    	        ++index;
    		}
		}
		return retVal;
	}

	*//**
	 * Use the given data to modify JobEstimate data from the {@link com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimate JobEstimate} web service.
	 * @param oc - {@link com.mincom.ews.service.connectivity.OperationContext OperationContext} to use during server interaction.
	 * @param jobServ - JobEstimate web service to use, must not be null.
     * @param equipReqService - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmnts EquipmentReqmnts} web service to use, must not be null.
     * @param labReqService - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmnts ResourceReqmnts ResourceReqmnts} web service to use, must not be null.
     * @param msg - {@link org.jboss.soa.esb.message.Message Message} Used only for logging successful status of server calls.
	 * @param activities - {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> that relate to job estimates.
	 * @param items - {@link java.util.ArrayList ArrayList}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> that relate to job estimates.
	 * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - modified data.
	 * @throws Exception Error during server call.
	 *//*
    protected ArrayList<ProjectScheduleActivity> modifyJobEstimates(OperationContext oc, JobEstimate jobServ,
            EquipmentReqmnts equipReqService, ResourceReqmnts labReqService, Message msg, ArrayList<ProjectScheduleActivity> activities,
            List<ProjectManagementExportItemDTO> items) throws Exception {
        ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
        ArrayOfJobEstimateServiceModifyItemRequestDTO modifyReqList = new ArrayOfJobEstimateServiceModifyItemRequestDTO();
        
        
        ArrayList<ProjectManagementExportItemDTO> finalItemList = new ArrayList<ProjectManagementExportItemDTO>();
        ArrayList<ProjectScheduleActivity> finalActivityList = new ArrayList<ProjectScheduleActivity>();
        
        for(int index = 0; index < items.size(); ++index) {
            // TODO This can cause an error.  The user can just set a JobEstimate to "IN" status and it would attempt to be processed.
            // TODO  The real problem is the ProjectManagementExportItemDTO not setting the status properly.
            // TODO  If that worked correctly, this could rely on something form the server, which will be far more reliable then something from the user.
            logger.warn("ProjectScheduleAction::modify(Message, ArrayList) - This may cause problems if the user inserted the status incorrectly, JobEstimate number: " + activities.get(index).getJobEstimateNumber());
            if("IN".equals(items.get(index).getEstimateItemStatus()) || "IN".equals(activities.get(index).getJobEstimateStatus())) {
                finalItemList.add(items.get(index));
                finalActivityList.add(activities.get(index));
            } else {
                logger.warn("ProjectScheduleAction::modify(Message, ArrayList) - Can't update Job Estimate number: " + items.get(index).getEstimateNumber()
                        + " with item: " + items.get(index).getJobEstimateItemNumber());
                logger.warn("ProjectScheduleAction::modify(Message, ArrayList) - Estimate status is NOT in progress.");
            }
        }
        
        for(ProjectScheduleActivity act: finalActivityList) {
            modifyReqList.getJobEstimateServiceModifyItemRequestDTO().add(act.getJobEstimateItemModify());
        }
        
        if(modifyReqList.getJobEstimateServiceModifyItemRequestDTO().size() > 0) {
            JobEstimateServiceModifyItemReplyCollectionDTO jobEstimateItemReply = jobServ.multipleModifyItem(oc, modifyReqList);
            
            int finalIndex = 0;
            for(JobEstimateServiceModifyItemReplyDTO modRes:jobEstimateItemReply.getReplyElements().getJobEstimateServiceModifyItemReplyDTO()) {
                ProjectScheduleActivity psaResult = new ProjectScheduleActivity();
                psaResult.populateFields(modRes);
                psaResult.populateEstimateFields(finalItemList.get(finalIndex));
                
                ArrayOfEquipmentReqmntsServiceModifyRequestDTO equipReqModList = new ArrayOfEquipmentReqmntsServiceModifyRequestDTO();
                ArrayOfEquipmentReqmntsServiceDeleteRequestDTO equipReqDelList = new ArrayOfEquipmentReqmntsServiceDeleteRequestDTO();
                ArrayOfResourceReqmntsServiceModifyRequestDTO resReqModList = new ArrayOfResourceReqmntsServiceModifyRequestDTO();
                ArrayOfResourceReqmntsServiceDeleteRequestDTO resReqDelList = new ArrayOfResourceReqmntsServiceDeleteRequestDTO();
                
                for(Object obj: activities.get(finalIndex).getResourceRequests()) {
                    if(obj instanceof EquipmentReqmntsServiceModifyRequestDTO) {
                        EquipmentReqmntsServiceModifyRequestDTO equipReq = (EquipmentReqmntsServiceModifyRequestDTO)obj;
                        if (null == equipReq.getEqptType() &&
                            	null == equipReq.getUOM() &&
                        		null == equipReq.getQuantityRequired() &&
                        		null == equipReq.getUnitQuantityReqd()) {
                            EquipmentReqmntsServiceDeleteRequestDTO equipDelReq = new EquipmentReqmntsServiceDeleteRequestDTO();
                        	equipDelReq.setJEItemNo(BigDecimal.valueOf(Long.valueOf(activities.get(finalIndex).getJobEstimateItemID())));
                            equipDelReq.setEstimateNo(activities.get(finalIndex).getJobEstimateNumber());
                            equipDelReq.setVersionNo(activities.get(finalIndex).getJobEstimateVersion());
                            equipDelReq.setDistrictCode(activities.get(finalIndex).getDistrictCode());
                            equipDelReq.setClassType("JE");
                            equipDelReq.setSeqNo(equipReq.getSeqNo());
                        	equipReqDelList.getEquipmentReqmntsServiceDeleteRequestDTO().add(equipDelReq);                        	
                        } else {
                                equipReq.setJEItemNo(BigDecimal.valueOf(Long.valueOf(activities.get(finalIndex).getJobEstimateItemID())));
                                equipReq.setEstimateNo(activities.get(finalIndex).getJobEstimateNumber());
                                equipReq.setVersionNo(activities.get(finalIndex).getJobEstimateVersion());
                                equipReq.setDistrictCode(activities.get(finalIndex).getDistrictCode());
                                equipReq.setClassType("JE");
                            	equipReqModList.getEquipmentReqmntsServiceModifyRequestDTO().add(equipReq);                        	
                    	}
                    } else if(obj instanceof ResourceReqmntsServiceModifyRequestDTO) {
                        ResourceReqmntsServiceModifyRequestDTO labReq = (ResourceReqmntsServiceModifyRequestDTO)obj;
                        if (null == labReq.getUOM() &&
                        		null == labReq.getQuantityRequired() &&
                            	null == labReq.getUnitQuantityReqd()) {
                        	ResourceReqmntsServiceDeleteRequestDTO labDelReq = new ResourceReqmntsServiceDeleteRequestDTO();
                        	labDelReq.setJEItemNo(BigDecimal.valueOf(Long.valueOf(activities.get(finalIndex).getJobEstimateItemID())));
                            labDelReq.setEstimateNo(activities.get(finalIndex).getJobEstimateNumber());
                            labDelReq.setVersionNo(activities.get(finalIndex).getJobEstimateVersion());
                            labDelReq.setDistrictCode(activities.get(finalIndex).getDistrictCode());
                            labDelReq.setClassType("JE");
                            labDelReq.setResourceClass(labReq.getResourceClass());
                            labDelReq.setResourceCode(labReq.getResourceCode());
                            labDelReq.setSeqNo(labReq.getSeqNo());
                            resReqDelList.getResourceReqmntsServiceDeleteRequestDTO().add(labDelReq); 
                        } else {
                        	labReq.setJEItemNo(BigDecimal.valueOf(Long.valueOf(activities.get(finalIndex).getJobEstimateItemID())));
                        	labReq.setEstimateNo(activities.get(finalIndex).getJobEstimateNumber());
                        	labReq.setDistrictCode(activities.get(finalIndex).getDistrictCode());
                        	labReq.setVersionNo(activities.get(finalIndex).getJobEstimateVersion());
                        	labReq.setClassType("JE");
                        	resReqModList.getResourceReqmntsServiceModifyRequestDTO().add(labReq);
                        }
                    }
                }
                
                ArrayList<Object> resourceReply = handleResourceRequests(equipReqModList, equipReqDelList, resReqModList, resReqDelList, oc, equipReqService, labReqService, msg);
                if(!resourceReply.isEmpty()) {
                    psaResult.addResourceReplies(resourceReply);
                }
                psaResult.setExpectedResourceRequirementsExist(false);
                psaResult.setActualResourceRequirementsExist(false);
                retVal.add(psaResult);
                ++finalIndex;
            }
        }
        return retVal;
    }

    *//**
     * Use the given data to retrieve ContractItem data from the {@link com.mincom.enterpriseservice.ellipse.contractitem.ContractItem ContractItem} web service.
     * @param oc - Context to use for the web service call.
     * @param cItemServ - ContractItem web service to use use, should not be null!
     * @param items - {@link java.util.List List}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> data to retrieve.
     * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - Retrieved ContractItem data as a list of ProjectScheduleActivity.
     * @throws com.mincom.enterpriseservice.ellipse.contractitem.EnterpriseServiceOperationException - Error during web service call.
     * @throws java.text.ParseException Error during date conversion.
     *//*
	protected ArrayList<ProjectScheduleActivity> getContractItems(OperationContext oc, ContractItem cItemServ, List<ProjectManagementExportItemDTO> items)
			throws com.mincom.enterpriseservice.ellipse.contractitem.EnterpriseServiceOperationException, java.text.ParseException
	{
		ArrayOfContractItemServiceFetchPortMileRequestDTO fetchReqList = new ArrayOfContractItemServiceFetchPortMileRequestDTO();
		ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
		ContractItemServiceFetchPortMileRequiredAttributesDTO reqAttrs = new ContractItemServiceFetchPortMileRequiredAttributesDTO();
		reqAttrs.setReturnContractNo(true);
		reqAttrs.setReturnStatus385(true);
		reqAttrs.setReturnActualStartDt(true);
		
		for(ProjectManagementExportItemDTO item: items) {
		    ContractItemServiceFetchPortMileRequestDTO cItemReq = new ContractItemServiceFetchPortMileRequestDTO();
	        // Break apart the export ID, populate each variable inside the cItemRet
	        // via the act, ProjectScheduleActivity.
	        // Need to define a contract item entity key.
	        cItemReq.setContractNo(item.getParentEntity());
	        cItemReq.setPortion(item.getEntityKey());
	        cItemReq.setRequiredAttributes(reqAttrs);
		    fetchReqList.getContractItemServiceFetchPortMileRequestDTO().add(cItemReq);
		}
		
		if(fetchReqList.getContractItemServiceFetchPortMileRequestDTO().size() > 0) {
		    long before = Calendar.getInstance().getTimeInMillis();
    		ContractItemServiceFetchPortMileReplyCollectionDTO cItemRes = cItemServ.multipleFetchPortMile(oc, fetchReqList);
    		long after = Calendar.getInstance().getTimeInMillis();
    		EllipseTotal += (after-before);
    		++EllipseCallTotal;
    
    		int index = 0;
            // Store the required data inside the project schedule activity:
    		for(ContractItemServiceFetchPortMileReplyDTO cItem: cItemRes.getReplyElements().getContractItemServiceFetchPortMileReplyDTO()) {
        		ProjectScheduleActivity activity = new ProjectScheduleActivity();
        		activity.setEllipseDTO(items.get(index));
        		activity.populateFields(cItem);
                activity.setExpectedResourceRequirementsExist(true);
                activity.setActualResourceRequirementsExist(true);    	        

        		retVal.add(activity);
        		++index;
    		}
		}
		return retVal;
	}

    *//**
     * Use the given data to modify ContractItem data from the {@link com.mincom.enterpriseservice.ellipse.contractitem.ContractItem ContractItem} web service.
     * @param oc - {@link com.mincom.ews.service.connectivity.OperationContext OperationContext} to use during server interaction.
     * @param contractItemService - ContractItem web service to use, must not be null.
     * @param equipReqService - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmnts EquipmentReqmnts} web service to use, must not be null.
     * @param labReqService - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmnts ResourceReqmnts ResourceReqmnts} web service to use, must not be null.
     * @param msg - {@link org.jboss.soa.esb.message.Message Message} Used only for logging successful status of server calls.
     * @param activities - {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> that relate to contract items.
     * @param items - {@link java.util.ArrayList ArrayList}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> that relate to contract items.
     * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - modified data.
     * @throws Exception Error during server call.
     *//*
    protected ArrayList<ProjectScheduleActivity> modifyContractItems(
            OperationContext oc, ContractItem contractItemService,
            EquipmentReqmnts equipReqService, ResourceReqmnts labReqService, Message msg,
            ArrayList<ProjectScheduleActivity> activities, List<ProjectManagementExportItemDTO> items)
            throws Exception {
        ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
        ArrayOfContractItemServiceModifyPortMileRequestDTO modReqList = new ArrayOfContractItemServiceModifyPortMileRequestDTO();
        
        for(ProjectScheduleActivity act: activities) {
            ContractItemServiceModifyPortMileRequestDTO contractItemRequest = act.getContractItemModify();
            modReqList.getContractItemServiceModifyPortMileRequestDTO().add(contractItemRequest);
        }
        
        if(modReqList.getContractItemServiceModifyPortMileRequestDTO().size() > 0) {
            ContractItemServiceModifyPortMileReplyCollectionDTO contractReplyDTOs = contractItemService.multipleModifyPortMile(oc, modReqList);
            
            int index = 0;
            for(ContractItemServiceModifyPortMileReplyDTO contractItemReply: contractReplyDTOs.getReplyElements().getContractItemServiceModifyPortMileReplyDTO()) {
                ProjectScheduleActivity psaResult = new ProjectScheduleActivity();
                psaResult.populateFields(contractItemReply);
                psaResult.populateEstimateFields(items.get(index));
                psaResult.setExpectedResourceRequirementsExist(false);
                psaResult.setActualResourceRequirementsExist(false);    	        
                
                retVal.add(psaResult);
                ++index;
            }
        }
        
        return retVal;
    }

    *//**
     * Use the given data to retrieve WorkOrderTask data from the {@link com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTask WorkOrderTask} web service.
     * @param oc - Context to use for the web service call.
     * @param wTServ - WorkOrderTask web service to use use, should not be null!
     * @param items - {@link java.util.List List}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> data to retrieve.
     * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - Retrieved WorkOrderTask data as a list of ProjectScheduleActivity.
     * @throws com.mincom.enterpriseservice.ellipse.workordertask.EnterpriseServiceOperationException - Error during web service call.
     * @throws java.text.ParseException Error during date conversion.
     *//*
	protected ArrayList<ProjectScheduleActivity> getWorkOrderTasks(OperationContext oc, WorkOrderTask wTServ, List<ProjectManagementExportItemDTO> items)
			throws com.mincom.enterpriseservice.ellipse.workordertask.EnterpriseServiceOperationException, java.text.ParseException
	{
	    ArrayOfWorkOrderTaskServiceReadRequestDTO readReqList = new ArrayOfWorkOrderTaskServiceReadRequestDTO();
	    ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
	    WorkOrderTaskServiceReadRequiredAttributesDTO reqAttrs = new WorkOrderTaskServiceReadRequiredAttributesDTO();
	    reqAttrs.setReturnWorkOrder(true);
	    reqAttrs.setReturnWOTaskNo(true);
	    reqAttrs.setReturnWOTaskDesc(true);
	    reqAttrs.setReturnTaskStatusU(true);
	    reqAttrs.setReturnActualStartDate(true);
	    reqAttrs.setReturnActualStartTime(true);
	    reqAttrs.setReturnActualFinishDate(true);
	    reqAttrs.setReturnActualFinishTime(true);
	    reqAttrs.setReturnPlanStrDate(true);
        reqAttrs.setReturnPlanStrTime(true);
        reqAttrs.setReturnPlanFinDate(true);
        reqAttrs.setReturnPlanFinTime(true);
        reqAttrs.setReturnMustStartInd(true);
        reqAttrs.setReturnAssignPerson(true);
        reqAttrs.setReturnAssignPersonDescription(true);
        reqAttrs.setReturnCalculatedEquipmentCost(true);
        reqAttrs.setReturnCalculatedEquipmentFlag(true);
        reqAttrs.setReturnCalculatedLabCost(true);
        reqAttrs.setReturnCalculatedLabFlag(true);
        reqAttrs.setReturnCalculatedLabHrs(true);
        reqAttrs.setReturnCalculatedMatCost(true);
        reqAttrs.setReturnCalculatedMatFlag(true);
        reqAttrs.setReturnCalculatedOtherCost(true);
        reqAttrs.setReturnCalculatedOtherFlag(true);
        reqAttrs.setReturnEstimatedEquipmentCost(true);
        reqAttrs.setReturnEstimatedLabCost(true);
        reqAttrs.setReturnEstimatedLabHrs(true);
        reqAttrs.setReturnEstimatedMatCost(true);
        reqAttrs.setReturnEstimatedOtherCost(true);
        reqAttrs.setReturnEstimatedTotalCost(true);
        reqAttrs.setReturnTskDurationsHrs(true);
        reqAttrs.setReturnManEffort(true);
        reqAttrs.setReturnPcComplete(true);

	    for(ProjectManagementExportItemDTO item: items) {
	        WorkOrderTaskServiceReadRequestDTO workTaskReq = new WorkOrderTaskServiceReadRequestDTO();
	        // Break apart the entity key, populate each variable inside the workRet
	        // via the act, ProjectScheduleActivity.
	        // R100 00001889 002
	        // String entityKey = item.getEntityKey();
	        // String district = entityKey.substring(0,4);
	        // String workOrder = entityKey.substring(4,12);
	        // String workOrderTask = entityKey.substring(12);

	        com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderDTO wotID = new com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderDTO();
	        wotID.setPrefix(item.getWorkOrderNumber().substring(0, 2));
	        wotID.setNo(item.getWorkOrderNumber().substring(2));
	        workTaskReq.setWorkOrder(wotID);

	        workTaskReq.setWOTaskNo(item.getTaskNumber());
	        workTaskReq.setDistrictCode(item.getDistrictCode());
	        workTaskReq.setRequiredAttributes(reqAttrs);
	        readReqList.getWorkOrderTaskServiceReadRequestDTO().add(workTaskReq);
	    }

	    if(readReqList.getWorkOrderTaskServiceReadRequestDTO().size() > 0) {
	        long before = Calendar.getInstance().getTimeInMillis();
    		WorkOrderTaskServiceReadReplyCollectionDTO wTaskRes = wTServ.multipleRead(oc, readReqList);
    		long after = Calendar.getInstance().getTimeInMillis();
    		EllipseTotal += (after - before);
    		++EllipseCallTotal;
    
    		int index = 0;
            // Store the required data inside the project schedule activity:
    		for(WorkOrderTaskServiceReadReplyDTO taskRes : wTaskRes.getReplyElements().getWorkOrderTaskServiceReadReplyDTO()) {
    	        ProjectScheduleActivity activity = new ProjectScheduleActivity();
    	        activity.setEllipseDTO(items.get(index));
    	        activity.populateFields(taskRes);
                activity.setExpectedResourceRequirementsExist(true);
                activity.setActualResourceRequirementsExist(true);    	        

    	        retVal.add(activity);
    		    ++index;
    		}
	    }
		return retVal;
	}

    *//**
     * Use the given data to modify WorkOrderTask data from the {@link com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTask WorkOrderTask} web service.
     * @param oc - {@link com.mincom.ews.service.connectivity.OperationContext OperationContext} to use during server interaction.
     * @param workOrderTaskService - WorkOrderTask web service to use, must not be null.
     * @param equipReqService - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmnts EquipmentReqmnts} web service to use, must not be null.
     * @param labReqService - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmnts ResourceReqmnts ResourceReqmnts} web service to use, must not be null.
     * @param msg - {@link org.jboss.soa.esb.message.Message Message} Used only for logging successful status of server calls.
     * @param activities - {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> that relate to work order tasks.
     * @param items - {@link java.util.ArrayList ArrayList}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> that relate to work order tasks.
     * @param user - ID of user to assign work order task to.
     * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - modified data.
     * @throws Exception Error during server call.
     *//*
    protected ArrayList<ProjectScheduleActivity> modifyWorkOrderTasks(OperationContext oc, WorkOrderTask workOrderTaskService,
            EquipmentReqmnts equipReqService, ResourceReqmnts labReqService, Message msg,
            ArrayList<ProjectScheduleActivity> activities, List<ProjectManagementExportItemDTO> items,
            String user) throws Exception {

    	ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
        ArrayOfWorkOrderTaskServiceModifyRequestDTO modReqList = new ArrayOfWorkOrderTaskServiceModifyRequestDTO();
  
        for(ProjectScheduleActivity act:activities) {
            WorkOrderTaskServiceModifyRequestDTO workOrderTaskRequest = act.getWorkOrderTaskModify();
            modReqList.getWorkOrderTaskServiceModifyRequestDTO().add(workOrderTaskRequest);
        }
        
        if(modReqList.getWorkOrderTaskServiceModifyRequestDTO().size() > 0) {
            WorkOrderTaskServiceModifyReplyCollectionDTO wotReply = workOrderTaskService.multipleModify(oc, modReqList);

            int index = 0;
            for(WorkOrderTaskServiceModifyReplyDTO workOrderTaskReply: wotReply.getReplyElements().getWorkOrderTaskServiceModifyReplyDTO()) {
                ProjectScheduleActivity psaResult = new ProjectScheduleActivity();
                psaResult.populateFields(workOrderTaskReply);
                psaResult.populateEstimateFields(items.get(index));

                ArrayOfEquipmentReqmntsServiceModifyRequestDTO equipReqModList = new ArrayOfEquipmentReqmntsServiceModifyRequestDTO();
                ArrayOfEquipmentReqmntsServiceDeleteRequestDTO equipReqDelList = new ArrayOfEquipmentReqmntsServiceDeleteRequestDTO();
                ArrayOfResourceReqmntsServiceModifyRequestDTO resReqModList = new ArrayOfResourceReqmntsServiceModifyRequestDTO();
                ArrayOfResourceReqmntsServiceDeleteRequestDTO resReqDelList = new ArrayOfResourceReqmntsServiceDeleteRequestDTO();
        		com.mincom.enterpriseservice.ellipse.resourcereqmnts.WorkOrderDTO resWOID = new com.mincom.enterpriseservice.ellipse.resourcereqmnts.WorkOrderDTO();
        		com.mincom.enterpriseservice.ellipse.equipmentreqmnts.WorkOrderDTO equipWOID = new com.mincom.enterpriseservice.ellipse.equipmentreqmnts.WorkOrderDTO();
                	
                for(Object obj: activities.get(index).getResourceRequests()) {
                    if(obj instanceof EquipmentReqmntsServiceModifyRequestDTO) {
                        EquipmentReqmntsServiceModifyRequestDTO equipReq = (EquipmentReqmntsServiceModifyRequestDTO)obj;
                        if (null == equipReq.getEqptType() &&
                            	null == equipReq.getUOM() &&
                        		null == equipReq.getQuantityRequired() &&
                        		null == equipReq.getUnitQuantityReqd()) {
                            EquipmentReqmntsServiceDeleteRequestDTO equipDelReq = new EquipmentReqmntsServiceDeleteRequestDTO();
                            equipDelReq.setDistrictCode(activities.get(index).getDistrictCode());
                        	equipWOID.setPrefix(activities.get(index).getEntityID().substring(4,6));
                        	equipWOID.setNo(activities.get(index).getEntityID().substring(6,12));
                        	equipDelReq.setWorkOrder(equipWOID);
                        	equipDelReq.setWorkOrderTask(activities.get(index).getEntityID().substring(12,15));
                            equipDelReq.setClassType("WT");
                            equipDelReq.setSeqNo(equipReq.getSeqNo());
                        	equipReqDelList.getEquipmentReqmntsServiceDeleteRequestDTO().add(equipDelReq);                        	
                        } else {
                        		equipReq.setDistrictCode(activities.get(index).getDistrictCode());
                        		equipWOID.setPrefix(activities.get(index).getEntityID().substring(4,6));
                        		equipWOID.setNo(activities.get(index).getEntityID().substring(6,12));
                        		equipReq.setWorkOrder(equipWOID);
                        		equipReq.setWorkOrderTask(activities.get(index).getEntityID().substring(12,15));
                                equipReq.setClassType("WT");
                            	equipReqModList.getEquipmentReqmntsServiceModifyRequestDTO().add(equipReq);                        	
                    	}
                    } else if(obj instanceof ResourceReqmntsServiceModifyRequestDTO) {
                        ResourceReqmntsServiceModifyRequestDTO labReq = (ResourceReqmntsServiceModifyRequestDTO)obj;
                        if (null == labReq.getUOM() &&
                        		null == labReq.getQuantityRequired() &&
                            	null == labReq.getUnitQuantityReqd()) {
                        	ResourceReqmntsServiceDeleteRequestDTO labDelReq = new ResourceReqmntsServiceDeleteRequestDTO();
                        	labDelReq.setDistrictCode(activities.get(index).getDistrictCode());
                        	resWOID.setPrefix(activities.get(index).getEntityID().substring(4,6));
                        	resWOID.setNo(activities.get(index).getEntityID().substring(6,12));
                        	labDelReq.setWorkOrder(resWOID);
                        	labDelReq.setWorkOrderTask(activities.get(index).getEntityID().substring(12,15));
                            labDelReq.setClassType("WT");
                            labDelReq.setResourceClass(labReq.getResourceClass());
                            labDelReq.setResourceCode(labReq.getResourceCode());
                            labDelReq.setSeqNo(labReq.getSeqNo());
                            resReqDelList.getResourceReqmntsServiceDeleteRequestDTO().add(labDelReq); 
                        } else {
                        	labReq.setDistrictCode(activities.get(index).getDistrictCode());
                        	resWOID.setPrefix(activities.get(index).getEntityID().substring(4,6));
                        	resWOID.setNo(activities.get(index).getEntityID().substring(6,12));
                        	labReq.setWorkOrder(resWOID);
                        	labReq.setWorkOrderTask(activities.get(index).getEntityID().substring(12,15));
                        	labReq.setClassType("WT");
                        	resReqModList.getResourceReqmntsServiceModifyRequestDTO().add(labReq);
                        }
                    }
                }                
                ArrayList<Object> resourceReply = handleResourceRequests(equipReqModList, equipReqDelList, resReqModList, resReqDelList, oc, equipReqService, labReqService, msg);
                if(!resourceReply.isEmpty()) {
                    psaResult.addResourceReplies(resourceReply);
                }

                psaResult.setExpectedResourceRequirementsExist(false);
                psaResult.setActualResourceRequirementsExist(false);
                retVal.add(psaResult);
                ++index;
            }
        }
        return retVal;
    }

    *//**
     * Use the given data to retrieve WorkOrder data from the {@link com.mincom.enterpriseservice.ellipse.workorder.WorkOrder WorkOrder} web service.
     * @param oc - Context to use for the web service call.
     * @param workServ - WorkOrder web service to use use, should not be null!
     * @param items - {@link java.util.List List}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> data to retrieve.
     * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - Retrieved WorkOrder data as a list of ProjectScheduleActivity.
     * @throws com.mincom.enterpriseservice.ellipse.workorder.EnterpriseServiceOperationException - Error during web service call.
     * @throws java.text.ParseException Error during date conversion.
     *//*
	protected ArrayList<ProjectScheduleActivity> getWorkOrders(OperationContext oc, WorkOrder workServ, List<ProjectManagementExportItemDTO> items)
			throws com.mincom.enterpriseservice.ellipse.workorder.EnterpriseServiceOperationException, java.text.ParseException
	{
	    ArrayOfWorkOrderServiceReadRequestDTO readReqList = new ArrayOfWorkOrderServiceReadRequestDTO();
		ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
		WorkOrderServiceReadRequiredAttributesDTO reqAttrs = new WorkOrderServiceReadRequiredAttributesDTO();
		reqAttrs.setReturnParentWo(true);
		reqAttrs.setReturnWorkOrder(true);
		reqAttrs.setReturnWorkOrderDesc(true);
		reqAttrs.setReturnWorkOrderStatusU(true);
		reqAttrs.setReturnWorkOrderType(true);
		reqAttrs.setReturnWorkOrderTypeDescription(true);
		reqAttrs.setReturnCloseCommitDate(true);
		reqAttrs.setReturnAccountCode(true);
		reqAttrs.setReturnAccountCodeDescription(true);
		reqAttrs.setReturnActualStartDate(true);
		reqAttrs.setReturnActualStartTime(true);
		reqAttrs.setReturnActualFinishDate(true);
		reqAttrs.setReturnActualFinishTime(true);
		reqAttrs.setReturnActualLabCost(true);
		reqAttrs.setReturnActualLabHrs(true);
		reqAttrs.setReturnActualEquipmentCost(true);
		reqAttrs.setReturnActualMatCost(true);
		reqAttrs.setReturnActualOtherCost(true);
		reqAttrs.setReturnActualDurationsHrs(true);
		reqAttrs.setReturnActualTotalCost(true);
		reqAttrs.setReturnAssignPerson(true);
		reqAttrs.setReturnAssignPersonDescription(true);
		reqAttrs.setReturnEquipmentRef(true);
		reqAttrs.setReturnEquipmentNo(true);
		reqAttrs.setReturnEquipmentNoDescription1(true);
		reqAttrs.setReturnEquipmentNoDescription2(true);
		reqAttrs.setReturnPlanStrDate(true);
		reqAttrs.setReturnPlanStrTime(true);
		reqAttrs.setReturnPlanFinDate(true);
		reqAttrs.setReturnPlanFinTime(true);
		reqAttrs.setReturnMustStartInd(true);
		reqAttrs.setReturnRequisitionStartDate(true);
		reqAttrs.setReturnRequisitionStartTime(true);
		reqAttrs.setReturnRequiredByDate(true);
		reqAttrs.setReturnRequiredByTime(true);		
		reqAttrs.setReturnCalculatedDurationsFlag(true);
		reqAttrs.setReturnCalculatedEquipmentCost(true);
		reqAttrs.setReturnCalculatedEquipmentFlag(true);
		reqAttrs.setReturnCalculatedLabCost(true);
		reqAttrs.setReturnCalculatedLabFlag(true);
		reqAttrs.setReturnCalculatedLabHrs(true);
		reqAttrs.setReturnCalculatedMatCost(true);
		reqAttrs.setReturnCalculatedMatFlag(true);
		reqAttrs.setReturnCalculatedOtherCost(true);
		reqAttrs.setReturnCalculatedOtherFlag(true);
		reqAttrs.setReturnCalculatedTotalFlag(true);
		reqAttrs.setReturnEstimatedDurationsHrs(true);
		reqAttrs.setReturnEstimatedEquipmentCost(true);
		reqAttrs.setReturnEstimatedLabCost(true);
		reqAttrs.setReturnEstimatedLabHrs(true);
		reqAttrs.setReturnEstimatedMatCost(true);
		reqAttrs.setReturnEstimatedOtherCost(true);
		reqAttrs.setReturnEstimatedTotalCost(true);
		reqAttrs.setReturnPcComplete(true);
		
		for(ProjectManagementExportItemDTO item: items) {
    		WorkOrderServiceReadRequestDTO woReq = new WorkOrderServiceReadRequestDTO();
    		// Break apart the entity key, populate each variable inside the workRet
    		// via the act, ProjectScheduleActivity.
    		// R100 00001889
    		// String entityKey = item.getEntityKey();
    		// String district = entityKey.substring(0,4);
    		// String workOrder = entityKey.substring(4,12);
    
    		com.mincom.enterpriseservice.ellipse.workorder.WorkOrderDTO woID = new com.mincom.enterpriseservice.ellipse.workorder.WorkOrderDTO();
    		woID.setPrefix(item.getWorkOrderNumber().substring(0, 2));
    		woID.setNo(item.getWorkOrderNumber().substring(2));
    
    		woReq.setWorkOrder(woID);
    		woReq.setDistrictCode(item.getDistrictCode());
    		woReq.setRequiredAttributes(reqAttrs);
    		readReqList.getWorkOrderServiceReadRequestDTO().add(woReq);
		}

		if(readReqList.getWorkOrderServiceReadRequestDTO().size() > 0) {
		    long before = Calendar.getInstance().getTimeInMillis();
    		WorkOrderServiceReadReplyCollectionDTO workRes = workServ.multipleRead(oc, readReqList);
    		long after = Calendar.getInstance().getTimeInMillis();
    		EllipseTotal += (after - before);
    		++EllipseCallTotal;
    
    		int index = 0;
    		for( WorkOrderServiceReadReplyDTO replyDTO: workRes.getReplyElements().getWorkOrderServiceReadReplyDTO()) {
        		// Store the required data inside the project schedule activity:
        		ProjectScheduleActivity activity = new ProjectScheduleActivity();
        		activity.setEllipseDTO(items.get(index));
        		activity.populateFields(replyDTO);
        		activity.setExpectedResourceRequirementsExist(true);
                activity.setActualResourceRequirementsExist(true);    	        

                retVal.add(activity);
        		++index;
    		}
		}
    		
		return retVal;

	}

    *//**
     * Use the given data to modify WorkOrder data from the {@link com.mincom.enterpriseservice.ellipse.workorder.WorkOrder WorkOrder} web service.
     * @param oc - {@link com.mincom.ews.service.connectivity.OperationContext OperationContext} to use during server interaction.
     * @param workOrderService - WorkOrder web service to use, must not be null.
     * @param equipReqService - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmnts EquipmentReqmnts} web service to use, must not be null.
     * @param labReqService - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmnts ResourceReqmnts ResourceReqmnts} web service to use, must not be null.
     * @param msg - {@link org.jboss.soa.esb.message.Message Message} Used only for logging successful status of server calls.
     * @param activities - {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> that relate to work orders.
     * @param items - {@link java.util.ArrayList ArrayList}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> that relate to work orders.
     * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - modified data.
     * @throws Exception Error during server call.
     *//*
    protected ArrayList<ProjectScheduleActivity> modifyWorkOrders(OperationContext oc, WorkOrder workOrderService,
            EquipmentReqmnts equipReqService, ResourceReqmnts labReqService, Message msg,
            ArrayList<ProjectScheduleActivity> activities, List<ProjectManagementExportItemDTO> items)
            throws Exception {

    	ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
        ArrayOfWorkOrderServiceModifyRequestDTO modReqList = new ArrayOfWorkOrderServiceModifyRequestDTO();
        for(ProjectScheduleActivity act:activities) {
            WorkOrderServiceModifyRequestDTO workOrderRequest = act.getWorkOrderModify();
            modReqList.getWorkOrderServiceModifyRequestDTO().add(workOrderRequest);
        }
        
        if(modReqList.getWorkOrderServiceModifyRequestDTO().size() > 0) {
            WorkOrderServiceModifyReplyCollectionDTO woReply = workOrderService.multipleModify(oc, modReqList);
            
            int index = 0;
            for(WorkOrderServiceModifyReplyDTO workOrderReply: woReply.getReplyElements().getWorkOrderServiceModifyReplyDTO()) {
                ProjectScheduleActivity psaResult = new ProjectScheduleActivity();
                psaResult.populateFields(workOrderReply);
                psaResult.populateEstimateFields(items.get(index));
                psaResult.setExpectedResourceRequirementsExist(false);
                psaResult.setActualResourceRequirementsExist(false);    	        
               
                retVal.add(psaResult);
                ++index;
            }
        }
        return retVal;
    }

	*//**
	 * Use the given data to retrieve Project data from the {@link com.mincom.enterpriseservice.ellipse.project.Project Project} web service.
	 * @param oc - Context to use for the web service call.
	 * @param projServ - Project web service to use use, should not be null!
	 * @param items - {@link java.util.List List}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> data to retrieve.
	 * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - Retrieved Project data as a list of ProjectScheduleActivity.
	 * @throws com.mincom.enterpriseservice.ellipse.project.EnterpriseServiceOperationException - Error during web service call.
	 * @throws java.text.ParseException Error during date conversion.
	 *//*
	protected ArrayList<ProjectScheduleActivity> getProjects(OperationContext oc, Project projServ, List<ProjectManagementExportItemDTO> items)
			throws com.mincom.enterpriseservice.ellipse.project.EnterpriseServiceOperationException, java.text.ParseException
	{
	    ArrayOfProjectServiceReadRequestDTO readReqList = new ArrayOfProjectServiceReadRequestDTO();
	    ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
	    ProjectServiceReadRequiredAttributesDTO reqAttr = new ProjectServiceReadRequiredAttributesDTO();
	    reqAttr.setReturnProjDesc(true);
	    reqAttr.setReturnParentProject(true);
	    reqAttr.setReturnStatus(true);
	    reqAttr.setReturnActualStrDate(true);
	    reqAttr.setReturnActualFinDate(true);
	    reqAttr.setReturnPlanStrDate(true);
	    reqAttr.setReturnPlanFinDate(true);
	    reqAttr.setReturnTargetStrDate(true);
	    reqAttr.setReturnTargetFinDate(true);
	    reqAttr.setReturnAssignPerson(true);
	    reqAttr.setReturnAssignToDescription(true);
	    reqAttr.setReturnShedStrDate(true);
	    reqAttr.setReturnSchedFinDate(true);
	    reqAttr.setReturnClass1(true);
	    reqAttr.setReturnClass2(true);
	    reqAttr.setReturnClass3(true);
	    reqAttr.setReturnClass4(true);
	    reqAttr.setReturnClass5(true);
	    reqAttr.setReturnClass1Description(true);
	    reqAttr.setReturnClass2Description(true);
	    reqAttr.setReturnClass3Description(true);
	    reqAttr.setReturnClass4Description(true);
	    reqAttr.setReturnClass5Description(true);
	    reqAttr.setReturnAccountCode(true);
	    reqAttr.setReturnAccountCodeDescription(true);
	    reqAttr.setReturnEquipmentRef(true);
	    reqAttr.setReturnEquipmentNo(true);
	    reqAttr.setReturnEquipmentNoDescription(true);
	    reqAttr.setReturnEquipmentNoDescription2(true);
	    reqAttr.setReturnPercentCompl(true);
	    
	    for(ProjectManagementExportItemDTO item: items) {
	        ProjectServiceReadRequestDTO projReq = new ProjectServiceReadRequestDTO();
	        // Break apart the export ID, populate each variable inside the progRet
	        // via the act, ProjectScheduleActivity.
	        // R100 N0001358
	        // String entityKey = item.getEntityKey();
	        // String district = entityKey.substring(0,4);
	        // String project = entityKey.substring(4);

	        projReq.setProjectNo(item.getProjectNumber());
	        projReq.setDistrictCode(item.getDistrictCode());
	        projReq.setRequiredAttributes(reqAttr);
	        readReqList.getProjectServiceReadRequestDTO().add(projReq);
	    }

		
	    if(readReqList.getProjectServiceReadRequestDTO().size() > 0) {
	        long before = Calendar.getInstance().getTimeInMillis();
    		ProjectServiceReadReplyCollectionDTO projRes = projServ.multipleRead(oc, readReqList);
    		long after = Calendar.getInstance().getTimeInMillis();
    		EllipseTotal += (after-before);
    		++EllipseCallTotal;
    
    		// Store the required data inside the project schedule activity:
    		int index = 0;
    		for(ProjectServiceReadReplyDTO replyDTO: projRes.getReplyElements().getProjectServiceReadReplyDTO()) {
    		    ProjectScheduleActivity activity = new ProjectScheduleActivity();
    		    activity.setEllipseDTO(items.get(index));
    		    activity.populateFields(replyDTO);
    		    activity.setExpectedResourceRequirementsExist(true);
                activity.setActualResourceRequirementsExist(true);    	        

    		    retVal.add(activity);
    		    ++index;
    		}
	    }
	    
		return retVal;
	}

	*//**
     * Use the given data to modify Project data from the {@link com.mincom.enterpriseservice.ellipse.project.Project Project} web service.
     * @param oc - {@link com.mincom.ews.service.connectivity.OperationContext OperationContext} to use during server interaction.
     * @param projectService - Project web service to use, must not be null. 
     * @param equipReqService - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmnts EquipmentReqmnts} web service to use, must not be null.
     * @param labReqService - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmnts ResourceReqmnts ResourceReqmnts} web service to use, must not be null.
     * @param msg - {@link org.jboss.soa.esb.message.Message Message} Used only for logging successful status of server calls.
     * @param activities - {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> that relate to projects.
     * @param items - {@link java.util.ArrayList ArrayList}<{@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTO}> that relate to projects.
     * @return {@link java.util.ArrayList ArrayList}<{@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}> - modified data.
	 * @throws Exception Error during server call.
     *//*
    protected ArrayList<ProjectScheduleActivity> modifyProjects(OperationContext oc, Project projectService,
            EquipmentReqmnts equipReqService, ResourceReqmnts labReqService, Message msg,
            ArrayList<ProjectScheduleActivity> activities, List<ProjectManagementExportItemDTO> items)
            throws Exception {
        ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
        
        for(ProjectScheduleActivity act:activities) {
            ProjectScheduleActivity psaResult = new ProjectScheduleActivity();
        	ProjectServiceModifyReplyDTO projModReply = new ProjectServiceModifyReplyDTO();
        	if (null != act.getRequiredStartDateTime() || null != act.getRequiredFinishDateTime()) {
        		ProjectServiceModifyRequestDTO projectModRequest = act.getProjectModify();
            	projModReply = projectService.modify(oc, projectModRequest);
            	psaResult.populateFields(projModReply);
        	}
        	ProjectServicePlanReplyDTO projPlanReply = new ProjectServicePlanReplyDTO();
        	if (null != act.getPlannedStartDateTime() || null != act.getPlannedFinishDateTime()) {
        		ProjectServicePlanRequestDTO projectPlanRequest = act.getProjectPlan();
            	projPlanReply = projectService.plan(oc, projectPlanRequest);
            	psaResult.populateFields(projPlanReply);
        	}
        	ProjectServiceScheduleReplyDTO projSchedReply = new ProjectServiceScheduleReplyDTO();
        	if (null != act.getScheduleStartDateTime() || null != act.getScheduleEndDateTime()) {
        		ProjectServiceScheduleRequestDTO projectSchedRequest = act.getProjectSchedule();
            	projSchedReply = projectService.schedule(oc, projectSchedRequest);
            	psaResult.populateFields(projSchedReply);
        	}
        	ProjectServiceActualsReplyDTO projActReply = new ProjectServiceActualsReplyDTO();
        	if (null != act.getActualStartDateTime() || null != act.getActualEndDateTime()) {
        		ProjectServiceActualsRequestDTO projectActRequest = act.getProjectActuals();
            	projActReply = projectService.actuals(oc, projectActRequest);
            	psaResult.populateFields(projActReply);
            	// Ellipse service doesn't return values below, so need to set from Activity.
                psaResult.setEntityID(act.getEntityID());
                psaResult.setActualStartDateTime(ProjectSchedule.toDateFromEllipseDateString(projectActRequest.getActualStrDate(),null));
                psaResult.setActualEndDateTime(ProjectSchedule.toDateFromEllipseDateString(projectActRequest.getActualFinDate(),null));
        	}
        	psaResult.setExpectedResourceRequirementsExist(false);
            psaResult.setActualResourceRequirementsExist(false);    	        

        	retVal.add(psaResult);
        }
        
        return retVal;
    }
	
	*//**
	 * Retrieve the {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTOs}
	 * @param exportItemService - {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItem ProjectManagementExportItem} service to use.
	 * @param conversation - The current conversation to use. {@link com.mincom.ews.client.EWSClientConversation EWSClientConversation)
	 * @param oc - The current operation context
	 * @param parentExportID - The export ID used to retrieve the associated items.
	 * @return Array list of {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTOs}, as an {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ArrayOfProjectManagementExportItemServiceResult ArrayOfProjectManagementExportItemServiceResult}
	 * @throws Exception Error during service interaction
	 *//*
	private ArrayOfProjectManagementExportItemServiceResult getItems(ProjectManagementExportItem exportItemService, EWSClientConversation conversation, OperationContext oc, String parentExportID) throws Exception {
	    long after = 0;
	    long before = 0;
	    if(exportItemService == null) {
	        before = Calendar.getInstance().getTimeInMillis();
	        exportItemService = createService(null, ProjectManagementExportItem.class);
	        after = Calendar.getInstance().getTimeInMillis();
	        EllipseTotal += (after - before);
	        ++EllipseCallTotal;
	    }
	    ArrayOfProjectManagementExportItemServiceResult retVal = new ArrayOfProjectManagementExportItemServiceResult();
	    
	    ProjectManagementExportItemSearchParam searchParams = new ProjectManagementExportItemSearchParam();
	    searchParams.setExportId(parentExportID);
	    
	    ProjectManagementExportItemDTO restart = null;
	    boolean moreData = true;
	    int batchCount = 0;
	    logger.debug("ProjectScheduleAction::getItems() - Call searchItems, starting loop:");
	    while(moreData) {
	        ++batchCount;
	        logger.debug("ProjectScheduleAction::getItems() - Begin searchItem loop.");
	        before = Calendar.getInstance().getTimeInMillis();
	        ArrayOfProjectManagementExportItemServiceResult itemResults = exportItemService.searchItems(oc, searchParams , restart);
	        after = Calendar.getInstance().getTimeInMillis();
	        EllipseTotal += (after - before);
	        ++EllipseCallTotal;
	        
	        inspectReplyForErrors(itemResults, "ProjectScheduleAction.getItems");
	        int readCount = itemResults.getProjectManagementExportItemServiceResult().size();
	        logger.debug("ProjectScheduleAction::getItems() - search received batch count {}, returned {}", batchCount, readCount);
	        
	        if(readCount < oc.getMaxInstances()) {
	            moreData = false;
	        } else {
	            // RESTART INFO IS UGLY, CAN WE WRITE A BUG ON THIS?
	            restart = itemResults.getProjectManagementExportItemServiceResult().get(readCount -1).getProjectManagementExportItemDTO();
	        }
	        
	        retVal.getProjectManagementExportItemServiceResult().addAll(itemResults.getProjectManagementExportItemServiceResult());
	    }

	    return retVal;
	}
	
	*//**
	 * Search the array for the given key.
	 * @param pmeArray - Array of {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProgramManagementExportItemDTO} to search.
	 * @param type - {@link com.mincom.mib.ellipse.projectmanagement.ActivityType ActivityType} that we are searching for.
	 * @param entityID - The entity ID of the data.  This is used if the type IS NOT ActivityType.JE.
	 * @param estimateNo - The estimate number of the data.  This is used if the type IS ActivityType.JE.
	 * @param estimateItemNo - The estimate item number of the data.  This is used if the type IS ActivityType.JE. 
	 * @return True if the ID was found, false if it wasn't.
	 *//*
	private ProjectManagementExportItemDTO findData(ArrayOfProjectManagementExportItemServiceResult pmeArray, ActivityType type, String entityID, String estimateNo, String estimateItemNo) {
	    logger.debug("ProjectScheduleAction::findData() - Begin.");
	    ProjectManagementExportItemDTO retVal = null;
	    
	    if(type != ActivityType.JE) {
	        int i = 0;
	        while(retVal == null && i < pmeArray.getProjectManagementExportItemServiceResult().size()) {
	            ProjectManagementExportItemDTO current = pmeArray.getProjectManagementExportItemServiceResult().get(i).getProjectManagementExportItemDTO();                
                if(StringUtils.equals(StringUtils.trimToNull(current.getEntityKey()),StringUtils.trimToNull(entityID))) {
                    retVal = current;
                    logger.debug("ProjectScheduleAction::findData() - The activity with entityID: " + entityID + " was in the list!");
                }
	            ++i;
	        }
	    } else {
	        int i = 0;
	        while(retVal == null && i < pmeArray.getProjectManagementExportItemServiceResult().size()) {
                ProjectManagementExportItemDTO current = pmeArray.getProjectManagementExportItemServiceResult().get(i).getProjectManagementExportItemDTO();
                if(StringUtils.equals(StringUtils.trimToNull(current.getEstimateNumber()),StringUtils.trimToNull(estimateNo))
                        && StringUtils.equals(StringUtils.trimToNull(current.getJobEstimateItemNumber()),StringUtils.trimToNull(estimateItemNo))) {
                    retVal = current;
                    logger.debug("ProjectScheduleAction::findData() - The Job Estimate with ID: " + estimateNo + " was in the list!");
                }
                ++i;
            }
	    }
	    logger.debug("ProjectScheduleAction::findData() - End.");
	    return retVal;
	}
	
	*//**
	 * Iterate through the given list of ProjectSchedules and their Activities and retrieve each of their resources. 
	 * @param projectSchedules - {@link com.mincom.mib.ellipse.projectmanagement.ProjectSchedule ProjectSchedule} to retrieve resources for.
	 * @param conversation - {@link com.mincom.ews.client.EWSClientConversation EWSClientConversation} to use when creating a web service connection. 
	 * @param oc - {@link com.mincom.ews.service.connectivity.OperationContext OperationContext} to use during server call.
	 * @return {@link com.mincom.mib.ellipse.projectmanagement.ProjectSchedule ProjectSchedule} that includes the resource requirements tied to each activity.
	 * @throws Exception Error during web service call.
	 *//*
	private ProjectSchedule getResourceRequirements(ProjectSchedule projectSchedules,
	        EWSClientConversation conversation, OperationContext oc) throws Exception {
	    long before = 0;
	    long after = 0;
	    before = Calendar.getInstance().getTimeInMillis();
	    ResourceReqmnts resReqServ = createService(conversation, ResourceReqmnts.class);
	    after = Calendar.getInstance().getTimeInMillis();
	    EllipseTotal += (after - before);
	    ++EllipseCallTotal;
	    
	    before = Calendar.getInstance().getTimeInMillis();
        EquipmentReqmnts equipReqServ = createService(conversation, EquipmentReqmnts.class);
        after = Calendar.getInstance().getTimeInMillis();
        EllipseTotal += (after-before);
        ++EllipseCallTotal;
        
        before = Calendar.getInstance().getTimeInMillis();
        MaterialReqmnts matReqServ = createService(conversation, MaterialReqmnts.class);
        after = Calendar.getInstance().getTimeInMillis();
        EllipseTotal += (after - before);
        ++EllipseCallTotal;

        for(ProjectScheduleActivity psa: projectSchedules.getScheduleActivities()) {
            switch(psa.getType()) {
                case JE: {
                    if(!psa.getJobEstimateStatus().equals("PK")) {
                        if(StringUtils.isNotBlank(psa.getJobEstimateItemID())) {
                            ResourceReqmntsServiceRetrieveRequestDTO resRet = new ResourceReqmntsServiceRetrieveRequestDTO();
                            EquipmentReqmntsServiceRetrieveRequestDTO equipRet = null;
                            MaterialReqmntsServiceRetrieveRequestDTO matRet = null;
                            
                            resRet.setJEItemNo(BigDecimal.valueOf(Long.valueOf(psa.getJobEstimateItemID())));
                            resRet.setEstimateNo(psa.getJobEstimateNumber());
                            resRet.setDistrictCode(psa.getDistrictCode());
                            resRet.setClassType("JE");
                            resRet.setRetrAllFlg(true);
                            resRet.setVersionNo(psa.getJobEstimateVersion());
                            
                            if(psa.isExpectedEquipmentFlag()) {
                                equipRet = new EquipmentReqmntsServiceRetrieveRequestDTO();
                                ObjectDataHelper.defaultData(equipRet, resRet, false);
                            }
                            
                            if(psa.isExpectedMaterialFlag()) {
                                matRet = new MaterialReqmntsServiceRetrieveRequestDTO();
                                ObjectDataHelper.defaultData(matRet, resRet, false);
                            }
                            
                            if(!psa.isExpectedLabourFlag()) {
                                resRet = null;
                            }
                            
                            if(psa.isExpectedEquipmentFlag() || psa.isExpectedMaterialFlag() || psa.isExpectedLabourFlag()) {
                                psa.addResourceReplies(retrieveResources(resRet, resReqServ, equipRet, equipReqServ, matRet, matReqServ, oc));
                            }
                        }
                    } else {
                        logger.warn("ProjectScheduleAction::getResourceRequirements() - JobEstimate "
                            + psa.getJobEstimateNumber() + " with item ID " + psa.getJobEstimateItemID()
                            + " was packaged so it will be skipped for resource retrieval.");
                    }
                }
                break;
                case WT: {
                    ResourceReqmntsServiceRetrieveRequestDTO resRet = null;
                    EquipmentReqmntsServiceRetrieveRequestDTO equipRet = null;
                    MaterialReqmntsServiceRetrieveRequestDTO matRet = null;
                    
                    String ID = psa.getEntityParentID();
                    com.mincom.enterpriseservice.ellipse.resourcereqmnts.WorkOrderDTO resWODTO
                        = new com.mincom.enterpriseservice.ellipse.resourcereqmnts.WorkOrderDTO();
                    
                    if(psa.isExpectedLabourFlag()) {
                        resRet = new ResourceReqmntsServiceRetrieveRequestDTO();
                        resWODTO.setPrefix(ID.substring(0, 2));
                        resWODTO.setNo(ID.substring(2));
                        resRet.setWorkOrder(resWODTO);
                        resRet.setWorkOrderTask(psa.getEntityID().substring(12));
                        resRet.setDistrictCode(psa.getDistrictCode());
                        resRet.setClassType("WT");
                        resRet.setRetrAllFlg(true);
                    }
                    
                    if(psa.isExpectedEquipmentFlag()) {
                        equipRet = new EquipmentReqmntsServiceRetrieveRequestDTO();
                        com.mincom.enterpriseservice.ellipse.equipmentreqmnts.WorkOrderDTO equipWODTO
                            = new com.mincom.enterpriseservice.ellipse.equipmentreqmnts.WorkOrderDTO();
                        equipWODTO.setPrefix(resWODTO.getPrefix());
                        equipWODTO.setNo(resWODTO.getNo());
                        equipRet.setWorkOrder(equipWODTO);
                        equipRet.setWorkOrderTask(psa.getEntityID().substring(12));
                        equipRet.setDistrictCode(psa.getDistrictCode());
                        equipRet.setClassType("WT");
                        equipRet.setRetrAllFlg(true);
                    }
                    
                    if(psa.isExpectedMaterialFlag()) {
                        matRet = new MaterialReqmntsServiceRetrieveRequestDTO();
                        com.mincom.enterpriseservice.ellipse.materialreqmnts.WorkOrderDTO matWODTO
                            = new com.mincom.enterpriseservice.ellipse.materialreqmnts.WorkOrderDTO();
                        matWODTO.setPrefix(resWODTO.getPrefix());
                        matWODTO.setNo(resWODTO.getNo());
                        matRet.setWorkOrder(matWODTO);
                        matRet.setWorkOrderTask(psa.getEntityID().substring(12));
                        matRet.setDistrictCode(psa.getDistrictCode());
                        matRet.setClassType("WT");
                        matRet.setRetrAllFlg(true);
                    }
                    
                    if(psa.isExpectedEquipmentFlag() || psa.isExpectedMaterialFlag() || psa.isExpectedLabourFlag()) {
                        psa.addResourceReplies(retrieveResources(resRet, resReqServ, equipRet, equipReqServ, matRet, matReqServ, oc));
                    }
                }
                break;
                case CI:
                case PR:
                case WO: {
                    logger.debug("Nothing to retrieve for this ProjectScheduleActivity.");
                }
                break;
            }
        }
	    return projectSchedules;
	}
	
	*//**
	 * Retrieve the given resource requirements.
	 * @param resRet - Resource requirements to retrieve.
	 * @param resReqServ - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmnts ResourceReqmnts} web service to use.
	 * @param equipRet - Equipment requirements to retrieve.
	 * @param equipReqServ - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmnts EquipmentReqmnts} web service to use.
	 * @param matRet - Material requirements to retrieve.
	 * @param matReqServ - {@link com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmnts MaterialReqmnts} web service to use.
	 * @param oc - {@link com.mincom.ews.service.connectivity.OperationContext OperationContext} to use during server call.
	 * @return {@link java.util.ArrayList ArrayList}<{@link java.lang.Object Object}> of the retrieved resource requirements.
	 * @throws Exception Error during web service call.
	 *//*
	private ArrayList<Object> retrieveResources(
            ResourceReqmntsServiceRetrieveRequestDTO resRet, ResourceReqmnts resReqServ,
            EquipmentReqmntsServiceRetrieveRequestDTO equipRet, EquipmentReqmnts equipReqServ,
            MaterialReqmntsServiceRetrieveRequestDTO matRet, MaterialReqmnts matReqServ, OperationContext oc) throws Exception {
	    logger.debug("ProjectScheduleAction::retrieveResources() - Begin");
	    long before = 0;
	    long after = 0;
	    ArrayList<Object> retVal = new ArrayList<Object>();
	    
	    ResourceReqmntsServiceRetrieveRequiredAttributesDTO resReqAttr = new ResourceReqmntsServiceRetrieveRequiredAttributesDTO();
	    resReqAttr.setReturnSeqNo(true);
	    resReqAttr.setReturnResourceCode(true);
	    resReqAttr.setReturnRescCodeDescription(true);
	    resReqAttr.setReturnResourceClass(true);
	    resReqAttr.setReturnRescClassDescription(true);
	    resReqAttr.setReturnQuantityRequired(true);
	    resReqAttr.setReturnUOM(true);
	    resReqAttr.setReturnUOMDescription(true);
	    resReqAttr.setReturnRateAmount(true);
	    resReqAttr.setReturnUnitQuantityReqd(true);
	    resReqAttr.setReturnFixedAmount(true);
	    resReqAttr.setReturnHrsReqd(true);
	    
	    MaterialReqmntsServiceRetrieveRequiredAttributesDTO matReqAttr = new MaterialReqmntsServiceRetrieveRequiredAttributesDTO();
	    matReqAttr.setReturnSeqNo(true);
	    matReqAttr.setReturnStockCode(true);
	    matReqAttr.setReturnMnemonic(true);
	    matReqAttr.setReturnPartNo(true);
	    matReqAttr.setReturnMatDesc1(true);
	    matReqAttr.setReturnMatDesc2(true);
	    matReqAttr.setReturnMatDesc3(true);
	    matReqAttr.setReturnMatDesc4(true);
	    matReqAttr.setReturnQuantityRequired(true);
	    matReqAttr.setReturnUOM(true);
	    matReqAttr.setReturnUOMDescription(true);
	    matReqAttr.setReturnRateAmount(true);
	    matReqAttr.setReturnUnitQuantityReqd(true);
	    matReqAttr.setReturnFixedAmount(true);
	    
	    EquipmentReqmntsServiceRetrieveRequiredAttributesDTO equipReqAttr = new EquipmentReqmntsServiceRetrieveRequiredAttributesDTO();
	    equipReqAttr.setReturnSeqNo(true);
	    equipReqAttr.setReturnEqptType(true);
	    equipReqAttr.setReturnEqptTypeDescription(true);
	    equipReqAttr.setReturnQuantityRequired(true);
	    equipReqAttr.setReturnUOM(true);
	    equipReqAttr.setReturnUOMDescription(true);
	    equipReqAttr.setReturnRateAmount(true);
	    equipReqAttr.setReturnUnitQuantityReqd(true);
	    equipReqAttr.setReturnFixedAmount(true);
	    
	    String restartInfo = null;
        boolean moreData = true;
        int batchCount = 0;
	    if(resRet != null) {
    	    logger.debug("ProjectScheduleAction::retrieveResources() - Retrieving ResourceReqmnts");
    	    
    	    try {
        	    while(moreData) {
        	        logger.debug("ProjectScheduleAction::retrieveResources() - Begin retrieve batch loop:");
        	        before = Calendar.getInstance().getTimeInMillis();
        	        ResourceReqmntsServiceRetrieveReplyCollectionDTO batchReply = resReqServ.retrieve(oc, resRet, resReqAttr, restartInfo);
        	        after = Calendar.getInstance().getTimeInMillis();
        	        EllipseTotal += (after-before);
        	        ++EllipseCallTotal;
        	        
        	        inspectReplyForErrors(batchReply, "ProjectScheduleAction.retrieveResources");
        	        int readCount = batchReply.getReplyElements().getResourceReqmntsServiceRetrieveReplyDTO().size();
        	        logger.debug("ProjectScheduleAction::retrieveResources() - Search received batch count - {}, returned - {}", batchCount, readCount);
        	        
        	        if(readCount < oc.getMaxInstances()) {
        	            moreData = false;
        	        } else {
        	            restartInfo = batchReply.getCollectionRestartPoint();
        	            ++batchCount;
        	        }
        	        
        	        logger.debug("ProjectScheduleAction::retrieveResources() - Adding retrieved ResourceReqmnts to return list.");
        	        retVal.addAll(batchReply.getReplyElements().getResourceReqmntsServiceRetrieveReplyDTO());
        	    }
    	    } catch (com.mincom.enterpriseservice.ellipse.resourcereqmnts.EnterpriseServiceOperationException esoe) {
    //	        if(extractError(esoe, REQMNTS_JOB_ESTIMATE_MISSING) == null) {
    //                throw esoe;
    //            }
    	    }
    	    logger.debug("ProjectScheduleAction::retrieveResources() - ResourceReqmnts batch loop complete.");
	    }
        
	    if(equipRet != null) {
            logger.debug("ProjectScheduleAction::retrieveResources() - Retrieving EquipmentReqmnts");
            restartInfo = null;
            moreData = true;
            batchCount = 0;
            try {
                while(moreData) {
                    logger.debug("ProjectScheduleAction::retrieveResources() - Begin retrieve batch loop:");
                    before = Calendar.getInstance().getTimeInMillis();
                    EquipmentReqmntsServiceRetrieveReplyCollectionDTO batchReply = equipReqServ.retrieve(oc, equipRet, equipReqAttr, restartInfo);
                    after = Calendar.getInstance().getTimeInMillis();
                    EllipseTotal += (after-before);
                    ++EllipseCallTotal;
                    
                    inspectReplyForErrors(batchReply, "ProjectScheduleAction.retrieveResources");
                    int readCount = batchReply.getReplyElements().getEquipmentReqmntsServiceRetrieveReplyDTO().size();
                    logger.debug("ProjectScheduleAction::retrieveResources() - Search received batch count - {}, returned - {}", batchCount, readCount);
                    
                    if(readCount < oc.getMaxInstances()) {
                        moreData = false;
                    } else {
                        restartInfo = batchReply.getCollectionRestartPoint();
                        ++batchCount;
                    }
                    
                    logger.debug("ProjectScheduleAction::retrieveResources() - Adding retrieved EquipmentReqmnts to return list.");
                    retVal.addAll(batchReply.getReplyElements().getEquipmentReqmntsServiceRetrieveReplyDTO());
                }
            } catch (com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EnterpriseServiceOperationException esoe) {
    //            if(extractError(esoe, REQMNTS_JOB_ESTIMATE_MISSING) == null) {
    //                throw esoe;
    //            }
            }
            logger.debug("ProjectScheduleAction::retrieveResources() - EquipmentReqmnts batch loop complete.");
	    }
        
	    if(matRet != null) {
            logger.debug("ProjectScheduleAction::retrieveResources() - Retrieving MaterialReqmnts");
            restartInfo = null;
            moreData = true;
            batchCount = 0;
            try {
                while(moreData) {
                    logger.debug("ProjectScheduleAction::retrieveResources() - Begin retrieve batch loop:");
                    before = Calendar.getInstance().getTimeInMillis();
                    MaterialReqmntsServiceRetrieveReplyCollectionDTO batchReply = matReqServ.retrieve(oc, matRet, matReqAttr, restartInfo);
                    after = Calendar.getInstance().getTimeInMillis();
                    EllipseTotal += (after - before);
                    ++EllipseCallTotal;
                    
                    inspectReplyForErrors(batchReply, "ProjectScheduleAction.retrieveResources");
                    int readCount = batchReply.getReplyElements().getMaterialReqmntsServiceRetrieveReplyDTO().size();
                    logger.debug("ProjectScheduleAction::retrieveResources() - Search received batch count - {}, returned - {}", batchCount, readCount);
                    
                    if(readCount < oc.getMaxInstances()) {
                        moreData = false;
                    } else {
                        restartInfo = batchReply.getCollectionRestartPoint();
                        ++batchCount;
                    }
                    
                    logger.debug("ProjectScheduleAction::retrieveResources() - Adding retrieved MaterialReqmnts to return list.");
                    retVal.addAll(batchReply.getReplyElements().getMaterialReqmntsServiceRetrieveReplyDTO());
                }
            } catch (com.mincom.enterpriseservice.ellipse.materialreqmnts.EnterpriseServiceOperationException esoe) {
    //            if(extractError(esoe, REQMNTS_JOB_ESTIMATE_MISSING) == null) {
    //                throw esoe;
    //            }
            }
            logger.debug("ProjectScheduleAction::retrieveResources() - MaterialReqmnts batch loop complete.");
	    }
	    
	    return retVal;
        
    }

    *//**
	 * Performs the web service call for EquipmentReqmnts and ResourceReqmnts and returns their result.
	 * @param equipReqModList - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.ArrayOfEquipmentReqmntsServiceModifyRequestDTO ArrayOfEquipmentReqmntsServiceModifyRequestDTO} is the list of modify request for equipment resources.
	 * @param resReqModList - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ArrayOfResourceReqmntsServiceModifyRequestDTO ArrayOfResourceReqmntsServiceModifyRequestDTO} is the list of modify request for labour resources.
	 * @param oc - {@link com.mincom.ews.service.connectivity.OperationContext OperationContext} to use during server interaction.
	 * @param equipReqService - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmnts EquipmentReqmnts} web service to use, must not be null.
	 * @param labReqService - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmnts ResourceReqmnts ResourceReqmnts} web service to use, must not be null.
	 * @param msg - {@link org.jboss.soa.esb.message.Message Message} Used only for logging successful status of server calls.
	 * @return Returns an ArrayList of the web service replies.
	 * @throws Exception Error during web service call(s).
	 *//*
    private ArrayList<Object> handleResourceRequests(ArrayOfEquipmentReqmntsServiceModifyRequestDTO equipReqModList,
    		ArrayOfEquipmentReqmntsServiceDeleteRequestDTO equipReqDelList,ArrayOfResourceReqmntsServiceModifyRequestDTO resReqModList, 
    		ArrayOfResourceReqmntsServiceDeleteRequestDTO resReqDelList, OperationContext oc,
	        EquipmentReqmnts equipReqService, ResourceReqmnts labReqService, Message msg)
	        throws Exception {
        logger.debug("ProjectScheduleAction::handleResourceRequests() - Begin");
	    ArrayList<Object> retVal = new ArrayList<Object>();
	    
	    if(equipReqModList.getEquipmentReqmntsServiceModifyRequestDTO().size() > 0) {
	        logger.debug("ProjectScheduleAction::handleResourceRequests() - Processing EquipmentReqmnts Modify List:");
    	    ArrayOfEquipmentReqmntsServiceModifyRequestDTO equipModList = new ArrayOfEquipmentReqmntsServiceModifyRequestDTO();
    	    ArrayOfEquipmentReqmntsServiceCreateRequestDTO equipCreateList = new ArrayOfEquipmentReqmntsServiceCreateRequestDTO();
    	    
    	    logger.debug("ProjectScheduleAction::handleResourceRequests() - Determine missing:");
            Set<Integer> missingEquips = filterMissing(equipReqService, oc, equipReqModList);
            if(missingEquips.isEmpty()) {
                logger.debug("ProjectScheduleAction::handleResourceRequests() - Nothing missing, adding all for modify:");
                equipModList.getEquipmentReqmntsServiceModifyRequestDTO().addAll(equipReqModList.getEquipmentReqmntsServiceModifyRequestDTO());
            } else {
                for(int i = 0; i < equipReqModList.getEquipmentReqmntsServiceModifyRequestDTO().size(); ++i) {
                    if(missingEquips.contains(i)) {
                        logger.debug("ProjectScheduleAction::handleResourceRequests() - Found a missing EquipmentReqmnts, changing to create!");
                        EquipmentReqmntsServiceCreateRequestDTO createReq = new EquipmentReqmntsServiceCreateRequestDTO();
                        ObjectDataHelper.defaultData(createReq, equipReqModList.getEquipmentReqmntsServiceModifyRequestDTO().get(i), false);
                        createReq.setEnteredInd("S");
                        equipCreateList.getEquipmentReqmntsServiceCreateRequestDTO().add(createReq);
                    } else {
                        logger.debug("ProjectScheduleAction::handleResourceRequests() - Adding a EquipmentReqmnts modify!");
                        equipModList.getEquipmentReqmntsServiceModifyRequestDTO().add(equipReqModList.getEquipmentReqmntsServiceModifyRequestDTO().get(i));
                    }
                }
            }

            if(!equipCreateList.getEquipmentReqmntsServiceCreateRequestDTO().isEmpty()) {
                logger.debug("ProjectScheduleAction::handleResourceRequests() - Calling create for EquipmentReqmnts");
                EquipmentReqmntsServiceCreateReplyCollectionDTO result = equipReqService.multipleCreate(oc, equipCreateList);
                String details = "Created " + result.getReplyElements().getEquipmentReqmntsServiceCreateReplyDTO().size() + " equipment resources.";
                addResultToResponse(msg, "OK", "EquipmentReqmntsResrouce-Create", details);
                retVal.addAll(result.getReplyElements().getEquipmentReqmntsServiceCreateReplyDTO());
            }
            
            if(!equipModList.getEquipmentReqmntsServiceModifyRequestDTO().isEmpty()) {
                logger.debug("ProjectScheduleAction::handleResourceRequests() - Calling modify for EquipmentReqmnts");
                EquipmentReqmntsServiceModifyReplyCollectionDTO result = equipReqService.multipleModify(oc, equipModList);
                String details = "Modified " + result.getReplyElements().getEquipmentReqmntsServiceModifyReplyDTO().size() + " equipment resources.";
                addResultToResponse(msg, "OK", "EquipmentReqmntsResrouce-Modify", details);
                retVal.addAll(result.getReplyElements().getEquipmentReqmntsServiceModifyReplyDTO());
            }
	    }

	    if(equipReqDelList.getEquipmentReqmntsServiceDeleteRequestDTO().size() > 0) {
            logger.debug("ProjectScheduleAction::handleResourceRequests() - Calling delete for EquipmentReqmnts");
            EquipmentReqmntsServiceDeleteReplyCollectionDTO result = equipReqService.multipleDelete(oc, equipReqDelList);
            String details = "Deleted " + result.getReplyElements().getEquipmentReqmntsServiceDeleteReplyDTO().size() + " equipment resources.";
            addResultToResponse(msg, "OK", "EquipmentReqmntsResrouce-Delete", details);
            retVal.addAll(result.getReplyElements().getEquipmentReqmntsServiceDeleteReplyDTO());
	    }

        if(resReqModList.getResourceReqmntsServiceModifyRequestDTO().size() > 0) {
            logger.debug("ProjectScheduleAction::handleResourceRequests() - ResourceReqmnts Modify List:");
            ArrayOfResourceReqmntsServiceModifyRequestDTO resModList = new ArrayOfResourceReqmntsServiceModifyRequestDTO();
            ArrayOfResourceReqmntsServiceCreateRequestDTO resCreateList = new ArrayOfResourceReqmntsServiceCreateRequestDTO();

            logger.debug("ProjectScheduleAction::handleResourceRequests() - Determine missing:");
            Set<Integer> missingLabor = filterMissing(labReqService, oc, resReqModList);
            if(missingLabor.isEmpty()) {
                logger.debug("ProjectScheduleAction::handleResourceRequests() - Nothing missing, adding all for modify:");
                resModList.getResourceReqmntsServiceModifyRequestDTO().addAll(resReqModList.getResourceReqmntsServiceModifyRequestDTO());
            } else {
                for(int i=0; i < resReqModList.getResourceReqmntsServiceModifyRequestDTO().size(); ++i) {
                    if(missingLabor.contains(i)) {
                        logger.debug("ProjectScheduleAction::handleResourceRequests() - Found a missing ResourceReqmnts, changing to create!");
                        ResourceReqmntsServiceCreateRequestDTO createReq = new ResourceReqmntsServiceCreateRequestDTO();
                        ObjectDataHelper.defaultData(createReq, resReqModList.getResourceReqmntsServiceModifyRequestDTO().get(i), false);
                        createReq.setEnteredInd("S");
                        resCreateList.getResourceReqmntsServiceCreateRequestDTO().add(createReq);
                    } else {
                        logger.debug("ProjectScheduleAction::handleResourceRequests() - Adding a ResourceReqmnts modify!");
                        resModList.getResourceReqmntsServiceModifyRequestDTO().add(resReqModList.getResourceReqmntsServiceModifyRequestDTO().get(i));
                    }
                }
            }
            
            if(!resCreateList.getResourceReqmntsServiceCreateRequestDTO().isEmpty()) {
                logger.debug("ProjectScheduleAction::handleResourceRequests() - Calling create for ResourceReqmnts");
                ResourceReqmntsServiceCreateReplyCollectionDTO result = labReqService.multipleCreate(oc, resCreateList);
                String details = "Created " + result.getReplyElements().getResourceReqmntsServiceCreateReplyDTO().size() + " labor resources.";
                addResultToResponse(msg, "OK", "ResourceReqmntsResrouce-Create", details);
                retVal.addAll(result.getReplyElements().getResourceReqmntsServiceCreateReplyDTO());
            }
            
            if(!resModList.getResourceReqmntsServiceModifyRequestDTO().isEmpty()) {
                logger.debug("ProjectScheduleAction::handleResourceRequests() - Calling modify for ResourceReqmnts");
                ResourceReqmntsServiceModifyReplyCollectionDTO result = labReqService.multipleModify(oc, resModList);
                String details = "Modified " + result.getReplyElements().getResourceReqmntsServiceModifyReplyDTO().size() + " labor resources.";
                addResultToResponse(msg, "OK", "ResourceReqmntsResrouce-Modify", details);
                retVal.addAll(result.getReplyElements().getResourceReqmntsServiceModifyReplyDTO());
            }
        }

	    if(resReqDelList.getResourceReqmntsServiceDeleteRequestDTO().size() > 0) {
            logger.debug("ProjectScheduleAction::handleResourceRequests() - Calling delete for ResourceReqmnts");
            ResourceReqmntsServiceDeleteReplyCollectionDTO result = labReqService.multipleDelete(oc, resReqDelList);
            String details = "Deleted " + result.getReplyElements().getResourceReqmntsServiceDeleteReplyDTO().size() + " labour resources.";
            addResultToResponse(msg, "OK", "LabourReqmntsResrouce-Delete", details);
            retVal.addAll(result.getReplyElements().getResourceReqmntsServiceDeleteReplyDTO());
	    }
        logger.debug("ProjectScheduleAction::handleResourceRequests() - End");
	    return retVal;
	}
    
    *//**
     * This determines which equipment requirements are not already created.
     * @param equipReqService - The EquipmentReqmnts service to use. This must not be null.
     * @param oc - The operation context to use during the web service calls.
     * @param modList - Array of EquipmentReqmntsService to check.
     * @return The list of indexes that are not found in the web service.
     * @throws com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EnterpriseServiceOperationException - Error during web service call.
     *//*
    private Set<Integer> filterMissing(EquipmentReqmnts equipReqService, OperationContext oc,
            ArrayOfEquipmentReqmntsServiceModifyRequestDTO modList) throws com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EnterpriseServiceOperationException {
        Set<Integer> retVal = new HashSet<Integer>();
        logger.debug("ProjectScheduleAction::filterMissing(EquipmentReqmnts) - Begin");
        try {
            ArrayOfEquipmentReqmntsServiceReadRequestDTO requestParameters = new ArrayOfEquipmentReqmntsServiceReadRequestDTO();
            
            for(EquipmentReqmntsServiceModifyRequestDTO equip: modList.getEquipmentReqmntsServiceModifyRequestDTO()) {
                EquipmentReqmntsServiceReadRequestDTO readReq = new EquipmentReqmntsServiceReadRequestDTO();
                if (equip.getClassType().equals("JE")) {
                    readReq.setClassType(equip.getClassType());
                    readReq.setSeqNo(equip.getSeqNo());
                    readReq.setEstimateNo(equip.getEstimateNo());
                    readReq.setJEItemNo(equip.getJEItemNo());
                    readReq.setVersionNo(equip.getVersionNo());
                    requestParameters.getEquipmentReqmntsServiceReadRequestDTO().add(readReq);                	
                } else {
                    readReq.setClassType(equip.getClassType());
                    readReq.setSeqNo(equip.getSeqNo());
                    readReq.setDistrictCode(equip.getDistrictCode());
                    readReq.setWorkOrder(equip.getWorkOrder());
                    readReq.setWorkOrderTask(equip.getWorkOrderTask());
                    requestParameters.getEquipmentReqmntsServiceReadRequestDTO().add(readReq);	
                }
            }
            
            equipReqService.multipleRead(oc, requestParameters);
            
        } catch (com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EnterpriseServiceOperationException esoe) {
            if(extractError(esoe, JE_RES_REQ_MISSING_CODE) != null || extractError(esoe, WT_RES_REQ_MISSING_CODE) != null) {
                List<ErrorMessageDTO> errorList = esoe.getFaultInfo().getErrorMessages().getErrorMessageDTO();
                for(ErrorMessageDTO error: errorList) {
                    retVal.add(error.getDtoIndex());
                }
            } else {
                throw esoe;
            }
        }
        logger.debug("ProjectScheduleAction::filterMissing(EquipmentReqmnts) - End");
        return retVal;
    }
    
    *//**
     * This determines which resource (labor) requirements are not already created.
     * @param resReqService - The ResourceReqmnts service to use. This must not be null.
     * @param oc - The operation context to use during the web service calls.
     * @param modList - Array of ResourceReqmnts to check.
     * @return The list of indexes that are not found in the web service.
     * @throws com.mincom.enterpriseservice.ellipse.resourcereqmnts.EnterpriseServiceOperationException - Error during web service call.
     *//*
    private Set<Integer> filterMissing(ResourceReqmnts resReqService, OperationContext oc,
            ArrayOfResourceReqmntsServiceModifyRequestDTO modList) throws com.mincom.enterpriseservice.ellipse.resourcereqmnts.EnterpriseServiceOperationException {
        Set<Integer> retVal = new HashSet<Integer>();
        logger.debug("ProjectScheduleAction::filterMissing(ResourceReqmnts) - Begin");
        try {
            ArrayOfResourceReqmntsServiceReadRequestDTO requestParameters = new ArrayOfResourceReqmntsServiceReadRequestDTO();
            
            for(ResourceReqmntsServiceModifyRequestDTO resource: modList.getResourceReqmntsServiceModifyRequestDTO()) {
                ResourceReqmntsServiceReadRequestDTO readReq = new ResourceReqmntsServiceReadRequestDTO();
                if (resource.getClassType().equals("JE")) {
                    readReq.setClassType(resource.getClassType());
                    readReq.setSeqNo(resource.getSeqNo());
                    readReq.setEstimateNo(resource.getEstimateNo());
                    readReq.setJEItemNo(resource.getJEItemNo());
                    readReq.setVersionNo(resource.getVersionNo());
                    readReq.setResourceClass(resource.getResourceClass());
                    readReq.setResourceCode(resource.getResourceCode());
                    requestParameters.getResourceReqmntsServiceReadRequestDTO().add(readReq);                	
                } else {
                    readReq.setClassType(resource.getClassType());
                    readReq.setSeqNo(resource.getSeqNo());
                    readReq.setDistrictCode(resource.getDistrictCode());
                    readReq.setWorkOrder(resource.getWorkOrder());
                    readReq.setWorkOrderTask(resource.getWorkOrderTask());
                    readReq.setResourceClass(resource.getResourceClass());
                    readReq.setResourceCode(resource.getResourceCode());                  
                    requestParameters.getResourceReqmntsServiceReadRequestDTO().add(readReq);
                }
            }
            
            resReqService.multipleRead(oc, requestParameters);
            
        } catch (com.mincom.enterpriseservice.ellipse.resourcereqmnts.EnterpriseServiceOperationException esoe) {
            if(extractError(esoe, JE_RES_REQ_MISSING_CODE) != null || extractError(esoe, WT_RES_REQ_MISSING_CODE) != null) {
                List<ErrorMessageDTO> errorList = esoe.getFaultInfo().getErrorMessages().getErrorMessageDTO();
                for(ErrorMessageDTO error: errorList) {
                    retVal.add(error.getDtoIndex());
                }
            } else {
                throw esoe;
            }
        }
        logger.debug("ProjectScheduleAction::filterMissing(ResourceReqmnts) - End");
        return retVal;
    }
}
*/