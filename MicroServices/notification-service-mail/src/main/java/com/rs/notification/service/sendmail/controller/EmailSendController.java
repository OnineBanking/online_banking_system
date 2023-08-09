package com.rs.notification.service.sendmail.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rs.notification.service.sendmail.service.EmailService;
import com.rs.notification.service.sendmail.util.CreateAccountInput;
import com.rs.notification.service.sendmail.util.WithdrawInput;


@RestController
@RequestMapping(value = "/sendemail")
public class EmailSendController {
	
	@Autowired
    private EmailService emailService;

    public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }
    

    @PostMapping("/sendwithAttachment")
    public String sendMailWithAttachemnt(@RequestParam(value = "file", required = false) MultipartFile[] file, 
    		String to, String[] cc, String subject, String body) {
    	
    	return emailService.sendMail(file, to, cc, subject, body);
    }    
    
    @PostMapping(path ="/accountCreation" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String accountCreation(@Valid @RequestBody CreateAccountInput accountCreationRequest) {
		return null;
    	
    }
    
    
    @PostMapping(path ="/amountWithdrawl" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String amountWithdraw(@Valid @RequestBody WithdrawInput amountWithdraw) {
    	return emailService.sendMailForWithdraw(amountWithdraw);
    	
    }
  
}
