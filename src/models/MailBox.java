package models;

import java.io.Serializable;
import java.util.List;

public class MailBox implements Serializable{
	
	private static final long serialVersionUID = 211L;
	
	private List<Mail> mails = null;	
	
	public List<Mail> getMails() {
		return mails;
	}
	public void setMails(List<Mail> mails) {
		this.mails = mails;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
