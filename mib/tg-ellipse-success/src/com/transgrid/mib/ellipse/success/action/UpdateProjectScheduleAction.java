package com.transgrid.mib.ellipse.success.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
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
import com.mincom.ellipse.service.m3660.projectmanagementexportitem.ArrayOfProjectManagementExportItemServiceResult;
import com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItem;
import com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO;
import com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemSearchParam;
import com.mincom.enterpriseservice.ellipse.ErrorMessageDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ArrayOfContractItemServiceModifyPortMileRequestDTO;
import com.mincom.enterpriseservice.ellipse.contractitem.ContractItem;
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
import com.mincom.enterpriseservice.ellipse.jobestimate.ArrayOfJobEstimateServiceModifyItemRequestDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimate;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceModifyItemReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceModifyItemReplyDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceUpdateEstimateCostsReplyDTO;
import com.mincom.enterpriseservice.ellipse.jobestimate.JobEstimateServiceUpdateEstimateCostsRequestDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.ArrayOfMaterialReqmntsServiceCreateRequestDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.ArrayOfMaterialReqmntsServiceDeleteRequestDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.ArrayOfMaterialReqmntsServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmnts;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceCreateReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceCreateRequestDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceDeleteReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceDeleteRequestDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceRetrieveReplyDTO;
import com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceRetrieveRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.Project;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceActualsReplyDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceActualsRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceModifyReplyDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServicePlanReplyDTO;
import com.mincom.enterpriseservice.ellipse.project.ProjectServicePlanRequestDTO;
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
import com.mincom.enterpriseservice.ellipse.table.Table;
import com.mincom.enterpriseservice.ellipse.table.TableServiceReadRequestDTO;
import com.mincom.enterpriseservice.ellipse.workorder.ArrayOfWorkOrderServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrder;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceModifyReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceModifyReplyDTO;
import com.mincom.enterpriseservice.ellipse.workorder.WorkOrderServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.ArrayOfWorkOrderTaskServiceModifyRequestDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTask;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceModifyReplyCollectionDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceModifyReplyDTO;
import com.mincom.enterpriseservice.ellipse.workordertask.WorkOrderTaskServiceModifyRequestDTO;
import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;
import com.mincom.mib.common.StandardError;
import com.mincom.mib.common.StandardResult;
import com.mincom.mib.common.utils.ObjectDataHelper;
import com.mincom.mib.ellipse.EllipseBaseAction;
import com.mincom.mib.ellipse.projectmanagement.ActivityType;
import com.mincom.mib.ellipse.projectmanagement.ProjectSchedule;
import com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity;

/**
 * This class handles all web service calls related to Project Schedule Exports.
 * 
 * @author Anil Kanike
 * @version 1.0
 * @see com.mincom.mib.ellipse.EllipseBaseAction
 */
public class UpdateProjectScheduleAction extends EllipseBaseAction {

	/**
	 * Logger used for this class.
	 * 
	 * @see org.slf4j.Logger
	 */
	public Logger logger = LoggerFactory.getLogger(UpdateProjectScheduleAction.class);

	/**
	 * Name of the project schedule export list inside the message, via the
	 * configuration file,
	 * {@link com.mincom.mib.ellipse.EllipseBaseAction.REQUEST_BEAN_ID
	 * REQUEST_BEAN_ID}.
	 */
	private String pseList;

	/**
	 * Name of the project schedule export list inside the return message, via
	 * the configuration file,
	 * {@link com.mincom.mib.ellipse.EllipseBaseAction.RESULT_BEAN_ID
	 * RESULT_BEAN_ID}
	 */
	private String pseResult;

	/**
	 * Configuration value, defaulted to false. If true, this action will return
	 * missing objects as a warning, advising the user to refresh their system.
	 */
	private boolean leniency;
	
    /**
     * These codes returned by ellipse when the ResourceReqmnts object is not found for the read.
     */
    private static String JE_RES_REQ_MISSING_CODE = "mims.e.0011";
    private static String WT_RES_REQ_MISSING_CODE = "mims.e.5031";

	/**
	 * The code returned by ellipse when the EquipmentReqmnts object is not
	 * found. TODO: Ensure this is correct!
	 */
	// private static String EQUIP_REQ_MISSING_CODE = "mims.e.5033";
	
	private static String DELETE_ALL_EXISTING = "DeleteAllExisting";
	private boolean isDelAllExistingMatResource = false;
	/**
	 * The code returned by ellipse when the ResourceReqmnts object is not found
	 * for the read.
	 */
	private static String RES_REQ_MISSING_CODE = "mims.e.0011";
	
	/**
	 * The code returned by ellipse when the MaterialReqmnts with UOM object is not found
	 * for the update.
	 */
	private static String TABLE_CODE_NOT_EXIST = "mims.e.0041";

	/**
	 * The code returned by ellipse when the ResourceReqmnts Job Estimate object
	 * is not found.
	 */
	// private static String REQMNTS_JOB_ESTIMATE_MISSING = "mims.e.4949";
	
	private Table tableService = null;

