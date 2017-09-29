package com.nic.mail;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.nic.mail.bo.MailCsvLoader;
import com.nic.mail.task.StartTaskThread;
import com.nic.mail.vo.MailVo;

public class Main {

	private static int produceTaskSleepTime = 300;

	public static void main (String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ThreadPoolTaskExecutor tt = (ThreadPoolTaskExecutor)appContext.getBean("threadPoolTaskExecutor");
		List<MailVo> list = MailCsvLoader.fetchMailList();
		for (int i=0; i<list.size(); i++) {
			try {
				Thread.sleep(produceTaskSleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new Thread(new StartTaskThread(tt, i+1, list.get(i))).start();
		}
	}
}