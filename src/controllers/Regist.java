package controllers;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import utilities.Dao;
import models.User;

public class Regist extends ActionSupport {

	private static final long serialVersionUID = 102L;

	private User user = new User();
	private Dao dao;
	private String repassword;
	private String message;

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
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String execute() {
	
		Session session = dao.getSession();
		user.setAddress(user.getAddress()+"@Web01.mail");
		if(session.get(User.class, user.getAddress()) == null){
			session.save(user);
			session.beginTransaction().commit();
			session.close();
			return SUCCESS;
		}else{
			this.message="◊¢≤· ß∞‹£¨” œ‰µÿ÷∑“—¥Ê‘⁄";
			return ERROR;
		}
					
	}	

}
