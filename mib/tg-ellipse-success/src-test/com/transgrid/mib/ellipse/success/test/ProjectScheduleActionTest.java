package com.transgrid.mib.ellipse.success.test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jboss.soa.esb.actions.ActionProcessingException;
import org.jboss.soa.esb.listeners.message.MessageDeliverException;
import org.jboss.soa.esb.message.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mincom.mib.common.StandardResponse;
import com.mincom.mib.ellipse.EllipseConstants;
import com.mincom.mib.ellipse.projectmanagement.ProjectScheduleAction;
import com.mincom.mib.test.EllipseUnitDriver;

public class ProjectScheduleActionTest extends EllipseUnitDriver {
	
	public Logger logger = LoggerFactory.getLogger(ProjectScheduleActionTest.class);
	
	private static String GetInputFile = "./test-data/GetProjectSchedule.xml";
	
	private static String ProjectScheduleGetMappingConfig = "./esbcontent/transforms/ellipse/ProjectScheduleGetMapping.xml";
	
	private static String ProjectScheduleShowMappingConfig = "./esbcontent/transforms/ellipse/ProjectScheduleShowMapping.xml";
	
	/**
	 * Executed before each test is run.
	 */
	@Before
	public void setUp() {
		logger.debug("ProjectScheduleActionUnitTest::setUp() - Begin test:");
        setConnectionInfo(getProperty("ellipse.user"),
                getProperty("ellipse.password"),
                getProperty("ellipse.serviceUrl"));
        setupConversation("DUMMY12","GRID");
        logger.debug("ProjectScheduleActionUnitTest::setUp() - startTransaction");
        startTransaction();
        // This will disable validation if that causes an error. 
        //  Usually an error is caused when the web service api/variables are changed without our knowledge.
        config.setAttribute("disableReplyValidation", "true");
	}
	
	/**
	 * Executed when each test is finished.
	 */
	@After
	public void tearDown() {
    	logger.debug("ProjectScheduleActionUnitTest::tearDown() - Begin, finishing test:");
    	logger.debug("ProjectScheduleActionUnitTest::tearDown() - rollbackTransaction()");
    	rollbackTransaction();
        logger.debug("ProjectScheduleActionUnitTest::tearDown() - Test Finished");
	}
	
	@Test
	public void testGet() {
		config.setAttribute("TestFlag", "false");
//		createTestData();

        try {
            // Execute the mapping:
    		Map<String, Object> dataObjects = executeMapping(GetInputFile, ProjectScheduleGetMappingConfig);
    		
    		// Create the message:
            Message message = constructMessage(dataObjects, "exports");
            
            // Create the action:
            ProjectScheduleAction action = new ProjectScheduleAction(config);
            action.setScratchPadData( message, EllipseConstants.CONVERSATION_CONTEXT, conversation );
            action.setScratchPadData(message, EllipseConstants.OPERATION_CONTEXT, oc);
            
            //Send get request
            message = action.get(message);
            assertNotNull(message);
            assertNotNull(getReplyDTOs(message));
            
            // Pull data from the message:
            StandardResponse response = action.getResponse(message);
            boolean success = handleResponse(response);
            assertTrue(success);
            // Pull the data from the message:
            Map<String, Object> replyDTOs = getReplyDTOs(message);
            // Pull the contract list from the map:
//            @SuppressWarnings("unchecked")
//            ArrayList<ProjectSchedule> replySchedule = (ArrayList<ProjectSchedule>)replyDTOs.get("exportResults");
            // Assert that only 1 contract was retrieved:
//            assertTrue(replyContracts.size() == 1);
            // Check that the retrieved contract was the same one that was expected:
//            String responseID = replyContracts.get(0).getContractHeader().getContractNo();
//            assertEquals(expectedIDs.get(0), responseID);
//            assertTrue(replySchedule.get(0).getScheduleActivities().size() == 3);
            
            String showMessage = executeJavaMapping(replyDTOs, ProjectScheduleShowMappingConfig);
            
            logger.debug("ProjectScheduleActionUnitTest::testGet() - results of show map follows:");
            logger.debug(showMessage);
            // Test mapping
//            int beg = showMessage.indexOf(expectedIDs.get(0));
//            if(beg == -1) {
//                logger.debug("ContractUnitTest::testGet() - Show mapping failed, could not find the ID.");
//                fail("ContractUnitTest::testGet() - Show mapping failed, could not find the ID.");
//            }
//            String convertedId = showMessage.substring(beg, beg + expectedIDs.get(0).length() );
//            assertEquals(expectedIDs.get(0), convertedId);
        } catch (MessageDeliverException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("MessageDeliverException inside ProjectScheduleActionUnitTest::testGet()");
        } catch (ActionProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("ActionProcessingException inside ProjectScheduleActionUnitTest::testGet()");
        }
		
	}

}
