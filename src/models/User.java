package models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
	
	private static final long serialVersionUID = 200L;

	private String address;
	private String password;
	private Set<Mail> inbox = new HashSet<Mail>();
	private Set<Mail> outbox = new HashSet<Mail>();
	private String securityCode;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Set<Mail> getInbox() {
		return inbox;
	}
	public void setInbox(Set<Mail> inbox) {
		this.inbox = inbox;
	}
	public Set<Mail> getOutbox() {
		return outbox;
	}
	public void setOutbox(Set<Mail> outbox) {
		this.outbox = outbox;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

}
