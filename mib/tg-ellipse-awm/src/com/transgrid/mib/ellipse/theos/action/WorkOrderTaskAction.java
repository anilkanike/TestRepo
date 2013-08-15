package com.transgrid.mib.ellipse.theos.action;

import org.jboss.soa.esb.actions.ActionProcessingFaultException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import com.mincom.ellipse.service.m3001.tablecode.TableCode;
import com.mincom.ellipse.service.m3001.tablecode.TableCodeDTO;
import com.mincom.ellipse.service.m3001.tablecode.TableCodeServiceResult;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTask;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceReadRequestDTO;
import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;
import com.mincom.mib.common.utils.ObjectDataHelper;
import com.mincom.mib.ellipse.EllipseBaseAction;
import com.transgrid.mib.ellipse.theos.domain.WorkOrderTaskWrapperDTO;

/**
 * This class implements the custom logic for Work order tasks.
 */
public class WorkOrderTaskAction extends EllipseBaseAction {

	protected String taskRequestDTOName;
	protected String taskResultDTOName;

	public WorkOrderTaskAction(ConfigTree config) {
		super(config);
		taskRequestDTOName = config.getAttribute(REQUEST_BEAN_ID, "workOrderTaskRequest");
		taskResultDTOName = config.getAttribute(RESULT_BEAN_ID, "workOrderResults");
	}

	public Message get(Message message) throws ActionProcessingFaultException {
		logger.info("WorkOrderTaskAction.get()...");
		try {
			EWSClientConversation conversation = getConversation(message);
			OperationContext oc = getOperationContext(message);
			WorkOrderTask workOrderTaskService = createService(conversation, WorkOrderTask.class);

			WorkOrderTaskWrapperDTO wrapperDTO = (WorkOrderTaskWrapperDTO) getDTOObject(message, taskRequestDTOName);

			WorkOrderTaskServiceReadRequestDTO readRequestDTO = new WorkOrderTaskServiceReadRequestDTO();
			readRequestDTO.setDistrictCode(wrapperDTO.getDistrictCode());
			readRequestDTO.setWOTaskNo(wrapperDTO.getWorkOrderTask());

			WorkOrderDTO workOrderDTO = new WorkOrderDTO();
			workOrderDTO.setPrefix(wrapperDTO.getWorkOrder().substring(0, 2));
			workOrderDTO.setNo(wrapperDTO.getWorkOrder().substring(2, wrapperDTO.getWorkOrder().trim().length()));
			readRequestDTO.setWorkOrder(workOrderDTO);

			WorkOrderTaskServiceReadReplyDTO readReplyDTO = workOrderTaskService.read(oc, readRequestDTO);
			/**
			 * If must start indicator is 'E', send the work order task to THEOS
			 */
			logger.info("WorkOrder no {} and task {} ",workOrderDTO.getNo(),wrapperDTO.getWorkOrderTask());
			logger.info("Must Start indicator is {} ",readReplyDTO.getMustStartInd());
			
			if (readReplyDTO.getMustStartInd().equals("E")) {
				addResultToResponse(message, "OK", "WorkOrderTaskAction.get", "Found 1 work order " + wrapperDTO.getWorkOrder() + " task " + wrapperDTO.getWorkOrderTask());
				storeReplyDTO(message, taskResultDTOName, wrapperDTO);
				//return null;
			} else {
				TableCode tableCodeService = createService(conversation, TableCode.class);
				TableCodeDTO tableCodeRequestDTO = new TableCodeDTO();
				tableCodeRequestDTO.setTableType("#TSL");
				tableCodeRequestDTO.setTableCode(readReplyDTO.getSafetyInstr().trim());
				
				TableCodeServiceResult serviceResult = tableCodeService.read(oc, tableCodeRequestDTO);
				logger.info("Table type {} and table code {}",tableCodeRequestDTO.getTableType(), tableCodeRequestDTO.getTableCode());
				/**
				 * If the list of errors is greater than zero, then the table code does not exist. 
				 * Hence we send the work order task to THEOS.
				 */
				if (serviceResult.getErrors().getError().size() > 0) {
					addResultToResponse(message, "OK", "WorkOrderTaskAction.get", "Found 1 work order " + wrapperDTO.getWorkOrder() + " task " + wrapperDTO.getWorkOrderTask());
					storeReplyDTO(message, taskResultDTOName, wrapperDTO);
				} else {
					/**
					 * If the table code exists, then we update the must start indicator with a value of 'E'.
					 * This should trigger an ellipse event on MSF623 again and we will send the work order task to THEOS.
					 */
					logger.info("Table code {} for #TSL is existed and updating MustStart indicator to E",readReplyDTO.getSafetyInstr());
					WorkOrderTaskServiceModifyRequestDTO modifyRequestDTO = new WorkOrderTaskServiceModifyRequestDTO();
					//ObjectDataHelper.defaultData(modifyRequestDTO, readReplyDTO, false);  
					modifyRequestDTO.setMustStartInd("E");
					WorkOrderDTO woDTO = new WorkOrderDTO();
	                woDTO.setNo(readReplyDTO.getWorkOrder().getNo());
	                woDTO.setPrefix(readReplyDTO.getWorkOrder().getPrefix());
					modifyRequestDTO.setWorkOrder(woDTO);
					modifyRequestDTO.setWOTaskNo(readReplyDTO.getWOTaskNo());
					workOrderTaskService.modify(oc, modifyRequestDTO);
					//return null;
				}
			}
			//logger.info("Work Order Task Get returned a total of 1");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, "getWorkOrderTask", e);
		}
		return message;
	}

}
