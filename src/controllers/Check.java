package controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import models.User;

import com.opensymphony.xwork2.ActionSupport;

import utilities.Dao;

public class Check extends ActionSupport {
	
	private static final long serialVersionUID = 113L;
	
	private Dao dao;
	private User user;
	private InputStream inputStream;
	private String time;
	
	@Override
	public String execute() throws Exception {
		
		User u = (User)dao.getSession().get(User.class, user.getAddress());
		String result;
		
		if(u.getInbox().size() != user.getInbox().size()){				
			result = "true";
		}else{
			result = "false";
		}
		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		return SUCCESS;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

}
