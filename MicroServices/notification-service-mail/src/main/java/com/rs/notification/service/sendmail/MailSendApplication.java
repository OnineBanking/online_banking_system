package com.rs.notification.service.sendmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailSendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailSendApplication.class, args);
		System.out.println( "Test app!" );
	}

}
