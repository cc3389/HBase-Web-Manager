package edu.wit.hbasemanager.model;

import lombok.Data;

@Data
public class MailMessage {
    private String targetMail;
    private String message;
    private String subject;
}
