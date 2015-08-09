package controllers;

import org.hibernate.Session;

import models.User;
import utilities.Dao;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport {
	
	private static final long serialVersionUID = 100L;
	private User user;
	private Dao dao;
	private String message;
	private String securityCode;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String execute() {

		if(!user.getSecurityCode().equals(this.securityCode)){
			this.message = "ÑéÖ¤Âë´íÎó";
			return ERROR;
		}
		
		this.user.setAddress(this.user.getAddress()+"@Web01.mail");
		Session session = dao.getSession();
		User u = (User)session.get(User.class, this.user.getAddress());		
		if(u != null){
			if(u.getPassword().equals(this.user.getPassword())){
				user.setInbox(u.getInbox());
				user.setOutbox(u.getOutbox());
				return SUCCESS;
			}else{
				this.message = "µÇÂ½Ê§°Ü£¬ÃÜÂë´íÎó";
				return ERROR;
			}
		}else{
			this.message = "µÇÂ½Ê§°Ü£¬ÓÊÏäµØÖ·²»´æÔÚ";
			return ERROR;
		}
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

}
