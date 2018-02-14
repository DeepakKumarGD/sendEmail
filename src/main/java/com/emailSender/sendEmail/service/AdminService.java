package com.emailSender.sendEmail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Slf4j
@Component
public class AdminService {

    @Autowired
    public JavaMailSender emailSender;

    public boolean sendSimpleMessage() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setTo("deepak.kumar@hashworks.co");
        helper.setText("simple message");
        helper.setSubject("testing");
//        ClassPathResource file = new ClassPathResource("cat.jpg");
//        helper.addAttachment("cat.jpg", file);
        emailSender.send(message);
        return true;
    }
}
