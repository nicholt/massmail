package com.nic.mail.task;

import java.io.Serializable;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.nic.mail.service.MailSendService;
import com.nic.mail.vo.MailSendTaskVo;
import com.nic.mail.vo.MailVo;

public class ThreadPoolTask implements Callable<String>, Serializable {

	private static final long serialVersionUID = 0;
	private Object threadPoolTaskData;
	private static final Logger log = Logger.getLogger(ThreadPoolTask.class);

	public ThreadPoolTask(Object tasks) {
		this.threadPoolTaskData = tasks;
	}

	public synchronized String call() throws Exception {
		MailSendTaskVo mailSendTaskVo = (MailSendTaskVo)threadPoolTaskData;
		System.out.println("task begin: " + mailSendTaskVo.getId());
		log.info("task begin: " + mailSendTaskVo.getId());
		String result = "";
		Thread.sleep(500);
		try {
			MailSendService service = (MailSendService)mailSendTaskVo.getService();
			MailVo mailVo = (MailVo)mailSendTaskVo.getParam();
			if (service.sendMail(mailVo)) result = "SUCCESS";
			else result = "FAILURE";
		} catch (Exception e) {
			e.printStackTrace();
			result = "FAILURE";
		}
		threadPoolTaskData = null;
		return result;
	}
}