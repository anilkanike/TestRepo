package com.transgrid.mib.ellipse.hr.common;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author C16950
 *
 */
public class EllipseTopicClient {
	TopicConnectionFactory tcf = null;
	TopicSession session;
	Topic topic;
	TopicConnection tc;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public void setupConnection() throws JMSException, NamingException {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, System.getProperty("ellipse.event.jms.url"));
		InitialContext iniCtx = new InitialContext(props);

		tcf = (TopicConnectionFactory) iniCtx.lookup("ConnectionFactory");
		topic = (Topic) iniCtx.lookup(System.getProperty("ellipse.event.jms.topic"));
		logger.info("Ellipse topic connection has been established..");
	}

	public void close() throws JMSException {
		tc.stop();
		session.close();
		tc.close();
	}

	public void sendMessage(String msg) throws JMSException {
		tc = tcf.createTopicConnection();
		session = tc.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		TopicPublisher tp = session.createPublisher(topic);
		ObjectMessage tm = session.createObjectMessage(msg);
		tm.setStringProperty("type", "update");
		tm.setStringProperty("table", "MSF810");
		tp.publish(tm);
		logger.info("Message has been published to ellipse Topic..");
	}
	
}