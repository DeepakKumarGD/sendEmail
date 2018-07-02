package com.emailSender.sendEmail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class MailContentBuilder {

    @Autowired
    private TemplateEngine templateEngine;

    public String buildUserRegistrationTemplate(String userName, String activationLink) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userName", userName);
        variables.put("activationLink", activationLink);
        return build(variables, "userRegistration");
    }

    private String build(Map<String, Object> map, String templateName) {
        Context context = new Context();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        return templateEngine.process(templateName, context);
    }
}
