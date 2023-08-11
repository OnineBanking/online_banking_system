package com.rs.notification.service.sendmail.service;

import org.springframework.web.multipart.MultipartFile;

import com.rs.notification.service.sendmail.util.WithdrawInput;


public interface EmailService  {
	String sendMailForWithdraw(WithdrawInput withdrawInput);	
	String sendMail(MultipartFile[] file, String to,String[] cc, String subject, String body);
}
