package com.transgrid.mib.ellipse.hr.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.soa.esb.actions.ActionProcessingFaultException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import com.mincom.ellipse.service.m3001.tablecode.ArrayOfTableCodeDTO;
import com.mincom.ellipse.service.m3001.tablecode.ArrayOfTableCodeServiceResult;
import com.mincom.ellipse.service.m3001.tablecode.TableCode;
import com.mincom.ellipse.service.m3001.tablecode.TableCodeDTO;
import com.mincom.ellipse.service.m3001.tablecode.TableCodeServiceResult;
import com.mincom.enterpriseservice.ellipse.employee.Employee;
import com.mincom.enterpriseservice.ellipse.employee.EmployeeServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.employee.EmployeeServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.emppositions.EmpPositions;
import com.mincom.enterpriseservice.ellipse.emppositions.EmpPositionsServiceRetrieveReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.emppositions.EmpPositionsServiceRetrieveReplyDTO;
import com.mincom.enterpriseservice.ellipse.emppositions.EmpPositionsServiceRetrieveRequestDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchy;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceRetrieveEmpReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceRetrieveEmpReplyDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceRetrieveEmpRequestDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceRetrieveReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceRetrieveReplyDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceRetrieveRequestDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceRetrieveTreeReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceRetrieveTreeReplyDTO;
import com.mincom.enterpriseservice.ellipse.posnhierarchy.PosnHierarchyServiceRetrieveTreeRequestDTO;
import com.mincom.enterpriseservice.ellipse.table.Table;
import com.mincom.enterpriseservice.ellipse.table.TableServiceRetrieveReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.table.TableServiceRetrieveReplyDTO;
import com.mincom.enterpriseservice.ellipse.table.TableServiceRetrieveRequestDTO;
import com.mincom.enterpriseservice.ellipse.table.TableServiceRetrieveRequiredAttributesDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrder;

import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;
import com.mincom.mib.db.JDBCUtilities;
import com.mincom.mib.ellipse.EllipseBaseAction;
import com.transgrid.mib.ellipse.hr.common.AATInterfaceException;
import com.transgrid.mib.ellipse.hr.common.CommonUtil;
import com.transgrid.mib.ellipse.hr.common.EllipseTopicClient;
import com.transgrid.mib.ellipse.hr.domain.EllipseEmployee;
import com.transgrid.mib.ellipse.hr.domain.EllipseEmployeeLeave;

/**
 * Employee Action class
 * 
 * @author Anil Kanike
 * 
 */
public class EmployeeAction extends EllipseBaseAction {
	protected static final String EMPLOYEE_EXIST_CODE = "mims.e.0673";
	protected static final String EMPLOYEE_NOT_EXIST_CODE = "mims.e.0609";
	private static final BigDecimal depth = new BigDecimal(1);
	protected boolean updateIfExists;
	protected String employeeBeanID;
	protected String employeeAttrBeanID;
	protected String employeeBeanResultID;
	protected String employeePositionBeanID;
	protected String employeePositionBeanResultID;
	private Employee employeeService = null;
	private EmpPositions empPositionsService = null;
	private PosnHierarchy posiHierService = null;
	private TableCode tableCodeService = null;
	private Table tableService = null;
	private OperationContext context = null;
	private String operationName = "";
	String terminatedPositionId;
	//private static Map<String, String> tableDesc = null;

	public EmployeeAction(ConfigTree config) {
		super(config);
		// terminatedPositionId = config.getAttribute("terminated.position.id");
		terminatedPositionId = System.getProperty("terminated.position.id");

		employeeBeanID = config.getAttribute(REQUEST_BEAN_ID, "employee");
		employeeBeanResultID = config.getAttribute(RESULT_BEAN_ID, "employeeResults");

		employeePositionBeanID = config.getAttribute(REQUEST_BEAN_ID, "employeePosition");
		employeePositionBeanResultID = config.getAttribute(RESULT_BEAN_ID, "employeePositionResults");
	}

