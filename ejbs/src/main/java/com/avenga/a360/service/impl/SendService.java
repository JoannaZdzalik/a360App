package com.avenga.a360.service.impl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class SendService {

    private static final String HOST = "localhost";
    private static final int PORT = 25;

    public boolean sendEmail(com.avenga.a360.domain.model.Email email) {
        String userName = "username@gmail.com";
        String password = "password";

        String fromAddress = "noreply@nonono";

        try {
            Email mail = new SimpleEmail();
            mail.setHostName(HOST);
            mail.setSmtpPort(PORT);
            mail.setAuthenticator(new DefaultAuthenticator(userName, password));
            mail.setFrom(fromAddress);
            mail.setSubject(email.getSubject());
            mail.setMsg(email.getMailBody());
            mail.addTo(email.getRecipient());
            mail.send();
        } catch (Exception e) {
            System.out.println("Unable to send email");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
