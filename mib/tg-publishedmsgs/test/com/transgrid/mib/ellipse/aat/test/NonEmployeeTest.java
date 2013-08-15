package com.transgrid.mib.ellipse.aat.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;


import org.jboss.soa.esb.message.Message;
import org.jboss.soa.esb.message.MessagePayloadProxy;
import org.jboss.soa.esb.message.format.MessageFactory;
import org.junit.Test;

import com.mincom.mib.ellipse.EllipseConstants;
import com.mincom.mib.test.EllipseUnitDriver;

import com.thoughtworks.xstream.XStream;

public class NonEmployeeTest extends EllipseUnitDriver {
	//protected ExchangeRateAction action;
	protected String inputFile;
	protected String mappingConfigFile;
	protected Map<String,Object> dataObjects;
	
	@Test
	public void testGet() {
		
		this.setConnectionInfo("C16748", "tgsep12", "http://vsvoel57x006:8080/ews/services/");
		this.setupConversation("DUMMY12", "GRID");
		
		inputFile = "./test-data/NonEmployeeEventMsg.xml";
		mappingConfigFile = "./esbcontent/transforms/NonEmployeeMessageMapping.xml";
		//Map results = this.executeMapping(inputFile, mappingConfigFile);
		
		dataObjects = executeMapping(inputFile,mappingConfigFile);
	
		try {
			this.oc.setMaxInstances(2);
			Message message = constructMessage(dataObjects,"RequestDTOs");
			message.getProperties().setProperty("type", "update");
			
			//System.out.println("<--NON-EMPLOYEE TARGET XML--> "+response.toString());

		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}	
	
	@Test
	public void testGetWithEventMsg() {

		try {
			File file = new File("test-data/NonEmployeeEventMsg.xml");
			InputStream sourceStream = new FileInputStream(file);
			String xml = new java.util.Scanner(sourceStream).useDelimiter("\\A").next();
			Message msg = buildMessage(xml);
			com.transgrid.mib.ellipse.hr.action.NonEmployeeAction action = new com.transgrid.mib.ellipse.hr.action.NonEmployeeAction(config);
			action.setScratchPadData(msg, EllipseConstants.CONVERSATION_CONTEXT, conversation);
			action.setScratchPadData(msg, EllipseConstants.OPERATION_CONTEXT, oc);
			msg = action.process(msg);			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Message buildMessage(String xml) throws Exception{
		//config.setAttribute("set-payload-location", payloadLocation);
		//config.setAttribute("get-payload-location", payloadLocation);
		MessagePayloadProxy messagePayloadProxy = new MessagePayloadProxy(config);
		Message message = MessageFactory.getInstance().getMessage();
		messagePayloadProxy.setPayload(message, xml);
		return message;
	}
	
	@Test
	public void testMapping()
	{
		String inputFile = "./test-data/NonEmployeeEventMsg.xml";
		String configFile = "./esbcontent/transforms/NonEmployeeMessageMapping.xml";
		
		//Map results = invokeInputMapping();
		Map results = this.executeMapping(inputFile, configFile);
		
		XStream x = new XStream();
		System.out.println(x.toXML(results));
	}
}
