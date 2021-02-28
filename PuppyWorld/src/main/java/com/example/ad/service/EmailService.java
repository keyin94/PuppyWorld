package com.example.ad.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}

	public void sendNotification(String msg, String userEmail) throws MailException, MessagingException, IOException{
		
		
		
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(userEmail);
		mail.setSubject("New Password");
		mail.setText(msg);
		
		
		javaMailSender.send(mail);
	}
	
	public void sendSuccessNotification(String msg, String userEmail) throws MailException, MessagingException, IOException{
		
		
		
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(userEmail);
		mail.setSubject("Reservation Success");
		mail.setText(msg);
		
		
		javaMailSender.send(mail);
	}

}
