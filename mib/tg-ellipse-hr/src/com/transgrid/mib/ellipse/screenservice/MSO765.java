package com.transgrid.mib.ellipse.screenservice;

import com.mincom.enterpriseservice.screen.Screen;
import com.mincom.ews.service.connectivity.OperationContext;
import com.transgrid.mib.ellipse.hr.common.CommonUtil;
import com.transgrid.mib.ellipse.hr.domain.EllipseResource;

import org.h2.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to invoke the MSO765 screen services for Employee resource details
 * 
 * @author Anil Kanike
 * 
 */
public class MSO765 extends BaseScreenAction {
	
	public Logger logger = LoggerFactory.getLogger(MSO765.class);

	// "resource type already exists" error
	private static final String ALREADY_EXIST = "2618";
	// "employee terminated" warning
	private static final String EMPLOYEE_TERMINATED = "3649";
	// competancey level does not exist
	private static final String COMPETENCY_LEVEL_NOT_EXIST = "8521";
	// Warning: Date entered < current effective date
	private static final String WRONG_EFFECTIVE_DATE = "8178";
	//Error: Date must be changed - 8286
	private static final String DATE_MUST_BE_CHANGED = "8286";
	private static final String MSO765 = "MSO765";

	EllipseResource empResource = null;

	public MSO765(Screen screen, OperationContext oc, EllipseResource empResource) throws Exception {
		super(screen, oc);
		this.empResource = empResource;
	}

