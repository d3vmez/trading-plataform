package com.tradingplataform.notificationservice.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
	
	private Logger log = Logger.getLogger(MailSenderService.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendEmail(String recipient, String subject, String content) {
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(recipient);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);
		
		try {
			
			javaMailSender.send(mailMessage);
			log.info("Email send to " + recipient);
			return true;
			
		} catch (MailException e) {
			
			System.out.println(e.getMessage().toString());
			return false;
			
		}

	}

}
