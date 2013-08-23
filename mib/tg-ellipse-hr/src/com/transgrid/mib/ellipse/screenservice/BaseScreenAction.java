package com.transgrid.mib.ellipse.screenservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mincom.enterpriseservice.screen.ArrayOfScreenNameValueDTO;
import com.mincom.enterpriseservice.screen.Screen;
import com.mincom.enterpriseservice.screen.ScreenDTO;
import com.mincom.enterpriseservice.screen.ScreenFieldDTO;
import com.mincom.enterpriseservice.screen.ScreenNameValueDTO;
import com.mincom.enterpriseservice.screen.ScreenSubmitRequestDTO;
import com.mincom.ews.service.connectivity.OperationContext;

/**
 * @author Anil Kanike
 * 
 */
public abstract class BaseScreenAction {
	
	public Logger logger = LoggerFactory.getLogger(BaseScreenAction.class);

	public static String SUBMIT_KEY = "1";
	public static String UPDATE_KEY = "2";
	public static String DELETE_KEY = "3";
	public static String CANCEL_KEY = "4";
	public static String HOME_KEY = "5";

	public OperationContext oc = null;
	public Screen screen = null;
	public Error err = null;
	public ScreenDTO dtoScreen = null;
	List<ScreenFieldDTO> sFields = null;
	protected ScreenNameValues arrDTO = new ScreenNameValues();
	protected List<ScreenNameValueDTO> lScreenDTO = new ArrayList<ScreenNameValueDTO>();

	public BaseScreenAction(){}
	
	public BaseScreenAction(Screen screen, OperationContext oc) throws Exception {
		this.oc = oc;
		this.screen = screen;
	}

	public void resetToMenu() throws Exception {
		if (screen != null) {
			logger.info("District: "+oc.getDistrict()+":"+oc.getPosition());
			screen.positionToMenu(oc);
		} else {
			return;
		}
	}

	public void executeScreen(String mso) throws Exception {
		logger.info("Executing screen {} ",mso);
		dtoScreen = screen.executeScreen(oc, mso);
		logger.info("SCREEN DTO OBJECTS SIZE.." + dtoScreen.getScreenFields().getScreenFieldDTO().size());
		err = new Error(dtoScreen.getMessage());
	}

	public void mainScreen() throws Exception {
		if (dtoScreen != null) {
			if (!dtoScreen.getMapName().trim().equals("")) {
				submitScreen(HOME_KEY);
			}
		}
	}

	public void submitScreen(String key) throws Exception {
		ScreenSubmitRequestDTO dto = new ScreenSubmitRequestDTO();
		dto.setScreenKey(key);
		screen.submit(oc, dto);
	}

	public void submitScreen(String key, ArrayOfScreenNameValueDTO adto) throws Exception {
		ScreenSubmitRequestDTO dto = new ScreenSubmitRequestDTO();

		if (adto != null) {
			dto.setScreenFields(adto);
		}
		dto.setScreenKey(key);
		dtoScreen = screen.submit(oc, dto);
		sFields = dtoScreen.getScreenFields().getScreenFieldDTO();
		err = new Error(dtoScreen.getMessage());
		// adto = null;
	}

	public void cancel() throws Exception {
		if (dtoScreen != null) {
			if (!dtoScreen.getMapName().trim().equals("")) {
				submitScreen(CANCEL_KEY);
				/*
				 * if (dtoScreen != null) { submitScreen(HOME_KEY); }
				 */
			}
		}
	}

	/*
	 * public boolean isGridRowFound(String gridScreen){ if(dtoScreen != null &&
	 * dtoScreen.getMapName().equalsIgnoreCase(gridScreen)){ while(){ } } return
	 * false; }
	 */

	public ScreenNameValueDTO setScreenFields(String fieldName, String value) {
		ScreenNameValueDTO snDTO = new ScreenNameValueDTO();
		snDTO.setFieldName(fieldName);
		snDTO.setValue(value);
		return snDTO;
	}

	// public void

	public void updateScreenField(String fieldName, String fieldValue) {

		// List<ScreenFieldDTO> sFields =
		// dtoScreen.getScreenFields().getScreenFieldDTO();
		for (int i = 0; i < sFields.size(); i++) {
			if (sFields.get(i).getFieldName().equalsIgnoreCase(fieldName)) {
				sFields.get(i).setValue(fieldValue);
				break;
			}
		}
	}

	public String validate(String fieldName) {
		for (int i = 0; i < sFields.size(); i++) {
			if (sFields.get(i).getFieldName().equalsIgnoreCase(fieldName)) {
				return sFields.get(i).getValue();
			}
		}
		return "";
	}

	// begin method for calling screen services
	abstract public void exec() throws Exception;

	abstract public void create() throws Exception;

	abstract public void update() throws Exception;

	abstract public void delete() throws Exception;

};

final class ScreenNameValues extends ArrayOfScreenNameValueDTO {
	private static final long serialVersionUID = 1L;

	public void setScreenNameValues(List<ScreenNameValueDTO> listDTO) {
		screenNameValueDTO = listDTO;
	}

}
