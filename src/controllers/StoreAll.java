package controllers;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import models.*;

public class StoreAll {
	private User user;
	private Date starttime;
	private Date endtime;
	private String downfilename;
	private String contentType;
	private String obj;
	private InputStream inputStream;

	public String execute() throws Exception {	
		String text = "";
		Set<Mail> box;
		if(obj.equals("inbox")){
			box = user.getInbox();
		}else if(obj.equals("outbox")){
			box = user.getOutbox();
		}else{
			box = user.getInbox();
			box.addAll(user.getOutbox());
		}
		
		// System.out.println(starttime);
		// System.out.println(endtime);
		for (Mail mail : box) {
			// System.out.println(mail.getDate());
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date temp = dateFormat.parse(mail.getDate());
			int start = temp.compareTo(starttime);
			int end = temp.compareTo(endtime);
			if (start > 0 && end < 0) {
				text = text + encodeBase64(mail.getAddresser()) + "#";
				text = text + encodeBase64(mail.getReceiver()) + "#";
				text = text + encodeBase64(mail.getTopic()) + "#";
				text = text + encodeBase64(mail.getContent()) + "#";
				if (mail.getAttach().size()>0) {
					Set<Attach> attset = mail.getAttach();
					text = text + encodeBase64(mail.getDate()) + "#";
					for(Attach attach:attset){
						text = text + attach.getCode() + "#";
					text = text + encodeBase64(attach.getName())
							+ "#";
					}
					text = text + "&";
				} else {
					text = text + encodeBase64(mail.getDate()) + "&";
				}
			}
		}
		Date now = new Date();
		DateFormat tem = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM);
		String str = tem.format(now);
		setDownfilename(str.replace(":", "") + ".doc");
		String path = ServletActionContext.getServletContext().getRealPath("/downloadfiles");
		File savedir = new File(path);
		if (!savedir.exists())
			savedir.mkdirs();
		//path = path+ "/" + downfilename;
		try {				
			File f = new File(savedir,downfilename);
			//System.out.println(path);
			f.createNewFile();
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(text);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setContentType("application/msword");
		FileInputStream bytes = new FileInputStream(path+ "/" + downfilename);
		setInputStream(bytes);
		return "success";
	}

	// ½«×Ö·û´®×ª»»ÎªBase64±àÂë×Ö·û´®
	public String encodeBase64(String str) {
		String base64;
		byte[] bstr = str.getBytes();
		base64 = new sun.misc.BASE64Encoder().encode(bstr);
		return base64;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
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

	public void setDownfilename(String downfilename) {
		this.downfilename = downfilename;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

}
