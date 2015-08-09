package controllers;

import java.io.*;

import org.hibernate.Session;

import utilities.Dao;
import models.*;

public class DownloadFiles {
	private static final long serialVersionUID = 55L;

	private int mailid;
	private int attachid;
	private String box;
	private Dao dao;
	private String downfilename;
	private InputStream inputStream;

	// private Mail mail;

	public String execute() throws Exception {
		Session session = dao.getSession();
		Attach attach = (Attach) session.get("a"+box, attachid);
		String strBase64 = attach.getCode();
		setDownfilename(attach.getName());

		// 解码，然后将字节转换为文件
		byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(strBase64); // 将字符串转换为byte数组
		inputStream = new ByteArrayInputStream(bytes);
		return "success";
	}

	public int getAttachid() {
		return attachid;
	}

	public void setAttachid(int attachid) {
		this.attachid = attachid;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public int getMailid() {
		return mailid;
	}

	public void setMailid(int mailid) {
		this.mailid = mailid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getDownfilename() {
		return downfilename;
	}

	public void setDownfilename(String downfilename) throws Exception{
		this.downfilename = new String(downfilename.getBytes("iso-8859-1"), "gbk");
	}
}
