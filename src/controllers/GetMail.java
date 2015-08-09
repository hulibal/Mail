package controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

import models.Attach;
import models.Mail;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import utilities.Dao;

public class GetMail extends ActionSupport {

	private static final long serialVersionUID = 114L;

	private Dao dao;
	private int id;
	private InputStream inputStream;
	private String box;

	@Override
	public String execute() throws Exception {

		Session session = dao.getSession();
		Mail mail = null;
		if(box.equals("inbox")){
			mail = (Mail) session.get("inbox", id);
		}else if(box.equals("outbox")){
			mail = (Mail) session.get("outbox", id);
		}
		
		Set<Attach> attaches = mail.getAttach();
		StringBuilder attach = new StringBuilder();
		for(Attach a : attaches){
			attach.append("<a href=\"download?mailid="+mail.getId()+"&attachid="+a.getId()+"&box="+box+"\">"+a.getName()+"</a><br/>");
		}
		String result = mail.getTopic() + "$" + mail.getAddresser() + "$"
				+ mail.getReceiver() + "$" + mail.getContent() + "$"+attach.toString()+"$"
				+ mail.getDate() + "$" + mail.getId();

		inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));

		return SUCCESS;

	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InputStream getInputStream() {
		return this.inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

}
