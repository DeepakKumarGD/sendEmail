package com.emailSender.sendEmail.dto;

import lombok.Data;

@Data
public class EmailContent {
    private String supportContactName;
    private String supportContactNo;
    private String supportEmailId;
    private String host;
    private String subDomain;
}
