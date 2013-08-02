package com.transgrid.mib.ellipse.aat.test;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSubscriber;
import javax.jms.TopicSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DurableTopicRecvClient {
	TopicConnection conn = null;
	TopicSession session = null;
	Topic topic = null;

	public void setupPubSub() throws JMSException, NamingException {
		Properties properties1 = new Properties();
		properties1.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		properties1.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		properties1.put(Context.PROVIDER_URL, "jnp://vsvoel57x006:1199");
		InitialContext iniCtx = new InitialContext(properties1);

		Object tmp = iniCtx.lookup("ConnectionFactory");

		TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
		//conn = tcf.createTopicConnection("guest", "guest");
		conn = tcf.createTopicConnection();
		//conn.setClientID("tg-hr-outbound");
		topic = (Topic) iniCtx.lookup("topic/EllipseServices");
		conn.setClientID("TestID");
		session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		conn.start();
	}

	public void recvSync() throws JMSException, NamingException {
		System.out.println("Begin recvSync");
		// Setup the pub/sub connection, session
		setupPubSub();
		// Wait upto 5 seconds for the message
		TopicSubscriber recv = session.createDurableSubscriber(topic,"AnilSubsriber");
		Message msg = recv.receive(10000);
		if (msg == null) {
			System.out.println("Timed out waiting for msg");
		} else {
			System.out.println("DurableTopicRecvClient.recv, msgt=" + msg);
		}
	}

	public void stop() throws JMSException {
		conn.stop();
		session.close();
		conn.close();
	}

	public static void main(String args[]) throws Exception {
		System.out.println("Begin DurableTopicRecvClient, now=" + System.currentTimeMillis());
		DurableTopicRecvClient client = new DurableTopicRecvClient();
		while(true){
			client.recvSync();
			client.stop();
		}
		//System.out.println("End DurableTopicRecvClient");
		//System.exit(0);
	}

}