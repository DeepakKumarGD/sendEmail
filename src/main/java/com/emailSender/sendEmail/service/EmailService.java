package com.emailSender.sendEmail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Async
    public boolean sendActivationEmail(String userName, String userEmail, String activationLink,
                                    String subject) {
        log.info("Started sending Email");
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(userEmail);
            messageHelper.setSubject(subject);
            String content = mailContentBuilder.buildUserRegistrationTemplate(userName, activationLink);
            messageHelper.setText(content, true);
        };
        javaMailSender.send(messagePreparator);
        log.info("Completed sending Email");
        return true;
    }

}
