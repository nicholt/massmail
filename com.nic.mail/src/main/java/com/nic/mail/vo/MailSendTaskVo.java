package com.nic.mail.vo;

import com.nic.mail.service.MailSendService;

public class MailSendTaskVo extends AbstractTaskVo {
	private String id;
	private MailVo param;
	private MailSendService service;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getParam() {
		return param;
	}
	public void setParam(MailVo param) {
		this.param = param;
	}
	public Object getService() {
		return service;
	}
	public void setService(MailSendService service) {
		this.service = service;
	}
}