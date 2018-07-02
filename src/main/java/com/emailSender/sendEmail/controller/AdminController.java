package com.emailSender.sendEmail.controller;

import com.emailSender.sendEmail.service.AdminService;
import com.emailSender.sendEmail.service.EmailService;
import com.emailSender.sendEmail.utils.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.UUID;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "sendEmail", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendEmail() throws MessagingException {
        String activationLink = "http://localhost:9090/newMailLink";
        String subject = "testing mail";
        UUID uuidOne = UUID.randomUUID();
        String randomUUIDOne = uuidOne.toString().replaceAll("-", "");
        return new ResponseEntity<>(ResponseJsonUtil.getSuccessResponseJson
                (this.emailService.sendActivationEmail("deepak", "deepak1992ac@gmail.com",
                        activationLink + "&token=" + randomUUIDOne,
                        subject)), HttpStatus.OK);
    }

    //https://myaccount.google.com/lesssecureapps
}
