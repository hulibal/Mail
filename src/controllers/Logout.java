package controllers;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport {
	
	private static final long serialVersionUID = 101L;
	
	@Override
	public String execute() {
		
		ServletActionContext.getRequest().getSession().invalidate();
		return SUCCESS;
		
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
