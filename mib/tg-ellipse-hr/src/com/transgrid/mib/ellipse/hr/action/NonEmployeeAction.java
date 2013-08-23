package com.transgrid.mib.ellipse.hr.action;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jboss.soa.esb.actions.ActionProcessingFaultException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import com.mincom.enterpriseservice.ellipse.nonemployee.NonEmployee;
import com.mincom.enterpriseservice.ellipse.nonemployee.NonEmployeeServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.nonemployee.NonEmployeeServiceReadRequestDTO;
import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;
import com.mincom.mib.ellipse.EllipseBaseAction;
import com.transgrid.mib.ellipse.hr.common.AATConstants;
import com.transgrid.mib.ellipse.hr.common.CommonUtil;
import com.transgrid.mib.ellipse.hr.domain.EllipseNonEmployee;

/**
 * 
 * @author Anil Kanike
 * 
 */
public class NonEmployeeAction extends EllipseBaseAction {
	protected ConfigTree configuration;
	protected String nonEmpDTOName;
	protected String resultDTOName;
	
	public NonEmployeeAction(ConfigTree config) {
		super(config);
		configuration = config;
		nonEmpDTOName = config.getAttribute(REQUEST_BEAN_ID, "nonEmployee");
		resultDTOName = config.getAttribute(RESULT_BEAN_ID, "nonEmployeeResults");
	}

	public Message process(Message message) throws ActionProcessingFaultException {

		logger.info("Entering to NonEmployeeAction.process");
		try {
			
			EllipseNonEmployee nonEmployee = (EllipseNonEmployee) getDTOObject(message, nonEmpDTOName);
			
			// write any validations if required
			validate(nonEmployee);
			//get non employee supplier details
			getSupplierData(message, nonEmployee);
			
			addResultToResponse(message, "OK", "read Non-Employee", "Non Employee Details for the ID : "+nonEmployee.getId());
			storeReplyDTO(message, resultDTOName, nonEmployee);
			logger.info("NON-EMPLOYEE: {}",nonEmployee);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, "GetNonEmployee", e);
		}

		return message;
	}
	
	private void validate(EllipseNonEmployee nonEmployee) throws Exception {
		boolean isValidDate = true;
		
		if(StringUtils.isNotEmpty(nonEmployee.getStartDate())){
			isValidDate = CommonUtil.isValidDateFormat(nonEmployee.getStartDate());
			if(!isValidDate)
				throw new Exception("non-employee start contract date must be in 'yyyyMMdd' format.");
		} 
		if(StringUtils.isNotEmpty(nonEmployee.getEndDate())){
			isValidDate = CommonUtil.isValidDateFormat(nonEmployee.getEndDate());
			if(!isValidDate)
				throw new Exception("non-employee end contract date must be in 'yyyyMMdd' format.");
		}
		//set non-employee status based on Start & End dates
		if(StringUtils.isNotEmpty(nonEmployee.getStartDate()) ){
			long days = CommonUtil.calculateDays(new Date(), CommonUtil.ellDateFormat.parse(nonEmployee.getStartDate()));
			if(days > 0)
				nonEmployee.setStatus(AATConstants.INACTIVE_STATUS);
			else
				nonEmployee.setStatus(AATConstants.ACTIVE_STATUS);			
		}
		
		if(StringUtils.isEmpty(nonEmployee.getStartDate()) && StringUtils.isEmpty(nonEmployee.getEndDate())){
			nonEmployee.setStatus(AATConstants.ACTIVE_STATUS);			
		} 

		if( (StringUtils.isNotEmpty(nonEmployee.getStartDate()) && StringUtils.isNotEmpty(nonEmployee.getEndDate())) ||
				(StringUtils.isEmpty(nonEmployee.getStartDate()) && StringUtils.isNotEmpty(nonEmployee.getEndDate())) ){
			long days = CommonUtil.calculateDays(new Date(), CommonUtil.ellDateFormat.parse(nonEmployee.getEndDate()));
			if(days > 0 || isDateEqual(nonEmployee.getEndDate()))
				nonEmployee.setStatus(AATConstants.ACTIVE_STATUS);
			else
				nonEmployee.setStatus(AATConstants.INACTIVE_STATUS);			
		}		
		
		logger.debug("non-employee status is {} ",nonEmployee.getStatus());
	}
	
	private void getSupplierData(Message message, EllipseNonEmployee nonEmployee) throws Exception {
		
		EWSClientConversation conversation = getConversation(message);
		NonEmployee nonEmpService = createService(conversation, NonEmployee.class);
		OperationContext context = getOperationContext(message);
		
		NonEmployeeServiceReadRequestDTO readDTO = new NonEmployeeServiceReadRequestDTO();
		readDTO.setNonEmplId(nonEmployee.getId());
		NonEmployeeServiceReadReplyDTO replyDTO = nonEmpService.read(context, readDTO);
		
		/*If the Supplier Id value is set, then use the Supplier Name; else
		  the Address line 1 on the Postal address tab on the MSO811 record will be used to hold the Organisation name for the non employee*/
		if(StringUtils.isNotEmpty(replyDTO.getSupplierNo())){
			nonEmployee.setSupplierCode(replyDTO.getSupplierNo());
			nonEmployee.setSupplierName(replyDTO.getSupplierName());
		}
	}
	
	private boolean isDateEqual(String date) throws Exception {
		
		Date from=CommonUtil.ellDateFormat.parse(date);
		Date to=CommonUtil.ellDateFormat.parse(CommonUtil.now());
		
		return from.equals(to);
	}

	
	/*public static void main(String[] args) throws Exception {
		ConfigTree conf=new ConfigTree("test");
		EllipseNonEmployee nonEmployee = new EllipseNonEmployee();
		nonEmployee.setStartDate("20130302");
		nonEmployee.setEndDate("20130304");
		new NonEmployeeAction(conf).validate(nonEmployee);
	}*/
	
}
