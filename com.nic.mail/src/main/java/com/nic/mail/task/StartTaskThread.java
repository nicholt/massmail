package com.nic.mail.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.nic.mail.service.MailSendService;
import com.nic.mail.service.impl.MailSendServiceImpl;
import com.nic.mail.vo.MailSendTaskVo;
import com.nic.mail.vo.MailVo;

public class StartTaskThread implements Runnable {

	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	private int i;
	private MailVo mailVo;
	private static final Logger log = Logger.getLogger(StartTaskThread.class);

	public StartTaskThread(ThreadPoolTaskExecutor threadPoolTaskExecutor, int i, MailVo mailVo) {
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
		this.i = i;
		this.mailVo = mailVo;
	}

	public synchronized void run() {
		MailSendService service = new MailSendServiceImpl();
		MailSendTaskVo mailSendTaskVo = new MailSendTaskVo();
		mailSendTaskVo.setId("task@"+String.valueOf(i));
		mailSendTaskVo.setParam(mailVo);
		mailSendTaskVo.setService(service);
		System.out.println("task created and added to thread pool: " + mailSendTaskVo.getId() + "(" + ((MailVo)mailSendTaskVo.getParam()).getComment() + ")");
		log.info("task created and added to thread pool: " + mailSendTaskVo.getId() + "(" + ((MailVo)mailSendTaskVo.getParam()).getComment() + ")");
		FutureTask<String> futureTask = new FutureTask<String>(new ThreadPoolTask(mailSendTaskVo));
		threadPoolTaskExecutor.execute(futureTask);
		String result = null;
		try {
			result = futureTask.get(600000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			futureTask.cancel(true);
			System.out.println(mailSendTaskVo.getId() + ":result=CANCEL");
			log.error(mailSendTaskVo.getId() + ":result=CANCEL");
		} catch (ExecutionException e) {
			futureTask.cancel(true);
			System.out.println(mailSendTaskVo.getId() + ":result=CANCEL");
			log.error(mailSendTaskVo.getId() + ":result=CANCEL");
		} catch (Exception e) {
			futureTask.cancel(true);
			System.out.println(mailSendTaskVo.getId() + ":result=CANCEL");
			log.error(mailSendTaskVo.getId() + ":result=CANCEL");
		} finally {
			System.out.println(mailSendTaskVo.getId() + ":result=" + result);
			log.info(mailSendTaskVo.getId() + ":result=" + result);
		}
	}
}