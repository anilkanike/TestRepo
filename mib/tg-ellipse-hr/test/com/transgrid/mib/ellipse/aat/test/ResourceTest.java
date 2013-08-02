package com.transgrid.mib.ellipse.aat.test;

import java.util.Map;


import org.jboss.soa.esb.message.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mincom.mib.common.StandardResponse;
import com.mincom.mib.ellipse.EllipseConstants;
import com.mincom.mib.test.EllipseUnitDriver;

import com.thoughtworks.xstream.XStream;
import com.transgrid.mib.ellipse.hr.action.ResourceAction;


public class ResourceTest extends EllipseUnitDriver
{
	public ResourceTest()
	{
		super();
	}

	@Before
	public void setUp() throws Exception {
		setConnectionInfo("C16748", "tgoct12", "http://vsvoel57x006:8080/ews/services/");
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
    		startTransaction();
    		String inputFile = null;
    		String mappingConfigFile = null;		
    		Map<String, Object> dataObjects = null;
    		
    		mappingConfigFile = "./esbcontent/transforms/ellipse/inbound/ResourceSyncMapping.xml";
    		inputFile = "./test-data/EmpProcessResource.xml";
    		dataObjects = executeMapping(inputFile,mappingConfigFile);
    		XStream x = new XStream();
    		System.out.println(x.toXML(dataObjects));
        	
            Message message = constructMessage( dataObjects, "RequestDTOs" );

            ResourceAction action = new ResourceAction( config );
            action.setScratchPadData( message, EllipseConstants.CONVERSATION_CONTEXT, conversation );
            action.setScratchPadData( message, EllipseConstants.OPERATION_CONTEXT, oc );

            message = action.sync(message);

			StandardResponse response = action.getResponse(message);
			commitTransaction();
			System.out.println("THE RESPONSE IS >>> "+response.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	protected Map invokeInputMapping()
	{
		String configFile = "./esbcontent/transforms/ellipse/inbound/ResourceSyncMapping.xml";
		String inputFile = "./test-data/EmpProcessResource.xml";

		Map results = this.executeMapping(inputFile, configFile);
		return results;
	}

	@Test
	public void testMapping()
	{
		String configFile = "./esbcontent/transforms/ellipse/inbound/ResourceSyncMapping.xml";
		String inputFile = "./test-data/EmpProcessResource.xml";
		
		//Map results = invokeInputMapping();
		Map results = this.executeMapping(inputFile, configFile);
		
		XStream x = new XStream();
		System.out.println(x.toXML(results));
	}
	
}
