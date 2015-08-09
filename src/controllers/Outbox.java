package controllers;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import models.User;
import utilities.Dao;

public class Outbox extends ActionSupport {
	
	private static final long serialVersionUID = 112L;
	
	private Dao dao;
	private User user;
	
	@Override
	public String execute() {
		
		Session session = dao.getSession();
		User u = (User)session.get(User.class, user.getAddress());
		user.setOutbox(u.getOutbox());
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
