package controllers;

import com.opensymphony.xwork2.ActionSupport;

import java.io.ByteArrayInputStream;

import models.User;
import utilities.SecurityCode;
import utilities.SecurityImage;


public class SecurityCodeImage extends ActionSupport{
    
	private static final long serialVersionUID = 7801596555707800612L;

	User user;
    
    private ByteArrayInputStream imageStream;

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    public String execute() throws Exception {

        //获取默认难度和长度的验证码
        String securityCode = SecurityCode.getSecurityCode(4).toLowerCase();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        user.setSecurityCode(securityCode);
        return SUCCESS;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    

}