package com.nic.mail.vo;

import java.util.Properties;

public class MailVo {

	private String host;
	private String port;
	private String from;
	private String[] to;
	private String[] cc;
	private String subject;
	private String text;
	private String[] attachFileList;
	private String username;
	private String password;
	private String contentType;
	private boolean isAuth;
	private String comment;

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String[] getCc() {
		return cc;
	}
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String[] getAttachFileList() {
		return attachFileList;
	}
	public void setAttachFileList(String[] attachFileList) {
		this.attachFileList = attachFileList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public boolean getAuth() {
		return isAuth;
	}
	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.host);
		p.put("mail.smtp.auth", this.isAuth ? "true" : "false");
		if(this.port!=null && this.port!="") p.put("mail.smtp.port", this.port);
		return p;
	}
}