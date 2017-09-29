package com.nic.mail.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nic.mail.common.Constants;
import com.nic.mail.common.Enums;
import com.nic.mail.util.UtilCsv;
import com.nic.mail.util.UtilProperties;
import com.nic.mail.vo.MailVo;

public class MailCsvLoader {

	private static final Logger log = Logger.getLogger(MailCsvLoader.class);

	public static List<MailVo> fetchMailList() {
		List<MailVo> voList = new ArrayList<MailVo>();
		String host = "";
		String port = "";
		String from = "";
		String username = "";
		String password = "";
		String auth = "";
		String attachfd = "";
		String attachfdsent = "";
		List<String> list = null;
		try {
			list = UtilCsv.loadCsvLineString(UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.FileCSV.name.getValue()));
			host = UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.Mail.host.getValue());
			from = UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.Mail.from.getValue());
			username = UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.Mail.username.getValue());
			password = UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.Mail.password.getValue());
			auth = UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.Mail.auth.getValue());
			port = UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.Mail.port.getValue());
			attachfd = UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.Mail.attachfd.getValue());
			attachfdsent = UtilProperties.getPropertyValue(Constants.Prop_Conf,Enums.Mail.attachfdsent.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		logic2(list, voList, host, port, from, username, password, auth, attachfd, attachfdsent);
		return voList;
	}

	public static void logic1(List<String> list, List<MailVo> voList, String host, String port, String from, String username, String password, String auth) {
		for(String str : list){
			if (str.length()>0) {
				MailVo mailVo = new MailVo();
				String[] strArr = str.split(",");
				mailVo.setHost(host);
				if(port!=null && port!="") mailVo.setPort(port);
				mailVo.setFrom(from);
				StringBuffer sb = new StringBuffer();
				if(strArr[2]!=null && !strArr[2].equals("")) {
					mailVo.setSubject(strArr[2]);
					sb.append(strArr[2]);
				}
				if(strArr[0]!=null && !strArr[0].equals("")) {
					mailVo.setTo(strArr[0].split(";"));
					sb.append(" To: "+strArr[0]);
				}
				if(strArr[1]!=null && !strArr[1].equals("")) {
					mailVo.setCc(strArr[1].split(";"));
					sb.append(" CC: "+strArr[1]);
					System.out.println(strArr[1]);
				}
				mailVo.setComment(sb.toString());
				if(strArr[3]!=null && !strArr[3].equals("")) mailVo.setText(strArr[3]);
				System.out.println(strArr[3]);
				if(strArr.length>4) mailVo.setAttachFileList(strArr[4].split(";"));
				System.out.println(strArr[4]);
				mailVo.setUsername(username);
				mailVo.setPassword(password);
				mailVo.setContentType(Enums.ContentType.UTF8.getValue());
				if("true".equals(auth)) mailVo.setAuth(true);
				else mailVo.setAuth(false);
				voList.add(mailVo);
			}
		}
	}

	public static void logic2(List<String> list, List<MailVo> voList, String host, String port, String from, String username, String password, String auth, String attachfd, String attachfdsent) {
		MailVo mailVo = new MailVo();
		StringBuffer sb = new StringBuffer();
		StringBuffer sba = new StringBuffer();
		mailVo.setHost(host);
		if (port!=null && port!="") mailVo.setPort(port);
		mailVo.setSubject("Monitor5");
		sb.append("Monitor5");
		mailVo.setFrom(from);
		mailVo.setTo(list.toArray(new String[list.size()]));
		sb.append(" To: "+list.toString());
		mailVo.setText("Photos.");
		File folder = new File(attachfd);
		if (folder.exists()) {
			File[] files = folder.listFiles();
			String[] attachArr = new String[files.length];
			for (int i=0;i<files.length;i++) {
				attachArr[i]=files[i].getAbsolutePath();
				sba.append(attachArr[i]).append(" ");
			}
			if (attachArr.length>0) mailVo.setAttachFileList(attachArr);
			log.info("attachement to send: ("+sba.toString()+")");
		} else {
			System.out.println("***attachement folder not exists!***");
			log.error("attachement folder not exists");
		}
		mailVo.setUsername(username);
		mailVo.setPassword(password);
		mailVo.setContentType(Enums.ContentType.UTF8.getValue());
		mailVo.setComment(sb.toString());
		if ("true".equals(auth)) mailVo.setAuth(true);
		else mailVo.setAuth(false);
		voList.add(mailVo);
	}
}