	@Override
	public void exec() throws Exception {

		try {
			resetToMenu();
			executeScreen(MSO765);

			if (!err.isError()) {
				create();
			} else {
				doErrorAction();
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void create() throws Exception {
		logger.info("Create new resource type for employee {}",empResource.getId());
		lScreenDTO.clear();
		if (dtoScreen != null && dtoScreen.getMapName().equalsIgnoreCase("MSM765A")) {
			logger.info("Setting screen fields to MSM765A");
			lScreenDTO.add(setScreenFields("OPTION1I", "1"));
			lScreenDTO.add(setScreenFields("EMPLOYEE1I", empResource.getId()));
			lScreenDTO.add(setScreenFields("RES_CLASS1I", empResource.getResourceClass()));
			lScreenDTO.add(setScreenFields("RES_CODE1I", empResource.getResourceCode()));
			arrDTO.setScreenNameValues(lScreenDTO);
			submitScreen(SUBMIT_KEY, arrDTO);

			if (!err.isError()) {
				lScreenDTO.clear();
				if (dtoScreen != null && dtoScreen.getMapName().equalsIgnoreCase("MSM765C")) {
					logger.info("Setting screen fields to MSM765C");
					lScreenDTO.add(setScreenFields("COMP_LEVEL3I", empResource.getCompetencyLevel()));
					lScreenDTO.add(setScreenFields("EFFECT_DATE3I", CommonUtil.now()));
					lScreenDTO.add(setScreenFields("GAINED_DATE3I", empResource.getEffectiveDate()));
					lScreenDTO.add(setScreenFields("REV_EXP_DATE3I", empResource.getExpiryDate()));
					//update Authorized By details with today's date (issue - 2256 fix).
					lScreenDTO.add(setScreenFields("AUTH_IND3I", empResource.getEmpNonEmpIndicator()));
					lScreenDTO.add(setScreenFields("AUTH_BY3I", empResource.getAuthorisedBy()));
					lScreenDTO.add(setScreenFields("AUTH_DATE3I", CommonUtil.now()));
					arrDTO.setScreenNameValues(lScreenDTO);
					submitScreen(SUBMIT_KEY, arrDTO);
				}

				doErrorAction(); // throw error/warnings from MSM765C screen
				submitScreen(SUBMIT_KEY); // double submit for new record creation
			} else if (err.getErrCode().equals(ALREADY_EXIST)) {
				cancel();
				update();
			} else {
				doErrorAction();
			}			
		}
	}

	@Override
	public void update() throws Exception {
		logger.info("Update new resource type for employee {}",empResource.getId());
		lScreenDTO.clear();
		// ScreenNameValues arrDTO = new ScreenNameValues();
		if (dtoScreen != null && dtoScreen.getMapName().equalsIgnoreCase("MSM765A")) {
			logger.info("Setting screen fields to MSM765A");
			lScreenDTO.add(setScreenFields("OPTION1I", "2"));
			lScreenDTO.add(setScreenFields("EMPLOYEE1I", empResource.getId()));
			lScreenDTO.add(setScreenFields("RES_CLASS1I", empResource.getResourceClass()));
			lScreenDTO.add(setScreenFields("RES_CODE1I", empResource.getResourceCode()));
			arrDTO.setScreenNameValues(lScreenDTO);
			submitScreen(SUBMIT_KEY, arrDTO);

			if (!err.isError() || !err.isWarning()) {
				lScreenDTO.clear();
				if (dtoScreen != null && dtoScreen.getMapName().equalsIgnoreCase("MSM765C")) {
					logger.info("Setting screen fields to MSM765C");
					lScreenDTO.add(setScreenFields("COMP_LEVEL3I", empResource.getCompetencyLevel()));
					lScreenDTO.add(setScreenFields("EFFECT_DATE3I", CommonUtil.now()));
					lScreenDTO.add(setScreenFields("GAINED_DATE3I", empResource.getEffectiveDate()));
					lScreenDTO.add(setScreenFields("REV_EXP_DATE3I", empResource.getExpiryDate()));
					//update Authorized By details with today's date (issue - 2256 fix).
					lScreenDTO.add(setScreenFields("AUTH_IND3I", empResource.getEmpNonEmpIndicator()));
					lScreenDTO.add(setScreenFields("AUTH_BY3I", empResource.getAuthorisedBy()));
					lScreenDTO.add(setScreenFields("AUTH_DATE3I", CommonUtil.now()));
					arrDTO.setScreenNameValues(lScreenDTO);
					submitScreen(SUBMIT_KEY, arrDTO);
					//this logic is to delete the current record and recreate the new record when Competency level is update
					if (err.isError() && StringUtils.equals(err.getErrCode(), DATE_MUST_BE_CHANGED)){
						logger.info("Competency level is changed and trying to delete the current record to update new record..");
						//cancel();
						resetToMenu();
						executeScreen(MSO765);
						delete();
					}else{
						doErrorAction();
					}
				}
				submitScreen(SUBMIT_KEY); // double submit for update the record
				//doErrorAction();
			} else {
				doErrorAction();
			}
		}
	}

	@Override
	public void delete() throws Exception {
		logger.info("Delete existng current resource type for employee {}",empResource.getId());
		lScreenDTO.clear();
		if (dtoScreen != null && dtoScreen.getMapName().equalsIgnoreCase("MSM765A")) {
			logger.info("Setting screen fields to MSM765A for delete..");
			lScreenDTO.add(setScreenFields("OPTION1I", "3"));
			lScreenDTO.add(setScreenFields("EMPLOYEE1I", empResource.getId()));
			lScreenDTO.add(setScreenFields("RES_CLASS1I", empResource.getResourceClass()));
			lScreenDTO.add(setScreenFields("RES_CODE1I", empResource.getResourceCode()));
			arrDTO.setScreenNameValues(lScreenDTO);
			submitScreen(SUBMIT_KEY, arrDTO);
			//submitScreen(SUBMIT_KEY);
			lScreenDTO.clear();
			if (dtoScreen != null && dtoScreen.getMapName().equalsIgnoreCase("MSM765C")) {
				logger.info("Setting screen field DELETE3I to MSM765C for delete the current record..");
				lScreenDTO.add(setScreenFields("DELETE3I", "Y"));
				submitScreen(SUBMIT_KEY, arrDTO);
				logger.info("Recreating the resource record after delete the current record..");
				exec();
			}
		}
	}
	
	private void doErrorAction() throws Exception {
		if (err.isError() || err.isWarning()) {
			throw new Exception("Screen Service Error: " + err.getErrCode() + " " + err.getErrMsg());
		}
	}
}
