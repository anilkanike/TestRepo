package com.transgrid.mib.ellipse.hr.action;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.jboss.soa.esb.actions.ActionProcessingFaultException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import com.mincom.enterpriseservice.ellipse.traincrssessupd.TrainCrsSessUpd;
import com.mincom.enterpriseservice.ellipse.traincrssessupd.TrainCrsSessUpdServiceHistoryUpdateRequestDTO;
import com.mincom.enterpriseservice.ellipse.traincrssessupd.TrainCrsSessUpdServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.traincrssessupd.TrainCrsSessUpdServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.traincrssessupd.TrainCrsSessUpdServiceResultUpdateRequestDTO;
import com.mincom.enterpriseservice.ellipse.trainingcourse.TrainingCourse;
import com.mincom.enterpriseservice.ellipse.trainingcourse.TrainingCourseServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.trainingcourse.TrainingCourseServiceReadRequestDTO;
import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;
import com.mincom.mib.common.utils.DateHelpers;
import com.mincom.mib.common.utils.ObjectDataHelper;
import com.mincom.mib.ellipse.EllipseBaseAction;
import com.transgrid.mib.ellipse.hr.domain.EllipseTrainingResults;
/**
 * 
 * @author Anil Kanike
 *
 */
public class TrainingResultsAction extends EllipseBaseAction {
	protected ConfigTree configuration;
	private OperationContext context = null;
	private TrainingCourse trainingCourseService = null;
	private TrainCrsSessUpd trainCrsSessUpd = null;
	private final ArrayList<String> attendeeResults = new ArrayList<String>();
	protected String trainResultsDTOName;
	protected String resultDTOName;
	private String trainCrsSessUpdBeanName;
	
	protected static final String NO_HISTORY_RECORD_EXISTS = "mims.e.8514";

	public TrainingResultsAction(ConfigTree config) {
		super(config);
		configuration = config;
		trainResultsDTOName = config.getAttribute(REQUEST_BEAN_ID, "trainingResults");
		resultDTOName = config.getAttribute(RESULT_BEAN_ID, "trainingResultDetails");
		
		trainCrsSessUpdBeanName = config.getAttribute(REQUEST_BEAN_ID, "empTrainCourseSess");
		
		init();
	}

	public Message process(Message message) throws ActionProcessingFaultException {
		logger.info("TrainingResultsAction.process...");
		
		try {
			EWSClientConversation conversation = getConversation(message);
			context = this.getOperationContext(message);
			trainingCourseService = createService(conversation, TrainingCourse.class, true);

			EllipseTrainingResults trResults = (EllipseTrainingResults) getDTOObject(message, trainResultsDTOName);
			trResults = getTrainingResults(trResults);
			if(trResults !=null){
				addResultToResponse(message, "OK", "read training results", "Training Results for CourseID is : "+trResults.getCourseId());
				storeReplyDTO(message, resultDTOName, trResults);
			}else{			
				addResultToResponse(message, "WARN", "Attendee Result is not in the list of "+attendeeResults, "Training Results can not be processed");
			}
			
			logger.info("TRAINING-RESULTS: "+trResults);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, "GetTrainingResults", e);
		}

