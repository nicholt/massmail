package com.nic.mail.service;

import com.nic.mail.vo.MailVo;

public interface MailSendService {
	public boolean sendMail(MailVo mailVo);
}