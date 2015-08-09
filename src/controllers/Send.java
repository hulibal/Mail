package controllers;

import java.io.*;
//import java.util.ArrayList;
import java.util.List;
import java.util.*;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import utilities.Dao;
import utilities.Date;
import models.*;

public class Send extends ActionSupport {

	private static final long serialVersionUID = 110L;

	private Mail mail;
	private Attach attach;
	private Dao dao;
	private User user;
	private List<File> file = new ArrayList <File> ();
	private List<String> fileContentType;
	private List<String> fileFileName;

	@Override
	public String execute() throws Exception {

		if (file.size() > 0) {
			Set<Attach> att = new HashSet<Attach>();
			for (int i = 0; i < file.size(); i++) {
				String strBase64 = "";
				FileInputStream ins = new FileInputStream(file.get(i));
				if (ins.available() > 0) {
					byte[] bytes = new byte[ins.available()];
					ins.read(bytes);
					strBase64 = new sun.misc.BASE64Encoder().encode(bytes); // 将字节流数组转换为字符串
					ins.close();
					attach = new Attach();
					attach.setMail(mail);
					attach.setCode(strBase64);
					attach.setName(fileFileName.get(i));
					att.add(attach);
				} else {
					attach = new Attach();
					attach.setMail(mail);
					attach.setCode(strBase64);
					attach.setName(fileFileName.get(i));
					att.add(attach);
				}
			}mail.setAttach(att);
		} else {
			System.out.println("datas为空");
		}

		mail.setAddresser(user.getAddress());
		mail.setDate(Date.getDate());
		Session session = dao.getSession();
		User addresser = (User) session.get(User.class, mail.getAddresser()); // 发件人
		User receiver = (User) session.get(User.class, mail.getReceiver()); // 收件人
		session.save("inbox", mail);
		receiver.getInbox().add(mail);

		mail = mail.clone();

		session.save("outbox", mail);
		addresser.getOutbox().add(mail);

		session.beginTransaction().commit();
		session.close();

		return SUCCESS;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
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

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Attach getAttach() {
		return attach;
	}

	public void setAttach(Attach attach) {
		this.attach = attach;
	}
}