	/**
	 * Default constructor
	 * 
	 * @param config
	 *            - Configuration file to use.
	 * @see EllipseBaseAction#EllipseBaseAction(ConfigTree)
	 */
	public UpdateProjectScheduleAction(ConfigTree config) {
		super(config);
		pseList = config.getAttribute(REQUEST_BEAN_ID, "exports");
		pseResult = config.getAttribute(RESULT_BEAN_ID, "exportResults");
		leniency = config.getBooleanAttribute("warnIfItemNotFound", false);
	}

	/**
	 * Process the data within the message. This data will be updated inside the
	 * project management web service and the corresponding
	 * {@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity
	 * ProjectScheduleActivity} via their respective web services.
	 * 
	 * @param msg
	 *            - Data to process.
	 * @return Message - Result of process call.
	 * @throws ActioProcessingException
	 *             Thrown when an error occurs with the server call.
	 */
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
			tableService = createService(conversation, Table.class);	
			
			// Create the Project Management Export service
			ProjectManagementExport exportService = createService(conversation, ProjectManagementExport.class);
			ArrayOfProjectManagementExportDTO readReqList = new ArrayOfProjectManagementExportDTO();
			for (ProjectSchedule ps : psList) {
				ProjectManagementExportDTO readReq = ps.getEllipseDTO();
				readReqList.getProjectManagementExportDTO().add(readReq);
			}
			ArrayOfProjectManagementExportServiceResult readReply = exportService.multipleRead(oc, readReqList);

			inspectReplyForErrors(readReply, "ProjectScheduleAction.get");

