package com.transgrid.mib.ellipse.hr.action;

import org.apache.commons.lang.StringUtils;
import org.jboss.soa.esb.actions.ActionProcessingFaultException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import com.mincom.enterpriseservice.ellipse.employee.Employee;
import com.mincom.enterpriseservice.ellipse.employee.EmployeeServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.employee.EmployeeServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.table.Table;
import com.mincom.enterpriseservice.ellipse.table.TableServiceReadRequestDTO;
import com.mincom.enterpriseservice.screen.Screen;
import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;
import com.mincom.mib.common.utils.DateHelpers;
import com.mincom.mib.ellipse.EllipseBaseAction;
import com.transgrid.mib.ellipse.hr.common.AATConstants;
import com.transgrid.mib.ellipse.hr.common.CommonUtil;
import com.transgrid.mib.ellipse.hr.domain.EllipseResource;
import com.transgrid.mib.ellipse.screenservice.MSO765;
import com.transgrid.mib.ellipse.screenservice.MSO812;
/**
 * 
 * @author Anil Kanike
 *
 */
public class ResourceAction extends EllipseBaseAction {
	protected static final String EMPLOYEE_NOT_EXIST_CODE = "mims.e.0609";
	protected ConfigTree configuration;
	private Table tableService = null;
	protected String resourceDTOName;
	protected String resultDTOName;
	private OperationContext oc = null;
	private Employee employeeService = null;
	private String msgType = "";

	public ResourceAction(ConfigTree config) {
		super(config);
		configuration = config;
		resourceDTOName = config.getAttribute(REQUEST_BEAN_ID, "resource");
		resultDTOName = config.getAttribute(RESULT_BEAN_ID, "resourceResults");
	}

	public Message process(Message message) throws ActionProcessingFaultException {
		logger.info("ResourceAction.process...");

		try {
			EWSClientConversation conversation = getConversation(message);
			oc = this.getOperationContext(message);
			tableService = createService(conversation, Table.class);
			employeeService = createService(conversation, Employee.class);

			EllipseResource resource = (EllipseResource) getDTOObject(message, resourceDTOName);
			msgType = (String)message.getProperties().getProperty("type");
			
			resource = getResourceData(resource);
			if (resource != null) {
				addResultToResponse(message, "OK", "read resource details", "Resource employee id is : " + resource.getId());
				storeReplyDTO(message, resultDTOName, resource);
			} 

			logger.info("PEOPLE RESOURCE: " + resource);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, "GetPeopleResource", e);
		}
		return message;
	}

	/**
	 * method to handle aat-ellipse inbound interface for sync resource details
	 * 
	 * @param message
	 * @return
	 * @throws ActionProcessingFaultException
	 */
	public Message sync(Message message) throws ActionProcessingFaultException {
		logger.info("ResourceAction.sync...");

		try {
			EWSClientConversation conversation = getConversation(message);
			OperationContext oc = getOperationContext(message);			
			Screen screenService = createService(conversation, Screen.class);

			EllipseResource resource = (EllipseResource) getDTOObject(message, resourceDTOName);

			String empType = resource.getEmpNonEmpIndicator();

			// logger.info("ResourceCode: {} ResourceClass: {} for the employeeID : {}",resource.getResourceCode(),resource.getResourceClass(),resource.getId());
			logger.debug("ResourceCode: {} ResourceClass: {}", resource.getResourceCode(), resource.getResourceClass());

			if (StringUtils.equals(empType, AATConstants.EMPLOYEE)) {
				logger.debug("Sync employee resource for id {} is started.. ",resource.getId());
				MSO765 resScreen = new MSO765(screenService, oc, resource);
				resScreen.exec();
				addResultToResponse(message, "OK", "Sync Employee Resource deatils", "Updated resource employee id is : " + resource.getId());
				//storeReplyDTO(message, resultDTOName, resource);
			} else if (StringUtils.equals(empType, AATConstants.NON_EMPLOYEE)) {
				logger.debug("Sync non-employee resource for id {} is started.. ",resource.getId());				
				MSO812 resScreen = new MSO812(screenService, oc, resource);
				resScreen.exec();
				addResultToResponse(message, "OK", "Sync NonEmployee Resource deatils", "Updated resource non-employee id is : " + resource.getId());
			}
			// storeReplyDTO(message, resultDTOName, resource);
			// System.out.println("ADD/UPDATE PEOPLE RESOURCE: ");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, "SyncPeopleResource", e);
		}
		return message;
	}

	private EllipseResource getResourceData(EllipseResource resource) throws Exception {

		/*String resInd = resource.getEmpResourceInd().trim();
		if (StringUtils.equals(resInd, "H")) {
			return null;
		}*/
		
		String resourceTy = resource.getResourceType();
		resource.setResourceClass(StringUtils.substring(resourceTy, 0, 1));
		resource.setResourceCode(StringUtils.substring(resourceTy, 1));

		resource.setResourceTypeDesc(getTableCodeDesc(resourceTy, "TT"));

		//String resEffeDate = resource.getEffectiveDate();
		//reverse data value only for Employee
		if (!StringUtils.equals(resource.getEmpNonEmpIndicator(), "N")) {
			/*if (StringUtils.isNotEmpty(gainedDate)) {
				gainedDate = DateHelpers.reverseDate(gainedDate);
			}*/
			
			EmployeeServiceReadReplyDTO readReplyDTO = null;
			try {
				EmployeeServiceReadRequestDTO readDTO = new EmployeeServiceReadRequestDTO();
				readDTO.setEmployee(resource.getId());
				readReplyDTO = employeeService.read(oc, readDTO);
				resource.setEmpPayrollInd(readReplyDTO.isPayrollEmployeeInd());

			} catch (Exception e) {
				if (extractError(e, EMPLOYEE_NOT_EXIST_CODE) != null) {
					logger.debug("Employee {} does not exists in Ellipse", resource.getId());
					throw new Exception("Employee does not exists with ID: " + resource.getId());
				} else {
					throw e;
				}
			}			
		}else{
			resource.setEmpPayrollInd(false);
		}
		
		resource.setGainedDate(CommonUtil.validateEmptyDate(resource.getGainedDate()));
		resource.setExpiryDate(CommonUtil.validateEmptyDate(resource.getExpiryDate()));
		//msgtype is derived from the ellipse event message.
		logger.debug("Message event type is : {}",msgType);
		resource.setTypeDelete("delete".equals(msgType));
		
		return resource;
	}

	protected String getTableCodeDesc(String tableCode, String tableType) throws Exception {
		logger.info("TableCode {} and TableType {} " + tableCode, tableType);
		TableServiceReadRequestDTO readDTO = new TableServiceReadRequestDTO();
		readDTO.setTableCode(tableCode);
		readDTO.setTableType(tableType);
		return tableService.read(oc, readDTO).getCodeDescription();
	}

}
