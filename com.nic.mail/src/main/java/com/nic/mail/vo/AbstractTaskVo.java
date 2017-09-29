package com.nic.mail.vo;

public abstract class AbstractTaskVo {

	private String id;
	private Object param;
	private Object service;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	public Object getService() {
		return service;
	}
	public void setService(Object service) {
		this.service = service;
	}
}