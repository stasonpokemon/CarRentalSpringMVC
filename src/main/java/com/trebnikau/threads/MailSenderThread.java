package com.trebnikau.threads;

import com.trebnikau.service.MailSenderService;

public class MailSenderThread extends Thread {

    private MailSenderService mailSenderService;
    private String emailTo;
    private String subject;
    private String message;

    public MailSenderThread(MailSenderService mailSenderService, String emailTo, String subject, String message) {
        this.mailSenderService = mailSenderService;
        this.emailTo = emailTo;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public void run() {
        mailSenderService.send(emailTo, subject, message);

    }
}
