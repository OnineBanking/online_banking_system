package com.obs.notification.mail.service;

import com.obs.notification.mail.TransferServiceEvent;

public interface EmailService  {
	//String sendMailForWithdraw(WithdrawInput withdrawInput);
	//String sendMail(String to,String[] cc, String subject, String body);
	String sendMail(TransferServiceEvent transferServiceEvent);
}
