package com.transgrid.mib.ellipse.success.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SendInboundMessage {
	QueueConnection conn;
	QueueSession session;
	Queue que;

	public void setupConnection() throws JMSException, NamingException {
		Properties properties1 = new Properties();
		properties1.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		properties1.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		properties1.put(Context.PROVIDER_URL, "jnp://localhost:1199");
		InitialContext iniCtx = new InitialContext(properties1);

		Object tmp = iniCtx.lookup("ConnectionFactory");
		QueueConnectionFactory qcf = (QueueConnectionFactory) tmp;
		conn = qcf.createQueueConnection();
		que = (Queue) iniCtx.lookup("queue/TG.ELLIPSE-SUCCESS.IN");
		// que = (Queue) iniCtx.lookup("queue/TG.COMMON.GW");
		session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		conn.start();
		System.out.println("Connection Started...");
	}

	public void stop() throws JMSException {
		conn.stop();
		session.close();
		conn.close();
	}

	public void sendAMessage(String message) throws Exception {

		QueueSender send = session.createSender(que);
		ObjectMessage tm;

		tm = session.createObjectMessage(message);
		tm.setStringProperty("ReceiverID", "TG-ELLIPSE");
		send.send(tm);
		send.close();

	}

	public static void main(String args[]) throws Exception {
		//File file = new File("test-data/ProcessProjectSchedule-Update-Sandpit.xml");
		File file = new File("test-data/ProcessProjectSchedule-Update-JE.xml");
		InputStream sourceStream = new FileInputStream(file);
		String xml = new java.util.Scanner(sourceStream).useDelimiter("\\A").next();
		SendInboundMessage sm = new SendInboundMessage();
		sm.setupConnection();
		sm.sendAMessage(xml);
		// sm.sendAMessage("*********HEllo this is my test message**********");
		sm.stop();
	}

}