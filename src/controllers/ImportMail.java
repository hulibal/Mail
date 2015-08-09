package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
//import org.hibernate.Session;

import org.hibernate.Session;

import models.*;
import utilities.Dao;

public class ImportMail {

	private Dao dao;
	private User user;
	private List<File> file = new ArrayList<File>();
	private List<String> fileContentType;
	private List<String> fileFileName;

	public String execute() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath(
				"/uploadfiles");
		// System.out.println(path);
		if (file.size() > 0) {
			File savedir = new File(path);
			if (!savedir.exists())
				savedir.mkdirs();
			for (int i = 0; i < file.size(); i++) {
				File saveFile = new File(savedir, fileFileName.get(i));
				FileUtils.copyFile(file.get(i), saveFile);
			}

			// System.out.println(path+"\\"+fileFileName.get(0));
			File file = new File(path + "/" + fileFileName.get(0));
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file));
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			String stringOfmails = "";
			while ((lineTxt = bufferedReader.readLine()) != null) {
				stringOfmails += lineTxt;
				// System.out.println(lineTxt);
			}
			Set<Mail> temp = turnStringtoMails(stringOfmails);
			// Session session = dao.getSession();
			String address = user.getAddress();
			Session session = dao.getSession();
			User u = (User) session.get(User.class, user.getAddress());
			for (Mail mail : temp) {
				if ((mail.getAddresser().equals(address)&&(mail.getReceiver().equals(address)))) { // 收发件同一人
					session.save("inbox", mail);
					u.getInbox().add(mail);
					mail = mail.clone();
					session.save("outbox", mail);
					u.getOutbox().add(mail);				
				}else if (mail.getReceiver().equals(address)) { // 收件人
					session.save("inbox", mail);
					u.getInbox().add(mail);
				}else if(mail.getAddresser().equals(address)){ //发件人
					session.save("outbox", mail);
					u.getOutbox().add(mail);
				}
			}
			session.beginTransaction().commit();
			session.close();
			read.close();
		}
		return "success";
	}

	public Set<Mail> turnStringtoMails(String str) {
		Set<Mail> re = new HashSet<Mail>();
		String[] temp0 = str.split("&");
		for (int i = 0; i < temp0.length; i++) {
			String[] temp1 = temp0[i].split("#");
			if (temp1.length > 5) {// 有附件				
				Mail mail = new Mail();
				Set<Attach> attach = new HashSet<Attach>() ;
				mail.setAddresser(decode(temp1[0]));				
				mail.setReceiver(decode(temp1[1]));
				mail.setTopic(decode(temp1[2]));
				mail.setContent(decode(temp1[3]));
				mail.setDate(decode(temp1[4]));
				int count = 5;
				while(true) {
					Attach a = new Attach();
					a.setMail(mail);
					a.setName(decode(temp1[count+1]));
					a.setCode(temp1[count]);
					attach.add(a);
					count += 2;
					if(count >=temp1.length-1)
						break;
				}
				mail.setAttach(attach);				
				re.add(mail);
			} else if (temp1.length == 5) {
				Mail mail = new Mail();
				mail.setAddresser(decode(temp1[0]));
				mail.setReceiver(decode(temp1[1]));
				mail.setTopic(decode(temp1[2]));
				mail.setContent(decode(temp1[3]));
				mail.setDate(decode(temp1[4]));
				re.add(mail);
			} else {
				System.out.println("无邮件");
			}
		}
		return re;

	}

	// 解码
	public String decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(bt);
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

}