	// @SuppressWarnings("unchecked")
	public Message processEmp(Message message) throws ActionProcessingFaultException {
		logger.info("EmployeeAction.processEmp...");

		operationName = "Employee Details";
		try {
			EllipseEmployee employee = new EllipseEmployee();
			EWSClientConversation conversation = getConversation(message);
			employeeService = createService(conversation, Employee.class);
			// service to get the Employee End Date
			empPositionsService = createService(conversation, EmpPositions.class);
			posiHierService = createService(conversation, PosnHierarchy.class);
			tableCodeService = createService(conversation, TableCode.class);
			//WorkOrder workOrderService = createService(conversation, WorkOrder.class);
			context = getOperationContext(message);

			EmployeeServiceReadRequestDTO readDTO = (EmployeeServiceReadRequestDTO) getDTOObject(message, employeeBeanID);
			
			if (readDTO != null && StringUtils.isNotEmpty(readDTO.getEmployee())) {
				//long startTime = System.currentTimeMillis(); // Get the start Time
				employee = getEmployee(readDTO.getEmployee(), employee);
				//long endTime = System.currentTimeMillis(); // Get the end Time
				//logger.info("Time taken to process an employee in seconds: {}",(endTime-startTime)/1000);
				addResultToResponse(message, "OK", "read employee details", "Employee id is : " + employee.getId());
				storeReplyDTO(message, employeeBeanResultID, employee);
			} else {
				addResultToResponse(message, "WARN", "Employee Details", "Event message has no employee id, hence can not be processed.");
			}

			logger.info("ELLIPSE EMPLOYEE DETAILS: " + employee);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, operationName, e);
		}
		return message;

	}

	public Message processEmpPosition(Message message) throws ActionProcessingFaultException {
		logger.info("EmployeeAction.processeEmpPosition() - Begin");
		operationName = "Employee & Position Details";
		try {
			EWSClientConversation conversation = getConversation(message);
			employeeService = createService(conversation, Employee.class);
			posiHierService = createService(conversation, PosnHierarchy.class);
			tableCodeService = createService(conversation, TableCode.class);
			empPositionsService = createService(conversation, EmpPositions.class);
			context = getOperationContext(message);

			EllipseEmployee empPosition = (EllipseEmployee) getDTOObject(message, employeePositionBeanID);

			if (empPosition != null) {
				/*
				 * Skip retrieval of Position subordinates when the PositionID
				 * is TERM1, since there may be thousands of terminated
				 * employees and takes lots of time to process in ESB layer
				 */
				logger.debug("Employee Position ID is {} ", empPosition.getPositionId());
				if (StringUtils.equalsIgnoreCase(empPosition.getPositionId().trim(), terminatedPositionId)) {
					addResultToResponse(message, "WARN", "Employee & Position Details", "Position ID is Terminated, hence can not be processed.");
				} else {
					if (StringUtils.isNotEmpty(empPosition.getPositionId())) {
						getSubordinates(empPosition.getPositionId(), empPosition);
						addResultToResponse(message, "WARN", "read employee position deatils", "Position id is : " + empPosition.getPositionId());
						// storeReplyDTO(message, employeePositionBeanResultID, listEmps);
					} else {
						addResultToResponse(message, "WARN", "Employee & Position Details", "Event message has no Position id, hence can not be processed.");
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, operationName, e);
		}
		logger.info("EmployeeAction.processeEmpPosition() - End");
		return message;

	}

	/**
	 * method to process personnel leave request message
	 * 
	 * @param message
	 * @return
	 * @throws ActionProcessingFaultException
	 */
	public Message processPersonnelLeave(Message message) throws ActionProcessingFaultException {

		logger.info("Entering to EmployeeAction.processPersonnelLeave");
		try {

			EllipseEmployeeLeave empLeave = (EllipseEmployeeLeave) getDTOObject(message, config.getAttribute(REQUEST_BEAN_ID, "personnelLeave"));

			empLeave.setLeaveEndTime(CommonUtil.formatTime(empLeave.getLeaveEndTime()));
			empLeave.setLeaveStartTime(CommonUtil.formatTime(empLeave.getLeaveStartTime()));
			empLeave.setReqApprovedTime(CommonUtil.formatTime(empLeave.getReqApprovedTime()));
			empLeave.setReqCreatedTime(CommonUtil.formatTime(empLeave.getReqCreatedTime()));

			addResultToResponse(message, "OK", "read personnel leave", "Personnel Leave for the ID : " + empLeave.getEmpId());
			storeReplyDTO(message, config.getAttribute(RESULT_BEAN_ID, "personnelLeaveResults"), empLeave);
			logger.info("EMPLOYEE LEAVE : {}", empLeave);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, "GetPersonnelLeave", e);
		}

		return message;
	}

	protected EllipseEmployee getEmployee(String empId, EllipseEmployee employee) throws Exception {
		logger.info("Employee id is : " + empId);

		EmployeeServiceReadReplyDTO readReplyDTO = null;
		try {
			EmployeeServiceReadRequestDTO readDTO = new EmployeeServiceReadRequestDTO();
			readDTO.setEmployee(empId);
			// Thread.sleep(5000);
			readReplyDTO = employeeService.read(context, readDTO);

		} catch (Exception e) {
			if (extractError(e, EMPLOYEE_NOT_EXIST_CODE) != null) {
				logger.debug("Employee {} does not exists in Ellipse", empId);
				throw new Exception("Employee does not exists with ID: " + empId);
			} else {
				throw e;
			}
		}

		employee.setId(readReplyDTO.getEmployee());
		employee.setFirstName(readReplyDTO.getFirstName());
		employee.setLastName(readReplyDTO.getLastName());
		employee.setEmailAddr(readReplyDTO.getEmailAddress());
		employee.setWorkPhone(readReplyDTO.getWorkTelephoneNumber());
		employee.setLocationId(readReplyDTO.getPhysicalLocation());
		employee.setLocationDesc(readReplyDTO.getPhysicalLocationDesc());
		employee.setStaffCategoryCode(readReplyDTO.getStaffCategory());
		employee.setStaffCategoryDesc(readReplyDTO.getStaffCategoryDesc());
		employee.setPositionId(readReplyDTO.getPosition());
		employee.setPositionTitle(readReplyDTO.getPositionDesc());
		employee.setStartDate(readReplyDTO.getPositionStartDate());
		employee.setStatusCode(readReplyDTO.getPersEmpStatus());
		employee.setStatusDesc(readReplyDTO.getPersEmpStatusDesc());
		employee.setPayrollInd(readReplyDTO.isPayrollEmployeeInd());
		employee.setPhotoPath(readReplyDTO.getPhotoPathname());
		// get employee PRC code
		String primRepCode = readReplyDTO.getPrimRepCode();
		logger.debug("Primary Reporting Code is {} ", primRepCode);
		employee.setPrimRepCode(primRepCode);
		if (StringUtils.isNotEmpty(primRepCode)) {

			ArrayOfTableCodeServiceResult readResultDTO = getMultipleTableCodes(primRepCode);
			for (TableCodeServiceResult result : readResultDTO.getTableCodeServiceResult()) {
				TableCodeDTO codeDTO = result.getTableCodeDTO();
				if (codeDTO != null) {
					if (StringUtils.equals(codeDTO.getTableType(), "PC01")) {
						employee.setOrgDesc(codeDTO.getTableDescription());
					} else if (StringUtils.equals(codeDTO.getTableType(), "PC02")) {
						employee.setBusinessUnitDesc(codeDTO.getTableDescription());
					}
					if (StringUtils.equals(codeDTO.getTableType(), "PC03")) {
						employee.setGroupDesc(codeDTO.getTableDescription());
					}
					if (StringUtils.equals(codeDTO.getTableType(), "PC04")) {
						employee.setBranchDesc(codeDTO.getTableDescription());
					}
				}
			}
			
			/*employee.setOrgDesc(tableDesc.get("PC01"+primRepCode.substring(0, 4).trim()));
			employee.setBusinessUnitDesc(tableDesc.get("PC02"+primRepCode.substring(4, 8).trim()));
			employee.setGroupDesc(tableDesc.get("PC03"+primRepCode.substring(8, 12).trim()));
			employee.setBranchDesc(tableDesc.get("PC04"+primRepCode.substring(12, 16).trim()));	*/		
		}

		// service call to get employee end date
		try {
			logger.debug("Get Employee {} End Date for the position {} ", empId, employee.getPositionId());
			EmpPositionsServiceRetrieveRequestDTO retDTO = new EmpPositionsServiceRetrieveRequestDTO();
			retDTO.setEmployee(empId);
			retDTO.setCurrentPositionInd(true);

			EmpPositionsServiceRetrieveReplyCollectionDTO replyDTO = empPositionsService.retrieve(context, retDTO, null, null);
			int size = replyDTO.getReplyElements().getEmpPositionsServiceRetrieveReplyDTO().size();
			if(replyDTO != null && size>0){
				employee.setEndDate(replyDTO.getReplyElements().getEmpPositionsServiceRetrieveReplyDTO().get(0).getEndDate());
			}
		} catch (Exception e) {
			logger.error("Failed to get the Employee End Date.");
			throw e; 
		}

		// get supervisor details
		if (StringUtils.isNotEmpty(employee.getPositionId())) {
			getPositionSuperior(employee.getPositionId(), employee);
		}

		return employee;
	}

	/**
	 * method to get position superior details for the employee
	 * 
	 * @param positionId
	 * @param tgEmp
	 * @return
	 * @throws Exception
	 */
	protected EllipseEmployee getPositionSuperior(String positionId, EllipseEmployee employee) throws Exception {

		PosnHierarchyServiceReadRequestDTO phReadDTO = new PosnHierarchyServiceReadRequestDTO();
		phReadDTO.setPositionId(positionId);
		phReadDTO.setHierVer("001");
		phReadDTO.setHierarchyType("C");
		PosnHierarchyServiceReadReplyDTO phReplyDTO = posiHierService.read(context, phReadDTO);

		if (phReplyDTO != null) {
			employee.setSuperPositionId(phReplyDTO.getSuperiorId());
			employee.setSuperPositionTitle(phReplyDTO.getSuperTitle());
		}

		return employee;
	}

	protected void getSubordinates(String positionId, EllipseEmployee employee) throws Exception {
		logger.info("EmployeeAction.getSubordinates() - Begin");
		// get the current position hierarchy details
		PosnHierarchyServiceRetrieveRequestDTO retrieveDTO = new PosnHierarchyServiceRetrieveRequestDTO();
		retrieveDTO.setCurrHierInd(true);
		retrieveDTO.setSearchMethod("A");
		PosnHierarchyServiceRetrieveReplyCollectionDTO replyColDTO = posiHierService.retrieve(context, retrieveDTO, null, null);
		PosnHierarchyServiceRetrieveReplyDTO replyDTO = replyColDTO.getReplyElements().getPosnHierarchyServiceRetrieveReplyDTO().get(0);

		logger.debug("Current Position Hierarchy Type {} and Version {}", replyDTO.getCurrHierType(), replyDTO.getCurrHierVer());

		// get list of subordinate positions
		operationName = "EmployeeAction.getSubordinates - retrieveTree";

		PosnHierarchyServiceRetrieveTreeRequestDTO retrieveTreeDTO = new PosnHierarchyServiceRetrieveTreeRequestDTO();
		retrieveTreeDTO.setDepth(depth);
		retrieveTreeDTO.setHierarchyType(replyDTO.getCurrHierType());
		retrieveTreeDTO.setHierVer(replyDTO.getCurrHierVer());
		retrieveTreeDTO.setPositionId(positionId);

		// Restart info here!
		String restartPoint = null;
		boolean moreData = true;
		int batchCount = 0;
		ArrayList<EllipseEmployee> employees = new ArrayList<EllipseEmployee>();

		while (moreData) {
			++batchCount;
			logger.debug("Call retrieveTree...");

			PosnHierarchyServiceRetrieveTreeReplyCollectionDTO batchResult = posiHierService.retrieveTree(context, retrieveTreeDTO, null, restartPoint);
			inspectReplyForErrors(batchResult, "EmployeeAction.getSubordinates");
			int readCount = batchResult.getReplyElements().getPosnHierarchyServiceRetrieveTreeReplyDTO().size();
			logger.debug("EmployeeAction.getSubordinates - search batch count {}, returned {}", batchCount, readCount);

			if (readCount < context.getMaxInstances()) {
				moreData = false;
			} else {
				restartPoint = batchResult.getCollectionRestartPoint();
			}

			if (readCount > 0) {
				for (PosnHierarchyServiceRetrieveTreeReplyDTO reply : batchResult.getReplyElements().getPosnHierarchyServiceRetrieveTreeReplyDTO()) {
					getIncumbents(reply.getPositionId());
				}
			} else {
				getIncumbents(positionId);
			}
		}
		logger.debug("Retrieved employees for position ID {} is {}: ", positionId, employees.size());

		logger.info("EmployeeAction.getSubordinates() - End");
		// return employees;
	}

	protected void getIncumbents(String positionId) throws Exception {
		logger.info("EmployeeAction.getIncumbents() - Begin");
		//ArrayList<EllipseEmployee> employees = new ArrayList<EllipseEmployee>();

		PosnHierarchyServiceRetrieveEmpRequestDTO retrieveEmpDTO = new PosnHierarchyServiceRetrieveEmpRequestDTO();
		retrieveEmpDTO.setCurrOrHist("C");
		retrieveEmpDTO.setPositionId(positionId);

		// Restart info here!
		String restartPoint = null;
		boolean moreData = true;
		int batchCount = 0;

		while (moreData) {
			++batchCount;
			logger.debug("Call retrieveEmp...");

			PosnHierarchyServiceRetrieveEmpReplyCollectionDTO batchResult = posiHierService.retrieveEmp(context, retrieveEmpDTO, null, restartPoint);
			inspectReplyForErrors(batchResult, "EmployeeAction.getIncumbents");
			int readCount = batchResult.getReplyElements().getPosnHierarchyServiceRetrieveEmpReplyDTO().size();
			logger.debug("EmployeeAction.getIncumbents - search batch count {}, returned {}", batchCount, readCount);

			if (readCount < context.getMaxInstances()) {
				moreData = false;
			} else {
				restartPoint = batchResult.getCollectionRestartPoint();
			}

			for (PosnHierarchyServiceRetrieveEmpReplyDTO reply : batchResult.getReplyElements().getPosnHierarchyServiceRetrieveEmpReplyDTO()) {
				if (reply != null) {
					logger.debug("Employee ID {} for retrieveing the details.. ", reply.getEmployeeId());
					writeToTopic(reply.getEmployeeId());
					/*
					 * EllipseEmployee employee = new EllipseEmployee();
					 * getEmployee(reply.getEmployeeId(), employee);
					 * employees.add(employee);
					 */
				}
			}
		}

		logger.info("EmployeeAction.getIncumbents() - End");
		// return employees;
	}

	/**
	 * method to send the custom ellipse employee event message to topic/EllipseServices topic
	 * @param empId
	 * @throws Exception
	 */
	private void writeToTopic(String empId) throws Exception {
		logger.info("Writing message to ellipse topic for employee Id {} ",empId);
		EllipseTopicClient ellipseTopic = null;

		if (ellipseTopic == null) {
			ellipseTopic = new EllipseTopicClient();
		}

		ellipseTopic.setupConnection();
		ellipseTopic.sendMessage(buildEventMsg(empId));
		ellipseTopic.close();
	}

	/**
	 * build event message for publishing on to Ellipse topic
	 * @param empId
	 * @return
	 */
	private String buildEventMsg(String empId) {
		StringBuilder sb = new StringBuilder();
		sb.append("<com.mincom.ellipse.edoi.ejb.msf810.MSF810Rec>").append("<primaryKey>").append("<employeeId>" + empId + "</employeeId>")
				.append("</primaryKey>").append("</com.mincom.ellipse.edoi.ejb.msf810.MSF810Rec>");
		return sb.toString();
	}

	/**
	 * method to get the single table code description.
	 * 
	 * @param tableCode
	 * @param tableType
	 * @return String
	 * @throws Exception
	 */
	@Deprecated
	protected String getTableCode(String tableCode, String tableType) throws Exception {
		logger.info("Table Code {} and Table Type {} ", tableCode, tableType);
		if (StringUtils.isEmpty(tableType) || StringUtils.isEmpty(tableCode)) {
			throw new AATInterfaceException("Table Code and Table Type should not be empty.");
		}
		TableCodeDTO codeDTO = new TableCodeDTO();
		codeDTO.setTableCode(tableCode);
		codeDTO.setTableType(tableType);

		return tableCodeService.read(context, codeDTO).getTableCodeDTO().getTableDescription();

	}

	/**
	 * method to get multiple table code description.
	 * 
	 * @param primRepCode
	 * @return PositionPRC
	 * @throws Exception
	 */	
	protected ArrayOfTableCodeServiceResult getMultipleTableCodes(String primRepCode) throws Exception {
		logger.info("EmployeeAction.getMultipleTableCodes() - Begin");
		List<TableCodeDTO> codeDTOs = new ArrayList<TableCodeDTO>();
		ArrayOfTableCodeDTO tableCodes = new ArrayOfTableCodeDTO();

		int startIndx = 0;
		int endIndx = 4;
		for (int i = 1; i <= 4; i++) {
			TableCodeDTO codeDTO = new TableCodeDTO();
			codeDTO.setTableCode(primRepCode.substring(startIndx, endIndx * i));
			codeDTO.setTableType("PC0" + i);
			codeDTOs.add(codeDTO);
			startIndx = endIndx * i;
		}

		tableCodes.getTableCodeDTO().addAll(codeDTOs);
		ArrayOfTableCodeServiceResult readResultDTO = tableCodeService.multipleRead(context, tableCodes);
		logger.info("EmployeeAction.getMultipleTableCodes() - End");

		return readResultDTO;
	}
	/**
	 * Caching table code data for table type PC01, PC02, PC03 and PC04
	 * @param tableDesc
	 * @param conversation
	 * @throws Exception
	 */
	@Deprecated
	private void loadTableCodes(Map<String, String> tableDesc, EWSClientConversation conversation) throws Exception {
		logger.info("Calling loadTableCodes() to load table code description for type PC01..04");
		tableService = createService(conversation, Table.class);
		
		for (int i = 1; i <= 4; i++) {
			TableServiceRetrieveRequestDTO retDTO = new TableServiceRetrieveRequestDTO();
			retDTO.setTableType("PC0" + i);
			
			String restartInfo = null;
			boolean moreData = true;
			int batchCount = 0;
			while (moreData) {
				
				TableServiceRetrieveReplyCollectionDTO replyDTO = tableService.retrieve(context, retDTO, null, restartInfo);
				int readCount = replyDTO.getReplyElements().getTableServiceRetrieveReplyDTO().size();
				
				if (readCount < context.getMaxInstances()) {
					moreData = false;
				} else {
					restartInfo = replyDTO.getCollectionRestartPoint();
					++batchCount;
				}
				
				List<TableServiceRetrieveReplyDTO> replyList = replyDTO.getReplyElements().getTableServiceRetrieveReplyDTO();
				for(TableServiceRetrieveReplyDTO tableDto : replyList){
					tableDesc.put(tableDto.getTableType().trim()+tableDto.getTableCode().trim(), tableDto.getDescription());
				}
			}
		}
		logger.info("Size of table code description for PC01..04 is {} ",tableDesc.size());
	}
}