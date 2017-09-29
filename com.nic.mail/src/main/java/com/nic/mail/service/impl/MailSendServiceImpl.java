package com.nic.mail.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.nic.mail.bo.MailAuthenticator;
import com.nic.mail.service.MailSendService;
import com.nic.mail.util.UtilFile;
import com.nic.mail.vo.MailVo;

public class MailSendServiceImpl implements MailSendService {

	public boolean sendMail(MailVo mailVo) {
		MailAuthenticator mailAuthenticator = null;
		Properties prop = mailVo.getProperties();
		if (mailVo.getAuth()) mailAuthenticator = new MailAuthenticator(mailVo.getUsername(), mailVo.getPassword());
		Session session = Session.getDefaultInstance(prop, mailAuthenticator);
		try {
        	MimeMessage message = new MimeMessage(session);
        	message.setFrom(new InternetAddress(mailVo.getFrom()));
        	String[] to = mailVo.getTo();
        	if (to!=null && to.length>0) {
	        	InternetAddress[] sendTo = new InternetAddress[to.length];
	        	for (int i=0; i<to.length; i++) {
	        		sendTo[i] = new InternetAddress(to[i]);
	        	}
	        	message.setRecipients(Message.RecipientType.TO, sendTo);
        	}
        	String[] cc = mailVo.getCc();
        	if (cc!=null && cc.length>0) {
	        	InternetAddress[] sendCc = new InternetAddress[cc.length];
	        	for (int i=0; i<cc.length; i++) {
	        		sendCc[i] = new InternetAddress(cc[i]);
	        	}
	        	message.setRecipients(Message.RecipientType.CC, sendCc);
        	}
        	message.setSubject(mailVo.getSubject());
        	Multipart multipart = new MimeMultipart();
        	BodyPart contentPart = new MimeBodyPart();
        	contentPart.setContent(mailVo.getText(), mailVo.getContentType());
        	multipart.addBodyPart(contentPart);
        	if(mailVo.getAttachFileList()!=null && mailVo.getAttachFileList().length>0) {
        		for(int i=0; i<mailVo.getAttachFileList().length; i++) {
        			if (!mailVo.getAttachFileList()[i].equals("")) {
        				BodyPart attachmentBodyPart = new MimeBodyPart();
        				DataSource source = new FileDataSource(mailVo.getAttachFileList()[i]);
                        attachmentBodyPart.setDataHandler(new DataHandler(source));
                        try {
        					attachmentBodyPart.setFileName(MimeUtility.encodeWord(UtilFile.getFileName(mailVo.getAttachFileList()[i])));
        				} catch (UnsupportedEncodingException e) {
        					e.printStackTrace();
        				}
                        multipart.addBodyPart(attachmentBodyPart);
        			}
        		}
        	}
        	message.setContent(multipart);
        	Transport.send(message);
            return true;
        } catch(MessagingException e) {
        	e.printStackTrace();
        }
		return false;
	}
}