package controllers;

import org.hibernate.Session;

import models.User;
import utilities.Dao;

import com.opensymphony.xwork2.ActionSupport;

public class Delete extends ActionSupport {
	
	private static final long serialVersionUID = 115L;
	
	private String box;
	private int id;
	private Dao dao;
	private User user;
	
	@Override
	public String execute() throws Exception {
		
		Session session = dao.getSession();		
		User u = (User)session.get(User.class, user.getAddress());
		if(box.equals("inbox")){
			Object mail = session.get("inbox", id);
			u.getInbox().remove(mail);			
		}else if(box.equals("outbox")){
			Object mail = session.get("outbox", id);
			u.getOutbox().remove(mail);			
		}else{
			System.out.println("error in delete : " + box);
		}
		session.beginTransaction().commit();
		session.close();
		return box;
		
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
