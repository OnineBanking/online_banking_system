package com.obs.notification.mail.service.impl;

import com.obs.notification.mail.TransferServiceEvent;
import com.obs.notification.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMail(TransferServiceEvent transferServiceEvent) {
        System.out.println("transferServiceEvent received--"+transferServiceEvent.getTransType());
        var mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(fromEmail);
        mailMessage.setTo("hemalatha.mohanraj@dxc.com");
//            mimeMessageHelper.setCc(cc);
        mailMessage.setSubject("Amount Withdraw Alert");
        mailMessage.setText("Dear Customer,\r\n" +
                "\r\n" +
                "Rs."+
                transferServiceEvent.getTransAmount()+
                " has been debited from account "+
                transferServiceEvent.getFromAccountNo()+
                " to " +
                transferServiceEvent.getToAccountNo()+
                "on "
                + LocalDate.now()+
                ". Your transaction reference number is 357872415786.\r\n" +
                "\r\n" +
                "Please call on 18002586161 to report if this transaction was not authorized by you.\r\n" +
                "\r\n" +
                "Warm Regards,\r\n" +
                "OBS Team.");

        javaMailSender.send(mailMessage);
        return "mail send";
    }
}