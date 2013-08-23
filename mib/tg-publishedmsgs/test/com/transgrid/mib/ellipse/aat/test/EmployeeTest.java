package com.transgrid.mib.ellipse.aat.test;

import java.util.Map;


import org.jboss.soa.esb.message.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.mincom.mib.ellipse.EllipseConstants;
import com.mincom.mib.test.EllipseUnitDriver;

import com.thoughtworks.xstream.XStream;

public class EmployeeTest extends EllipseUnitDriver
{
	public EmployeeTest()
	{
		super();
	}

	@Before
	public void setUp() throws Exception {
		setConnectionInfo("C16748", "tgsep12", "http://vsvoel57x006:8080/ews/services/");
		setupConversation("", "GRID");
		config.setAttribute("disableReplyValidation", "true");
	}

	@After
	public void tearDown() throws Exception {
		conversation.stop(oc);
	}
	
	@Test
	public void testAction()
	{
		try
		{
			
			Map dataObjects = invokeInputMapping();
			Message message = constructMessage(dataObjects, "RequestDTOs");
			//message.getProperties().getProperty("type");
			com.transgrid.mib.ellipse.hr.action.EmployeeAction action = new com.transgrid.mib.ellipse.hr.action.EmployeeAction(config);			
			action.setScratchPadData(message, EllipseConstants.CONVERSATION_CONTEXT, conversation);
			action.setScratchPadData(message, EllipseConstants.OPERATION_CONTEXT, oc);

			message = action.process(message);
			
		
			//StandardResponse response = action.getResponse(message);
			//System.out.println("THE RESPONSE IS >>> "+message.getBody().toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	protected Map invokeInputMapping()
	{
		String inputFile = "./test-data/GetEmployeeDetails.xml";
		String configFile = "./esbcontent/transforms/EmployeeGetMapping.xml";

		Map results = this.executeMapping(inputFile, configFile);
		return results;
	}

	//@Test
	public void testMapping()
	{
		String inputFile = "./test-data/GetEmployeeDetails.xml";
		String configFile = "./esbcontent/transforms/EmployeeGetMapping.xml";
		
		//Map results = invokeInputMapping();
		Map results = this.executeMapping(inputFile, configFile);
		
		XStream x = new XStream();
		System.out.println(x.toXML(results));
	}
	
}
