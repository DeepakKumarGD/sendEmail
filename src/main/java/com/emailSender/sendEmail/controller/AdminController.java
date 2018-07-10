package com.emailSender.sendEmail.controller;

import com.emailSender.sendEmail.service.AdminService;
import com.emailSender.sendEmail.service.EmailService;
import com.emailSender.sendEmail.utils.ResponseJsonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@Slf4j
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

    @RequestMapping(value = "upload-file", method = RequestMethod.POST)
    public ResponseEntity<?> saveUserProfile(@RequestPart MultipartFile file) throws Exception {
        log.info("Signed Document Upload Started");
        return new ResponseEntity<>(this.adminService.saveUserProfile(file), HttpStatus.OK);
    }
    //https://myaccount.google.com/lesssecureapps
}
