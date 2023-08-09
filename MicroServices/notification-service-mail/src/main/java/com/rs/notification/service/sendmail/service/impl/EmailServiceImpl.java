package com.rs.notification.service.sendmail.service.impl;

import java.time.LocalDateTime;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rs.notification.service.sendmail.service.EmailService;
import com.rs.notification.service.sendmail.util.WithdrawInput;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

//            mimeMessageHelper.setFrom(fromEmail);
//            mimeMessageHelper.setTo(to);
//            mimeMessageHelper.setCc(cc);
//            mimeMessageHelper.setSubject(subject);
//            mimeMessageHelper.setText(body);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo("hemalatha.mohanraj@dxc.com");
//            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject("TestMail");
            mimeMessageHelper.setText("Amount transsfer");

            for (int i = 0; i < file.length; i++) {
                mimeMessageHelper.addAttachment(
                        file[i].getOriginalFilename(),
                        new ByteArrayResource(file[i].getBytes()));
            }

            javaMailSender.send(mimeMessage);

            return "mail sent with attachment";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    
    public String sendMail(String to, String[] cc, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

//            mimeMessageHelper.setFrom(fromEmail);
//            mimeMessageHelper.setTo(to);
//            mimeMessageHelper.setCc(cc);
//            mimeMessageHelper.setSubject(subject);
//            mimeMessageHelper.setText(body);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo("hemalatha.mohanraj@dxc.com");
//            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject("TestMail");
            mimeMessageHelper.setText("Amount transsfer");

//            for (int i = 0; i < file.length; i++) {
//                mimeMessageHelper.addAttachment(
//                        file[i].getOriginalFilename(),
//                        new ByteArrayResource(file[i].getBytes()));
//            }

            javaMailSender.send(mimeMessage);

            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

	@Override
	public String sendMailForWithdraw(WithdrawInput withdrawInput) {
		try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

//            mimeMessageHelper.setFrom(fromEmail);
//            mimeMessageHelper.setTo(to);
//            mimeMessageHelper.setCc(cc);
//            mimeMessageHelper.setSubject(subject);
//            mimeMessageHelper.setText(body);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo("hemalatha.mohanraj@dxc.com");
//            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject("Amount Withdraw Alert");
            mimeMessageHelper.setText("Dear Customer,\r\n" + 
            		"\r\n" +
            		"Rs."
            		+withdrawInput.getAmount()+
            		" has been debited from account "
            		+withdrawInput.getAccountNumber()+
            		" to VPA q547245350@ybl on "
            		+ LocalDateTime.now()+
            		". Your UPI transaction reference number is 357872415786.\r\n" + 
            		"\r\n" + 
            		"Please call on 18002586161 to report if this transaction was not authorized by you.\r\n" + 
            		"\r\n" + 
            		"Warm Regards,\r\n" + 
            		"OBS Team.");

            javaMailSender.send(mimeMessage);

            return "mail send for withdrawal";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