			msg = modify(msg, psList);

		} catch (Exception ex) {
			logger.error("UpdateProjectScheduleAction::process(Message) - Exception: " + ex.getMessage());
			msg = storeExceptionInResponse(msg, currentContext, ex);
		}

		return msg;
	}

    /**
     * Modify the given data inside the project management web service and the corresponding
     *   {@link com.mincom.mib.ellipse.projectmanagement.ProjectScheduleActivity ProjectScheduleActivity}
     *   via their respective web services.
     * 
     * @param msg - Message that contains the hard data.
     * @param projectSched - ArrayList of {@link com.mincom.mib.ellipse.projectmanagement.ProjectSchedule ProjectSchedule} to modify.
     * @return Message - Result of modify call.
     * @throws ActionProcessingException Thrown when an error occurs with the server call.
     */
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
			if (oc.getRunAs() != null && oc.getRunAs().getUser() != null) {
				user = oc.getRunAs().getUser();
			}

			// Create the Project Management Export Item service
			ProjectManagementExportItem exportItemService = createService(conversation, ProjectManagementExportItem.class);

			// Create the services that a ProjectScheduleActivity will use, set them to null for now. They will be initialized when needed:
			Project projectService = null;
			WorkOrder workOrderService = null;
			WorkOrderTask workOrderTaskService = null;
			ContractItem contractItemService = null;
			JobEstimate jobEstimateService = null;
			EquipmentReqmnts equipReqService = null;
			ResourceReqmnts labReqService = null;
			MaterialReqmnts matReqService = null;

			for (ProjectSchedule ps : projectSched) {
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
				for (ProjectScheduleActivity act : ps.getScheduleActivities()) {

					// converting to custom java class to get the Material
					// Requirements
					//UnderlyingProjectScheduleActivity act = (UnderlyingProjectScheduleActivity) activity;

					currentContext = "modify(Message, ArrayList) - Modify items: ID: " + act.getEntityID() + ", estimate number: " + act.getJobEstimateNumber()
							+ ", estimate item number: " + act.getJobEstimateItemID();
					String estItemID = null;
					if (act.getJobEstimateItemID() != null) {
						estItemID = act.getJobEstimateItemID().toString();
					}
					// check activity here!
					ProjectManagementExportItemDTO item = findData(pmeItems, act.getType(), act.getEntityID(), act.getJobEstimateNumber(), estItemID);
					if (item == null) {
						if (leniency) {
							String details = "ProjectScheduleActivity was not found.  See additional information for more details.";
							String additionalDetails = "ProjectScheduleActivity ID: " + act.getEntityID() + ", Export ID:" + ps.getExportID() + ", Estimate Number: "
									+ act.getJobEstimateNumber();
							StandardResult res = new StandardResult("Warning", "ProjectScheduleAction.modify", details, additionalDetails, index);
							warnList.add(res);
						} else {
							String details = "ProjectScheduleActivity was not found.  See additional information for more details.";
							String additionalDetails = "ProjectScheduleActivity ID: " + act.getEntityID() + ", Export ID:" + ps.getExportID() + ", Estimate Number: "
									+ act.getJobEstimateNumber();
							StandardError error = new StandardError("Error", "ProjectScheduleAction.modify", details, additionalDetails, index);
							errorList.add(error);
						}
					} else {
						switch (act.getType()) {
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

				if (equipReqService == null) {
					equipReqService = createService(conversation, EquipmentReqmnts.class);
				}

				if (labReqService == null) {
					labReqService = createService(conversation, ResourceReqmnts.class);
				}
				// added for SUCCESS project
				if (matReqService == null) {
					matReqService = createService(conversation, MaterialReqmnts.class);
				}

				if (projItems.size() > 0) {
					currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify Project Data";

					if (projectService == null) {
						projectService = createService(conversation, Project.class);
					}
					psResult.addAll(modifyProjects(oc, projectService, equipReqService, labReqService, msg, projActivities, projItems));
				}

				if (woItems.size() > 0) {
					currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify WorkOrder Data";
					if (workOrderService == null) {
						workOrderService = createService(conversation, WorkOrder.class);
					}
					psResult.addAll(modifyWorkOrders(oc, workOrderService, equipReqService, labReqService, msg, woActivities, woItems));
				}

				if (wotItems.size() > 0) {
					currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify WorkOrderTask Data";
					if (workOrderTaskService == null) {
						workOrderTaskService = createService(conversation, WorkOrderTask.class);
					}

					psResult.addAll(modifyWorkOrderTasks(oc, workOrderTaskService, equipReqService, labReqService, msg, wotActivities, wotItems, user));

				}

				if (ciItems.size() > 0) {
					currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify Contract Item Data";

					if (contractItemService == null) {
						contractItemService = createService(conversation, ContractItem.class);
					}
					psResult.addAll(modifyContractItems(oc, contractItemService, equipReqService, labReqService, msg, ciActivities, ciItems));
				}

				if (jeItems.size() > 0) {
					currentContext = "modify(Message, ArrayList) - ProjectScheduleActivity: Modify Job Estimate Item Data";
					if (jobEstimateService == null) {
						jobEstimateService = createService(conversation, JobEstimate.class);
					}
					psResult.addAll(modifyJobEstimates(oc, jobEstimateService, equipReqService, labReqService, matReqService, msg, jeActivities, jeItems));
				}

				ProjectSchedule newPS = new ProjectSchedule(ps);
				newPS.setScheduleActivities(psResult);
				retVal.add(newPS);
			}

			if (errorList.size() > 0) {
				addErrorsToResponse(msg, errorList);
			}

			if (warnList.size() > 0) {
				// TODO will this cause an error?
				for (StandardResult sr : warnList) {
					addResultToResponse(msg, sr.getCode(), sr.getOperation(), sr.getDetails() + " " + sr.getAdditionalInformation(), sr.getRequestIndex());
				}
			}

			if (retVal.size() > 0) {
				String details = "Processed " + retVal.size() + " ProjectManagementExports.";
				addResultToResponse(msg, "OK", "ProcessProjectManagementExports", details);
				storeReplyDTO(msg, pseResult, retVal);
			}

		} catch (Exception ex) {
			logger.error(currentContext);
			logger.error("PSActivity::modify(Message, ArrayList) - Exception: " + ex.getMessage());
			msg = storeExceptionInResponse(msg, currentContext, ex);
		}
		return msg;
	}

	static protected long EllipseTotal = 0;
	static protected long EllipseCallTotal = 0;

	/**
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
	 */
	protected ArrayList<ProjectScheduleActivity> modifyJobEstimates(OperationContext oc, JobEstimate jobServ, EquipmentReqmnts equipReqService, ResourceReqmnts labReqService,
			MaterialReqmnts matReqService, Message msg, ArrayList<ProjectScheduleActivity> activities, List<ProjectManagementExportItemDTO> items) throws Exception {
		ArrayList<ProjectScheduleActivity> retVal = new ArrayList<ProjectScheduleActivity>();
		ArrayOfJobEstimateServiceModifyItemRequestDTO modifyReqList = new ArrayOfJobEstimateServiceModifyItemRequestDTO();

		ArrayList<ProjectManagementExportItemDTO> finalItemList = new ArrayList<ProjectManagementExportItemDTO>();
		ArrayList<ProjectScheduleActivity> finalActivityList = new ArrayList<ProjectScheduleActivity>();

		for (int index = 0; index < items.size(); ++index) {
            // TODO This can cause an error.  The user can just set a JobEstimate to "IN" status and it would attempt to be processed.
            // TODO  The real problem is the ProjectManagementExportItemDTO not setting the status properly.
            // TODO  If that worked correctly, this could rely on something form the server, which will be far more reliable then something from the user.
			logger.warn("ProjectScheduleAction::modify(Message, ArrayList) - This may cause problems if the user inserted the status incorrectly, JobEstimate number: "
					+ activities.get(index).getJobEstimateNumber());
            if("IN".equals(items.get(index).getEstimateItemStatus()) || "IN".equals(activities.get(index).getJobEstimateStatus())) {
                finalItemList.add(items.get(index));
                finalActivityList.add(activities.get(index));
            } else {
                logger.warn("ProjectScheduleAction::modify(Message, ArrayList) - Can't update Job Estimate number: " + items.get(index).getEstimateNumber()
                        + " with item: " + items.get(index).getJobEstimateItemNumber());
                logger.warn("ProjectScheduleAction::modify(Message, ArrayList) - Estimate status is NOT in progress.");
            }
		}

		for (ProjectScheduleActivity act : finalActivityList) {
			modifyReqList.getJobEstimateServiceModifyItemRequestDTO().add(act.getJobEstimateItemModify());
		}

		if (modifyReqList.getJobEstimateServiceModifyItemRequestDTO().size() > 0) {
			JobEstimateServiceModifyItemReplyCollectionDTO jobEstimateItemReply = jobServ.multipleModifyItem(oc, modifyReqList);

			int finalIndex = 0;
			for (JobEstimateServiceModifyItemReplyDTO modRes : jobEstimateItemReply.getReplyElements().getJobEstimateServiceModifyItemReplyDTO()) {
				ProjectScheduleActivity psaResult = new ProjectScheduleActivity();
				psaResult.populateFields(modRes);
				psaResult.populateEstimateFields(finalItemList.get(finalIndex));

				// Start -- added logic for SUCCESS
				MaterialReqmntsServiceRetrieveRequestDTO matRet = new MaterialReqmntsServiceRetrieveRequestDTO();
				matRet.setJEItemNo(BigDecimal.valueOf(Long.valueOf(finalActivityList.get(finalIndex).getJobEstimateItemID())));
				matRet.setEstimateNo(finalActivityList.get(finalIndex).getJobEstimateNumber());
				matRet.setDistrictCode(finalActivityList.get(finalIndex).getDistrictCode());
				matRet.setClassType("JE");
				matRet.setRetrAllFlg(true);
				matRet.setVersionNo(finalActivityList.get(finalIndex).getJobEstimateVersion());
				// End -- added logic for SUCCESS

				ArrayOfEquipmentReqmntsServiceModifyRequestDTO equipReqModList = new ArrayOfEquipmentReqmntsServiceModifyRequestDTO();
                ArrayOfEquipmentReqmntsServiceDeleteRequestDTO equipReqDelList = new ArrayOfEquipmentReqmntsServiceDeleteRequestDTO();
                ArrayOfResourceReqmntsServiceModifyRequestDTO resReqModList = new ArrayOfResourceReqmntsServiceModifyRequestDTO();
                ArrayOfResourceReqmntsServiceDeleteRequestDTO resReqDelList = new ArrayOfResourceReqmntsServiceDeleteRequestDTO();
				ArrayOfMaterialReqmntsServiceModifyRequestDTO mateReqModList = new ArrayOfMaterialReqmntsServiceModifyRequestDTO();

				for (Object obj : activities.get(finalIndex).getResourceRequests()) {
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
					// Added to update the Material resource for the SUCCESS interface
					else if (obj instanceof MaterialReqmntsServiceModifyRequestDTO) {
						MaterialReqmntsServiceModifyRequestDTO matReq = (MaterialReqmntsServiceModifyRequestDTO) obj;
						//condition added to verify whether existing Material Resource details need to be deleted or not based on request message.
						if(StringUtils.equals(matReq.getSeqNo(), DELETE_ALL_EXISTING)){
							isDelAllExistingMatResource = true;
							continue;
						}
						matReq.setJEItemNo(BigDecimal.valueOf(Long.valueOf(activities.get(finalIndex).getJobEstimateItemID())));
						matReq.setEstimateNo(activities.get(finalIndex).getJobEstimateNumber());
						matReq.setDistrictCode(activities.get(finalIndex).getDistrictCode());
						matReq.setVersionNo(activities.get(finalIndex).getJobEstimateVersion());
						matReq.setClassType("JE");
						mateReqModList.getMaterialReqmntsServiceModifyRequestDTO().add(matReq);
					}
				}

				ArrayList<Object> resourceReply = handleResourceRequests(equipReqModList, equipReqDelList, resReqModList, resReqDelList, mateReqModList, oc, equipReqService, labReqService, matReqService, matRet,	msg);
				
				// Update the Estimated Costs added for SUCCESS project since the existing service does't support to update Estimate costs.
				if (finalActivityList.size() > 0) {
					updateNonCalculatedCosts(finalActivityList.get(finalIndex), jobServ, oc, msg);
				}
				
				if (!resourceReply.isEmpty()) {
					psaResult.addResourceReplies(resourceReply);
				}
				/*psaResult.setExpectedResourceRequirementsExist(false);
                psaResult.setActualResourceRequirementsExist(false);*/
                retVal.add(psaResult);
                ++finalIndex;
			} 
		}

		return retVal;
	}

	/**
	 * Method to update the non calculated costs for Job Estimate details.
	 * @author C16950
	 * @param activities
	 * @param jobServ
	 * @param oc
	 * @throws Exception
	 */
	private void updateNonCalculatedCosts(ProjectScheduleActivity act, JobEstimate jobServ, OperationContext oc, Message msg) throws Exception {
		logger.info("---Entered to ProjectScheduleAction::updateNonCalculatedCosts---");
		boolean updateCosts = false;

		if (act.getExpectedLabourHrs() != null || act.getExpectedLabourCost() != null || act.getExpectedMaterialCost() != null || act.getExpectedEquipmentCost() != null
				|| act.getExpectedOtherCost() != null) {
			updateCosts = true;
		}

		if (updateCosts) {
			JobEstimateServiceUpdateEstimateCostsRequestDTO reqDTO = new JobEstimateServiceUpdateEstimateCostsRequestDTO();			
			reqDTO.setEstimateNo(act.getJobEstimateNumber());
			reqDTO.setItemNo(new BigDecimal(act.getJobEstimateItemID()));
			reqDTO.setVersionNo(act.getJobEstimateVersion());

			if (act.getExpectedLabourHrs() != null){
				reqDTO.setCalculatedLabFlag(false);
				reqDTO.setEstimatedLabHrs(act.getExpectedLabourHrs());
			}
			if (act.getExpectedLabourCost() != null){
				reqDTO.setCalculatedLabFlag(false);
				reqDTO.setEstimatedLabCost(act.getExpectedLabourCost());
			}
			if (act.getExpectedMaterialCost() != null){
				reqDTO.setCalculatedMatFlag(false);
				reqDTO.setEstimatedMatCost(act.getExpectedMaterialCost());
			}
			if (act.getExpectedEquipmentCost() != null){
				reqDTO.setCalculatedEquipmentFlag(false);
				reqDTO.setEstimatedEquipmentCost(act.getExpectedEquipmentCost());
			}
			if (act.getExpectedOtherCost() != null){
				reqDTO.setCalculatedOtherFlag(false);
				reqDTO.setEstimatedOtherCost(act.getExpectedOtherCost());
			}
			
			JobEstimateServiceUpdateEstimateCostsReplyDTO result = jobServ.updateEstimateCosts(oc, reqDTO);
			if(result != null)
				addResultToResponse(msg, "OK", "JobEstimate-Update", "Updated Estimate Costs for Item No "+act.getJobEstimateItemID());
			logger.info("Job Estimate non caluclated costs have been updated successfully for Item no {} ",act.getJobEstimateItemID());
		
			//update calculated falgs back to checked to populate Ellipse estimated cost deatils after update SUCCESS estimate details
			JobEstimateServiceUpdateEstimateCostsRequestDTO reqUpdateFlags = new JobEstimateServiceUpdateEstimateCostsRequestDTO();			
			reqUpdateFlags.setCalculatedEquipmentFlag(true);
			reqUpdateFlags.setCalculatedLabFlag(true);
			reqUpdateFlags.setCalculatedMatFlag(true);
			reqUpdateFlags.setEstimateNo(act.getJobEstimateNumber());
			reqUpdateFlags.setItemNo(new BigDecimal(act.getJobEstimateItemID()));
			reqUpdateFlags.setVersionNo(act.getJobEstimateVersion());
			jobServ.updateEstimateCosts(oc, reqUpdateFlags);	
		}		 

		logger.info("---Exit to ProjectScheduleAction::updateNonCalculatedCosts---");
	}

	/**
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
     */
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
                /*psaResult.setExpectedResourceRequirementsExist(false);
                psaResult.setActualResourceRequirementsExist(false);*/    	        
                
                retVal.add(psaResult);
                ++index;
            }
        }
        
        return retVal;
    }

	/**
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
     */
    protected ArrayList<ProjectScheduleActivity> modifyWorkOrderTasks(OperationContext oc, WorkOrderTask workOrderTaskService,
            EquipmentReqmnts equipReqService, ResourceReqmnts labReqService, Message msg,
            ArrayList<ProjectScheduleActivity> activities, List<ProjectManagementExportItemDTO> items,
            String user) throws Exception {
    	isDelAllExistingMatResource = false;
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
                
                // Start -- added logic for SUCCESS
				/*MaterialReqmntsServiceRetrieveRequestDTO matRet = new MaterialReqmntsServiceRetrieveRequestDTO();
				matRet.setJEItemNo(BigDecimal.valueOf(Long.valueOf(activities.get(index).getJobEstimateItemID())));
				matRet.setEstimateNo(activities.get(index).getJobEstimateNumber());
				matRet.setDistrictCode(activities.get(index).getDistrictCode());
				matRet.setClassType("WT");
				matRet.setRetrAllFlg(true);
				matRet.setVersionNo(activities.get(index).getJobEstimateVersion());*/
				// End -- added logic for SUCCESS

                ArrayOfEquipmentReqmntsServiceModifyRequestDTO equipReqModList = new ArrayOfEquipmentReqmntsServiceModifyRequestDTO();
                ArrayOfEquipmentReqmntsServiceDeleteRequestDTO equipReqDelList = new ArrayOfEquipmentReqmntsServiceDeleteRequestDTO();
                ArrayOfResourceReqmntsServiceModifyRequestDTO resReqModList = new ArrayOfResourceReqmntsServiceModifyRequestDTO();
                ArrayOfResourceReqmntsServiceDeleteRequestDTO resReqDelList = new ArrayOfResourceReqmntsServiceDeleteRequestDTO();
                //ArrayOfMaterialReqmntsServiceModifyRequestDTO mateReqModList = new ArrayOfMaterialReqmntsServiceModifyRequestDTO();
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
                	// Added to update the Material resource for the SUCCESS interface
					/*else if (obj instanceof MaterialReqmntsServiceModifyRequestDTO) {
						MaterialReqmntsServiceModifyRequestDTO matReq = (MaterialReqmntsServiceModifyRequestDTO) obj;
						String ID = activities.get(index).getParentID();
						com.mincom.enterpriseservice.ellipse.materialreqmnts.WorkOrderDTO resWODTO = new com.mincom.enterpriseservice.ellipse.materialreqmnts.WorkOrderDTO();
						resWODTO.setPrefix(ID.substring(0, 2));
						resWODTO.setNo(ID.substring(2));
						matReq.setWorkOrder(resWODTO);
						matReq.setWorkOrderTask(activities.get(index).getEntityID().substring(12));
						matReq.setDistrictCode(activities.get(index).getDistrictCode());
						matReq.setClassType("WT");
						mateReqModList.getMaterialReqmntsServiceModifyRequestDTO().add(matReq);
					}*/
                }                
                //ArrayList<Object> resourceReply = handleResourceRequests(equipReqModList, equipReqDelList, resReqModList, resReqDelList, oc, equipReqService, labReqService, msg);
                ArrayList<Object> resourceReply = handleResourceRequests(equipReqModList, equipReqDelList, resReqModList, resReqDelList, null, oc, equipReqService, labReqService, null, null, msg);
                if(!resourceReply.isEmpty()) {
                    psaResult.addResourceReplies(resourceReply);
                }

                /*psaResult.setExpectedResourceRequirementsExist(false);
                psaResult.setActualResourceRequirementsExist(false);*/
                retVal.add(psaResult);
                ++index;
            }
        }
        return retVal;
    }
    
	/**
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
     */
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
                /*psaResult.setExpectedResourceRequirementsExist(false);
                psaResult.setActualResourceRequirementsExist(false);*/    	        
               
                retVal.add(psaResult);
                ++index;
            }
        }
        return retVal;
    }

	/**
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
     */
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
        	/*psaResult.setExpectedResourceRequirementsExist(false);
            psaResult.setActualResourceRequirementsExist(false);*/    	        

        	retVal.add(psaResult);
        }
        
        return retVal;
    }

