package com.transgrid.mib.ellipse.aat.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.jboss.soa.esb.message.Message;
import org.jboss.soa.esb.message.MessagePayloadProxy;
import org.jboss.soa.esb.message.format.MessageFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mincom.mib.ellipse.EllipseConstants;
import com.mincom.mib.test.EllipseUnitDriver;


public class TrainingResultsTest extends EllipseUnitDriver {
	// protected ExchangeRateAction action;
	protected String inputFile;
	protected String mappingConfigFile;
	protected Map<String, Object> dataObjects;
	MessagePayloadProxy messagePayloadProxy = null;

	@Before
	public void setUp() throws Exception {
		setConnectionInfo("C16748", "tgsep12", "http://vsvoel57x006:8080/ews/services/");
		//setConnectionInfo("C16748", "tgsep12", "http://vsvoel57x015:8080/ews/services/");
		 
		setupConversation("DUMMY12", "GRID");
		config.setAttribute("disableReplyValidation", "true");
	}

	@After
	public void tearDown() throws Exception {
		conversation.stop(oc);
	}

	private Message buildMessage(String xml) throws Exception {
		// config.setAttribute("set-payload-location", payloadLocation);
		// config.setAttribute("get-payload-location", payloadLocation);
		messagePayloadProxy = new MessagePayloadProxy(config);
		Message message = MessageFactory.getInstance().getMessage();
		messagePayloadProxy.setPayload(message, xml);
		return message;
	}

	@Test
	public void testGetWithEventMsg() {

		try {
			File file = new File("test-data/TrainingResultsEventMsg.xml");
			InputStream sourceStream = new FileInputStream(file);
			String xml = new java.util.Scanner(sourceStream).useDelimiter("\\A").next();
			Message msg = buildMessage(xml);
			com.transgrid.mib.ellipse.hr.action.TrainingResultsAction action = new com.transgrid.mib.ellipse.hr.action.TrainingResultsAction(config);
			action.setScratchPadData(msg, EllipseConstants.CONVERSATION_CONTEXT, conversation);
			action.setScratchPadData(msg, EllipseConstants.OPERATION_CONTEXT, oc);
			msg = action.process(msg);			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
