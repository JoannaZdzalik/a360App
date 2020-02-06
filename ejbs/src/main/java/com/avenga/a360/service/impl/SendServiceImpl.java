package com.avenga.a360.service.impl;

import com.avenga.a360.domain.model.Email;
import com.avenga.a360.service.SendService;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless
public class SendServiceImpl implements SendService {

    private static final String HOST = "localhost";
    private static final String PORT = "25";
    private static final String FROM = "asia@aaaa";

    @Override
    public boolean sendEmail(Email email) {
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", HOST);
            properties.put("mail.smtp.port", PORT);
            //properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("", "");
                }
            });

            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));
            message.setSubject(email.getSubject());
            message.setText(email.getMailBody());
            System.out.println("sending...");
            Transport.send(message);

            System.out.println("Email sent successfully....");
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

//    @Override
//    public boolean sendEmail(Mail mail) {
//        String userName = "username@gmail.com";
//        String password = "password";
//
//        String fromAddress = "noreply@nonono";
//
//        try {
//           Email email = new SimpleEmail();
//            email.setHostName(HOST);
//            email.setSmtpPort(PORT);
//            email.setAuthenticator(new DefaultAuthenticator(userName, password));
//            email.setFrom(fromAddress);
//            email.setSubject(mail.getSubject());
//            email.setMsg(mail.getMailBody());
//            email.addTo(mail.getRecipient());
//            email.send();
//        } catch (Exception e) {
//            System.out.println("Unable to send mail");
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }

}