/**
	 * Retrieve the {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTOs}
	 * @param exportItemService - {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItem ProjectManagementExportItem} service to use.
	 * @param conversation - The current conversation to use. {@link com.mincom.ews.client.EWSClientConversation EWSClientConversation)
	 * @param oc - The current operation context
	 * @param parentExportID - The export ID used to retrieve the associated items.
	 * @return Array list of {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProjectManagementExportItemDTOs}, as an {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ArrayOfProjectManagementExportItemServiceResult ArrayOfProjectManagementExportItemServiceResult}
	 * @throws Exception Error during service interaction
	 */
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

	/**
	 * Search the array for the given key.
	 * @param pmeArray - Array of {@link com.mincom.ellipse.service.m3660.projectmanagementexportitem.ProjectManagementExportItemDTO ProgramManagementExportItemDTO} to search.
	 * @param type - {@link com.mincom.mib.ellipse.projectmanagement.ActivityType ActivityType} that we are searching for.
	 * @param entityID - The entity ID of the data.  This is used if the type IS NOT ActivityType.JE.
	 * @param estimateNo - The estimate number of the data.  This is used if the type IS ActivityType.JE.
	 * @param estimateItemNo - The estimate item number of the data.  This is used if the type IS ActivityType.JE. 
	 * @return True if the ID was found, false if it wasn't.
	 */
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

	/**
	 * Performs the web service call for EquipmentReqmnts and ResourceReqmnts and returns their result.
	 * @param equipReqModList - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.ArrayOfEquipmentReqmntsServiceModifyRequestDTO ArrayOfEquipmentReqmntsServiceModifyRequestDTO} is the list of modify request for equipment resources.
	 * @param resReqModList - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ArrayOfResourceReqmntsServiceModifyRequestDTO ArrayOfResourceReqmntsServiceModifyRequestDTO} is the list of modify request for labour resources.
	 * @param oc - {@link com.mincom.ews.service.connectivity.OperationContext OperationContext} to use during server interaction.
	 * @param equipReqService - {@link com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EquipmentReqmnts EquipmentReqmnts} web service to use, must not be null.
	 * @param labReqService - {@link com.mincom.enterpriseservice.ellipse.resourcereqmnts.ResourceReqmnts ResourceReqmnts ResourceReqmnts} web service to use, must not be null.
	 * @param msg - {@link org.jboss.soa.esb.message.Message Message} Used only for logging successful status of server calls.
	 * @return Returns an ArrayList of the web service replies.
	 * @throws Exception Error during web service call(s).
	 */
	private ArrayList<Object> handleResourceRequests(ArrayOfEquipmentReqmntsServiceModifyRequestDTO equipReqModList, ArrayOfEquipmentReqmntsServiceDeleteRequestDTO equipReqDelList, 
			ArrayOfResourceReqmntsServiceModifyRequestDTO resReqModList, ArrayOfResourceReqmntsServiceDeleteRequestDTO resReqDelList,
			ArrayOfMaterialReqmntsServiceModifyRequestDTO mateReqModList, OperationContext oc, EquipmentReqmnts equipReqService, ResourceReqmnts labReqService,
			MaterialReqmnts matReqService, MaterialReqmntsServiceRetrieveRequestDTO matRet, Message msg) throws Exception {
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

		// Added code for create the Material resource details for SUCCESS interface.
		if(isDelAllExistingMatResource){
			logger.info("ProjectScheduleAction::handleResourceRequests() - isDelAllExistingMatResource is true - ");			
			deleteExistingMaterialResource(matReqService, matRet, msg, oc);
			isDelAllExistingMatResource = false; //setting back to false status after delete.
		}
		if (mateReqModList != null && mateReqModList.getMaterialReqmntsServiceModifyRequestDTO().size() > 0) {
			//create the material resources from the request message.
			if (!mateReqModList.getMaterialReqmntsServiceModifyRequestDTO().isEmpty()) {
				createMaterialReqirements(mateReqModList, matReqService, oc, msg);
			}
		}

		logger.debug("ProjectScheduleAction::handleResourceRequests() - End");
		return retVal;
	}
	
	/**
	 * Method to delete the existing material resource details
	 * @author C16950
	 * @param matReqService
	 * @param matRet
	 * @param msg
	 * @param oc
	 * @throws Exception
	 */
	private void deleteExistingMaterialResource(MaterialReqmnts matReqService, MaterialReqmntsServiceRetrieveRequestDTO matRet, Message msg, OperationContext oc) throws Exception {
		logger.debug("ProjectScheduleAction::deleteExistingMaterialResource() - Start");
		ArrayOfMaterialReqmntsServiceDeleteRequestDTO matDeleteList = new ArrayOfMaterialReqmntsServiceDeleteRequestDTO();

		com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceRetrieveRequiredAttributesDTO matReqAttr = new com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceRetrieveRequiredAttributesDTO();
		matReqAttr.setReturnSeqNo(true);

		ArrayList<Object> matRetValues = new ArrayList<Object>();

		if (matRet != null) {
			logger.debug("ProjectScheduleAction::deleteExistingMaterialResource() - Retrieving MaterialReqmnts");
			String restartInfo = null;
			boolean moreData = true;
			int batchCount = 0;
			try {
				while (moreData) {
					logger.debug("ProjectScheduleAction::deleteExistingMaterialResource() - Begin retrieve batch loop:");
					com.mincom.enterpriseservice.ellipse.materialreqmnts.MaterialReqmntsServiceRetrieveReplyCollectionDTO batchReply = matReqService.retrieve(oc, matRet,
							matReqAttr, restartInfo);

					inspectReplyForErrors(batchReply, "ProjectScheduleAction.deleteExistingMaterialResource");
					int readCount = batchReply.getReplyElements().getMaterialReqmntsServiceRetrieveReplyDTO().size();
					logger.debug("ProjectScheduleAction::deleteExistingMaterialResource() - Search received batch count - {}, returned - {}", batchCount, readCount);

					if (readCount < oc.getMaxInstances()) {
						moreData = false;
					} else {
						restartInfo = batchReply.getCollectionRestartPoint();
						++batchCount;
					}

					logger.debug("ProjectScheduleAction::deleteExistingMaterialResource() - Adding retrieved MaterialReqmnts to return list.");
					matRetValues.addAll(batchReply.getReplyElements().getMaterialReqmntsServiceRetrieveReplyDTO());
				}
			} catch (com.mincom.enterpriseservice.ellipse.materialreqmnts.EnterpriseServiceOperationException esoe) {

			}
			logger.debug("ProjectScheduleAction::deleteExistingMaterialResource() - MaterialReqmnts batch loop complete.");
		}

		// If any material requirements already exist in Ellipse, then delete them all
		if (matRetValues.size() > 0) {
			for (int i = 0; i < matRetValues.size(); i++) {
				Object obj = matRetValues.get(i);
				MaterialReqmntsServiceDeleteRequestDTO delMatReq = new MaterialReqmntsServiceDeleteRequestDTO();
				if (obj instanceof MaterialReqmntsServiceRetrieveReplyDTO) {
					MaterialReqmntsServiceRetrieveReplyDTO matReqDTO = (MaterialReqmntsServiceRetrieveReplyDTO) obj;
					delMatReq.setSeqNo(matReqDTO.getSeqNo());
					ObjectDataHelper.defaultData(delMatReq, matRet, false);
				}
				matDeleteList.getMaterialReqmntsServiceDeleteRequestDTO().add(delMatReq);
			}
			if (matDeleteList.getMaterialReqmntsServiceDeleteRequestDTO().size() > 0) {
				MaterialReqmntsServiceDeleteReplyCollectionDTO replyDelete = matReqService.multipleDelete(oc, matDeleteList);
				inspectReplyForErrors(replyDelete, "MaterialReqments.multipleDelete");
				addResultToResponse(msg, "OK", "Delete All Material Requirements", "Deleted exiting material resources.");
			}
		}
		logger.debug("ProjectScheduleAction::deleteExistingMaterialResource() - End");		
	}
	
	
	/**
	 * method to create the multiple Material resources
	 * @author C16950
	 * @param mateReqModList
	 * @param matReqService
	 * @param oc
	 */
	private void createMaterialReqirements(ArrayOfMaterialReqmntsServiceModifyRequestDTO mateReqModList, MaterialReqmnts matReqService, OperationContext oc, Message msg) throws Exception {
		logger.debug("UpdateProjectScheduleAction::createMaterialReqirements(MaterialReqmnts) - Begin");
		
		ArrayOfMaterialReqmntsServiceCreateRequestDTO matCreateList = new ArrayOfMaterialReqmntsServiceCreateRequestDTO();
		
		for (MaterialReqmntsServiceModifyRequestDTO materialDTO : mateReqModList.getMaterialReqmntsServiceModifyRequestDTO()) {
			//If stock code is not set, then create the material resources
			if(StringUtils.isEmpty(materialDTO.getStockCode())){
				//Mnemonic must always be SUCCESS from the request messages, otherwise throw an error
				if( StringUtils.equals(materialDTO.getMnemonic(), "SUCCESS") && StringUtils.isNotBlank(materialDTO.getPartNo()) ){
					
					MaterialReqmntsServiceCreateRequestDTO createMatReq = new MaterialReqmntsServiceCreateRequestDTO();
					
					createMatReq.setJEItemNo(materialDTO.getJEItemNo());
					createMatReq.setUOM(materialDTO.getUOM());
					createMatReq.setCatalogueFlag(false);
					createMatReq.setClassType("JE");
					createMatReq.setContestibleFlag(false);
					createMatReq.setEnteredInd("E");
					createMatReq.setEstimateNo(materialDTO.getEstimateNo());
					createMatReq.setMatDesc1(materialDTO.getMatDesc1());
					createMatReq.setMnemonic(materialDTO.getMnemonic());
					createMatReq.setPartNo(materialDTO.getPartNo());
					createMatReq.setQuantityRequired(materialDTO.getQuantityRequired());
					//createMatReq.setRateAmount(materialDTO.getRateAmount());
					createMatReq.setUnitQuantityReqd(materialDTO.getUnitQuantityReqd());
					//createMatReq.setFixedAmount(materialDTO.getFixedAmount());
					if(materialDTO.getFixedAmount().compareTo(BigDecimal.ZERO)>0){
						createMatReq.setEnteredInd("D");
						createMatReq.setFixedAmount(materialDTO.getFixedAmount());
					}else if(materialDTO.getRateAmount().compareTo(BigDecimal.ZERO)>0){
						createMatReq.setRateAmount(materialDTO.getRateAmount());
					}
					createMatReq.setVersionNo(materialDTO.getVersionNo());

					//verify UOM value is existed in TableCode, if not exist set UOM to EA by default					
					try{
						TableServiceReadRequestDTO req = new TableServiceReadRequestDTO();
						req.setTableCode(materialDTO.getUOM());
						req.setTableType("UM");
						tableService.read(oc, req).getDescription();
					}catch(Exception e){
						if (extractError(e, TABLE_CODE_NOT_EXIST) != null){
							logger.info("Table code {} is not exist and setting table code to EA.",materialDTO.getUOM());
							createMatReq.setUOM("EA");
						}
					}
					matCreateList.getMaterialReqmntsServiceCreateRequestDTO().add(createMatReq);	
					
				}else{
					throw new Exception("Mnemonic must always be SUCCESS and Part Number should not be empty for the job estimate id "+materialDTO.getEstimateNo()+" and Item No "+materialDTO.getJEItemNo()+" and Material Resource ID "+materialDTO.getSeqNo());
				}
			}
		}

		MaterialReqmntsServiceCreateReplyCollectionDTO result = matReqService.multipleCreate(oc, matCreateList);
		String details = "Created " + result.getReplyElements().getMaterialReqmntsServiceCreateReplyDTO().size() + " material resources.";
        addResultToResponse(msg, "OK", "MaterialReqmntsResrouce-Create", details);
		
		logger.debug("UpdateProjectScheduleAction::createMaterialReqirements(MaterialReqmnts) - End");
	}
	/**
     * This determines which equipment requirements are not already created.
     * @param equipReqService - The EquipmentReqmnts service to use. This must not be null.
     * @param oc - The operation context to use during the web service calls.
     * @param modList - Array of EquipmentReqmntsService to check.
     * @return The list of indexes that are not found in the web service.
     * @throws com.mincom.enterpriseservice.ellipse.equipmentreqmnts.EnterpriseServiceOperationException - Error during web service call.
     */
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
    
    /**
     * This determines which resource (labor) requirements are not already created.
     * @param resReqService - The ResourceReqmnts service to use. This must not be null.
     * @param oc - The operation context to use during the web service calls.
     * @param modList - Array of ResourceReqmnts to check.
     * @return The list of indexes that are not found in the web service.
     * @throws com.mincom.enterpriseservice.ellipse.resourcereqmnts.EnterpriseServiceOperationException - Error during web service call.
     */
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