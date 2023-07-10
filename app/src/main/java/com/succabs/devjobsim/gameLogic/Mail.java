package com.succabs.devjobsim.gameLogic;

public class Mail {
    private String sender;
    private String subject;
    private String content;

    public Mail(String sender, String subject, String content) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }
}
