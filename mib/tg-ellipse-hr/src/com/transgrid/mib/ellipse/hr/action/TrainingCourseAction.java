package com.transgrid.mib.ellipse.hr.action;


import org.jboss.soa.esb.actions.ActionProcessingFaultException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

import com.mincom.enterpriseservice.ellipse.trainingcourse.TrainingCourse;
import com.mincom.enterpriseservice.ellipse.trainingcourse.TrainingCourseServiceReadReplyDTO;
import com.mincom.enterpriseservice.ellipse.trainingcourse.TrainingCourseServiceReadRequestDTO;
import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;
import com.mincom.mib.ellipse.EllipseBaseAction;
import com.transgrid.mib.ellipse.hr.common.CommonUtil;
import com.transgrid.mib.ellipse.hr.domain.EllipseTrainingCourse;

/**
 * 
 * @author Anil Kanike
 *
 */
public class TrainingCourseAction extends EllipseBaseAction {
	protected ConfigTree configuration;
	protected String trainCrsDTOName;
	protected String resultDTOName;
	private OperationContext oc = null;
	private TrainingCourse trainingCourseService = null;
	//private TrainCrsResource trainCrsResourceService = null;
	private EllipseTrainingCourse trCourse = null;

	public TrainingCourseAction(ConfigTree config) {
		super(config);
		configuration = config;
		trainCrsDTOName = config.getAttribute(REQUEST_BEAN_ID, "trainingCourse");
		resultDTOName = config.getAttribute(RESULT_BEAN_ID, "trainingCourseResults");		
	}	

	public Message process(Message message) throws ActionProcessingFaultException {
		logger.info("TrainingCourseAction::process...");
		
		try {
			trCourse = new EllipseTrainingCourse();
			EWSClientConversation conversation = getConversation(message);
			oc = this.getOperationContext(message);
			trainingCourseService = createService(conversation, TrainingCourse.class, true);
			//trainCrsResourceService = createService(conversation, TrainCrsResource.class, true);
			
			TrainingCourseServiceReadRequestDTO readDTO = (TrainingCourseServiceReadRequestDTO)getDTOObject(message, trainCrsDTOName);
			logger.debug("CourseID = {} & MajorRevNo = {} ",readDTO.getCourseId(), readDTO.getCourseMajorRev());
			trCourse = getTrainingCourseData(readDTO);
			addResultToResponse(message, "OK", "read training course details", "Training Course ID is : "+trCourse.getId());
			storeReplyDTO(message, resultDTOName, trCourse);
			
			logger.info("TRAINING-COURSE: "+trCourse);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			message = this.storeExceptionInResponse(message, "GetTrainingCourse", e);
		}

		return message;
	}
	
	private EllipseTrainingCourse getTrainingCourseData(TrainingCourseServiceReadRequestDTO readDTO) throws Exception {
		logger.info("TrainingCourseAction.getTrainingCourseData() - Begin");
		String majorRevNo = CommonUtil.getMajorRevNo(readDTO.getCourseMajorRev());
		readDTO.setCourseMajorRev(majorRevNo);
		//Thread.sleep(1000);// setting 1 sec delay since ellipse not getting
							// updated after event generated
		TrainingCourseServiceReadReplyDTO readReplyDTO = trainingCourseService.read(oc, readDTO);
		logger.debug("TrainingCourseAction.getTrainingCourseData..."+readReplyDTO.getCourseId());
		trCourse.setId(readReplyDTO.getCourseId());
		trCourse.setName(readReplyDTO.getCourseTitle());
		trCourse.setDesc(readReplyDTO.getCourseDesc());
		trCourse.setStatus(readReplyDTO.getCourseStatus());
		trCourse.setDeliveryMethod(readReplyDTO.getDeliveryMethod());
		trCourse.setDeliveryMethodDesc(readReplyDTO.getDeliveryMethodDescription());
		trCourse.setType(readReplyDTO.getCourseType());
		trCourse.setTypeDesc(readReplyDTO.getCourseTypeDesc());
		trCourse.setRequalMonths(readReplyDTO.getRequalPeriod());
		trCourse.setRequalInd(readReplyDTO.getRequalPrdUOM());
		//trCourse = getTrainingCourseResourceData(trCourse.getId(), majorRevNo);

		return trCourse;
	}
 	

	/**
	 * Method to get the training course and training course resource details
	 * when employee/non-employee is been added.
	 * 
	 * @param readTrainCrsResReqDTO
	 * @param trainingCourseDetails
	 * @return
	 * @throws Exception
	 */
	/*private EllipseTrainingCourse getTrainingCourseResourceData(String courseId, String majorRevNo) throws Exception {

		TrainCrsResourceServiceRetrieveRequestDTO retrieveDTO = new TrainCrsResourceServiceRetrieveRequestDTO();
		retrieveDTO.setCourseMajorRev(majorRevNo);
		retrieveDTO.setResourceInd("T");
		retrieveDTO.setCourseId(courseId);

		TrainCrsResourceServiceRetrieveReplyCollectionDTO resultDTO = new TrainCrsResourceServiceRetrieveReplyCollectionDTO();
		resultDTO.setReplyElements(new ArrayOfTrainCrsResourceServiceRetrieveReplyDTO());
		List<TrainCrsResourceServiceRetrieveReplyDTO> results = resultDTO.getReplyElements().getTrainCrsResourceServiceRetrieveReplyDTO();

		String restart = null;
		boolean moreData = true;
		int batchCount = 0;
		while (moreData) {
			batchCount++;
			TrainCrsResourceServiceRetrieveReplyCollectionDTO batchResult = trainCrsResourceService.retrieve(oc, retrieveDTO, null, restart);
			results.addAll(batchResult.getReplyElements().getTrainCrsResourceServiceRetrieveReplyDTO());

			int readCount = batchResult.getReplyElements().getTrainCrsResourceServiceRetrieveReplyDTO().size();
			logger.info("Search batch count {}, returned {}", batchCount, readCount);

			restart = batchResult.getCollectionRestartPoint();
			if (restart == null) {
				moreData = false;
			}
		}

		// set resluts to domain object
		for (TrainCrsResourceServiceRetrieveReplyDTO replyDTO : results) {
			TrainerType personType = new TrainerType(); 
			personType.setId(replyDTO.getResourceId());
			personType.setFirstName(StringUtils.substringAfter(replyDTO.getResourceDesc(), ","));
			personType.setLastName(StringUtils.substringBefore(replyDTO.getResourceDesc(), ","));

			// validateTrainerType();
			String trainerType = replyDTO.getTrainerType();
			if ("1".equals(trainerType) || "2".equals(trainerType) || "3".equals(trainerType)) {
				personType.setEmpNonEmpIndicator(TGConstants.EMPLOYEE);
			} else {
				personType.setEmpNonEmpIndicator(TGConstants.NON_EMPLOYEE);
			}

			if ("1".equals(trainerType) || "4".equals(trainerType)) {
				trCourse.setTrainer(personType);
			} else if ("2".equals(trainerType) || "5".equals(trainerType)) {
				trCourse.setAssessor(personType);
			} else {
				trCourse.setTrainer(personType);
				trCourse.setAssessor(personType);
			}
		}
		return trCourse;
	}*/

}
