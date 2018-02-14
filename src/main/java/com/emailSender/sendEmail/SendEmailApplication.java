package com.emailSender.sendEmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SendEmailApplication {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(){
		return "This is to send the mail";
	}

	public static void main(String[] args) {
		SpringApplication.run(SendEmailApplication.class, args);
	}
}
