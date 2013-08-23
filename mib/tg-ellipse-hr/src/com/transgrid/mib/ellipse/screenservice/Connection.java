package com.transgrid.mib.ellipse.screenservice;

import com.mincom.enterpriseservice.screen.Screen;
import com.mincom.ews.client.EWSClientConversation;
import com.mincom.ews.service.connectivity.OperationContext;

public class Connection {
	
	private String user;
	private String password;
	private String district;
	private String position;
	private String url;
	private EWSClientConversation client;
	private Screen screen;
	private OperationContext oc;
	
	public Connection() throws Exception {
		//temp values
		district = "GRID";
		position = "DUMMY12";
		user = "c16748";
		password = "tgoct12";
		url = "http://vsvoel57x015:8080/ews/services/";
		
		oc = new OperationContext();   
	    oc.setDistrict(district);   
	    oc.setPosition(position);
	}
	
	public EWSClientConversation getWSConn(){
		client = new EWSClientConversation();
		client.start(url);
		client.authenticate(user,password);		
		return client;
	}
	
	public Screen getScreen() throws Exception{
		client = new EWSClientConversation();
		client.start(url);
		client.authenticate(user,password);		
		screen = (Screen) client.createService(Screen.class);
		return screen;
	}
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public EWSClientConversation getClient() {
		return client;
	}
	public void setClient(EWSClientConversation client) {
		this.client = client;
	}
	/*public Screen getScreen() {
		return screen;
	}*/
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	public OperationContext getOc() {
		return oc;
	}
	public void setOc(OperationContext oc) {
		this.oc = oc;
	}
	
	
}
