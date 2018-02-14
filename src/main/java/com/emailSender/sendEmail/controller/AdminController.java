package com.emailSender.sendEmail.controller;

import com.emailSender.sendEmail.service.AdminService;
import com.emailSender.sendEmail.utils.ResponseJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "sendEmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendEmail() throws MessagingException {
        return new ResponseEntity<Object>(ResponseJsonUtil.getSuccessResponseJson(this.adminService.sendSimpleMessage()), HttpStatus.OK);
    }

    //https://myaccount.google.com/lesssecureapps
}
