package com.avenga.a360.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public abstract class SendService {


    private static final String HOST = "localhost";
    private static final int PORT = 25;

    public static boolean sendEmail(String recipient, String subject, String mailBody) {
        String userName = "username@gmail.com";
        String password = "password";

        String fromAddress = "noreply@nonono";

        try {
            Email email = new SimpleEmail();
            email.setHostName(HOST);
            email.setSmtpPort(PORT);
            email.setAuthenticator(new DefaultAuthenticator(userName, password));
            email.setFrom(fromAddress);
            email.setSubject(subject);
            email.setMsg(mailBody);
            email.addTo(recipient);
            email.send();
        } catch (Exception e) {
            System.out.println("Unable to send email");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
