package models;

import java.io.Serializable;

public class Attach implements Serializable{
	
	private static final long serialVersionUID = 212L;
	
	private int mail_id;
	private int id;
	private String name; //¸½¼þÃû
	private String code; //±àÂë	
	private Mail mail;
	
	public Attach clone(Mail mail){
		Attach att = new Attach();
		att.setCode(this.code);
		att.setName(this.name);
		att.setMail(mail);
		return att;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}
	public int getMail_id() {
		if(mail == null){
			return mail_id;
		}else{
			return mail.getId();
		}		
	}
	public void setMail_id(int mail_id) {
		this.mail_id = mail_id;
	}
	
	
	
}
