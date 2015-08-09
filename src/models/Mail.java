package models;

import java.io.Serializable;
import java.util.*;

public class Mail implements Serializable {

	private static final long serialVersionUID = 210L;

	private int id;
	private String addresser; // 发件人
	private String receiver; // 收件人
	private String topic; // 主题
	private String content; // 正文
	private String Date;
	private Set<Attach> attach; // 附件

	public String getAddresser() {
		return addresser;
	}

	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		} else {
			Mail mail = (Mail) obj;
			if (mail.getId() == this.id) {
				return true;
			} else {
				return false;
			}
		}
	}

	public Set<Attach> getAttach() {
		return attach;
	}

	public void setAttach(Set<Attach> attach) {
		this.attach = attach;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Mail clone() {

		Mail m = new Mail();
		m.setAddresser(this.getAddresser());
		if (this.attach != null) {
			Set<Attach> out = this.getAttach();
			Set<Attach> in = new HashSet<Attach>();
			for (Attach a : out) {
				in.add(a.clone(m));			}
			m.setAttach(in);
		}
		m.setContent(this.getContent());
		m.setDate(this.getDate());
		m.setReceiver(this.getReceiver());
		m.setTopic(this.getTopic());

		return m;

	}

}