		return message;
	}
	
	/**
	 * This method is to load the employee training results in to its corresponding ellipse tables for Savv-e Inbound interface
	 * @param message
	 * @return
	 * @throws ActionProcessingFaultException
	 */
	public Message modify(Message message) throws ActionProcessingFaultException {
		logger.info("TrainingResultsAction::modify...");

		try {
			EWSClientConversation conversation = getConversation(message);
			context = getOperationContext(message);

			// Create the service for employee training course session results update
			trainCrsSessUpd = createService(conversation, TrainCrsSessUpd.class);

			TrainCrsSessUpdServiceResultUpdateRequestDTO empTrainCourseDTO = (TrainCrsSessUpdServiceResultUpdateRequestDTO) getDTOObject(message, trainCrsSessUpdBeanName);

			modifyEmpTrainCrsSession(empTrainCourseDTO);
			addResultToResponse(message, "OK", "update employee training results", "Employee training result detail(s) are updated for ID "+empTrainCourseDTO.getAttendeeId());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, "Update employee training results", e);
		}

		return message;
	}
	/**
	 * method to update the training course session details to ellipse.
	 * @param empTrainCourseDTOs
	 * @return
	 * @throws Exception
	 */
	private void modifyEmpTrainCrsSession(TrainCrsSessUpdServiceResultUpdateRequestDTO empTrainCourseDTO)throws Exception {
		logger.info("TrainingResultsAction.modifyTrainCrsSession..");
		logger.info("Update training course details for Employee ID {}",empTrainCourseDTO.getAttendeeId());
		
		if(empTrainCourseDTO != null){
			try {
				//get the 'completionDate' first to call method 'resultUpdate'
				TrainCrsSessUpdServiceReadRequestDTO readDTO = new TrainCrsSessUpdServiceReadRequestDTO();
				readDTO.setAttendeeId(empTrainCourseDTO.getAttendeeId());
				readDTO.setAttendeeInd(empTrainCourseDTO.getAttendeeInd());
				readDTO.setTrainingCourse(empTrainCourseDTO.getTrainingCourse());
				readDTO.setTrainingSession(empTrainCourseDTO.getTrainingSession());
				
				TrainCrsSessUpdServiceReadReplyDTO replyDTO = trainCrsSessUpd.read(context, readDTO);
				if(replyDTO != null && StringUtils.isNotEmpty(replyDTO.getCompletionDate())){
					logger.debug("CompletionDate for the employee training course is {} ",replyDTO.getCompletionDate());
					empTrainCourseDTO.setCompletionDate(replyDTO.getCompletionDate());
				}					
				//call to update nominated trainee results					
				empTrainCourseDTO.setUpdateIndicator("X");
				logger.debug("Input parameters.. "+empTrainCourseDTO.getAttendeeId()+":"+empTrainCourseDTO.getAttendeeInd()+":"+empTrainCourseDTO.getTrainingCourse()+":"+empTrainCourseDTO.getTrainingSession()+":"+empTrainCourseDTO.getCompletionDate()+":"+empTrainCourseDTO.getAttendeeResult()+":"+empTrainCourseDTO.getAttendeeStatus()+":"+empTrainCourseDTO.getUpdateIndicator());
				trainCrsSessUpd.resultUpdate(context, empTrainCourseDTO);				
		
			} catch (Exception e) {
				if (extractError(e, NO_HISTORY_RECORD_EXISTS) != null) {
					logger.debug("History for employee {} does not exists. Creating new history record.", empTrainCourseDTO.getAttendeeId());
					//create new history record for the training course employee if not exists.
					TrainCrsSessUpdServiceHistoryUpdateRequestDTO historyDTO = new TrainCrsSessUpdServiceHistoryUpdateRequestDTO();
					ObjectDataHelper.defaultData(historyDTO, empTrainCourseDTO, false);
					historyDTO.setCompletionDate("");
					trainCrsSessUpd.historyUpdate(context, historyDTO);					
				} else {
					throw e;
				}
			}
		}		
	}

	private EllipseTrainingResults getTrainingResults(EllipseTrainingResults trResults) throws Exception {
		logger.info("TrainingResultsAction.getTrainingResults..");
		String trAttendResult = trResults.getAttendeeResult().trim();
		logger.debug("Training Attendee Result is {}",trAttendResult);
		// validate whether attendeeResults are DA, NA, PF, PP, KP, KF. If not return null message
		if (attendeeResults.contains(trAttendResult)) {
			trResults.setAttendeeResult(trAttendResult);
		} else {
			return null;
		}

		// validate completion date, if it is blank return null message
		String complDate = trResults.getCompletionDate();
		if (StringUtils.isNotEmpty(complDate)) {
			complDate = DateHelpers.reverseDate(complDate);
			trResults.setCompletionDate(complDate);
		} else {
			return null;
		}

		String attendType = trResults.getAttendeeType();
		if (StringUtils.equals(attendType, "E")) {
			trResults.setAttendeeType("E");
		} else {
			trResults.setAttendeeType("N");
		}

		TrainingCourseServiceReadRequestDTO readDTO = new TrainingCourseServiceReadRequestDTO();
		readDTO.setCourseId(trResults.getCourseId().trim());
		readDTO.setCourseMajorRev(trResults.getCourseMajorRev().trim());
		TrainingCourseServiceReadReplyDTO readReplyDTO = trainingCourseService.read(context, readDTO);

		trResults.setCourseType(readReplyDTO.getCourseType());
		trResults.setCourseTypeDesc(readReplyDTO.getCourseTypeDesc());

		return trResults;
	}

	private void init() {
		attendeeResults.add("DA");
		attendeeResults.add("NA");
		attendeeResults.add("PF");
		attendeeResults.add("PP");
		attendeeResults.add("KP");
		attendeeResults.add("KF");
	}

}
