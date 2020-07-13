package com.avenga.a360.service.impl;

import com.avenga.a360.dao.EmailConfDao;
import com.avenga.a360.model.Email;
import com.avenga.a360.model.EmailConf;
import com.avenga.a360.service.SendService;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

@Stateless
public class SendServiceImpl implements SendService {

    @Resource(mappedName = "java:/mail")
    Session session;

    @Inject
    EmailConfDao emailConfDao;

    @Override
    public boolean sendEmail(Email email) {
        EmailConf emailConf = emailConfDao.findEmailConfiguration();
        try {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", emailConf.getHost());
            properties.put("mail.smtp.port", emailConf.getPort());
            properties.put("mail.smtp.auth", emailConf.isAuth());

            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailConf.getUsername(), emailConf.getPassword());
                }
            });
            session.setDebug(emailConf.isDebug());

            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getSendTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            message.setFrom(new InternetAddress(emailConf.getFrom()));
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Asynchronous
    public void sendEmailsToAllParticipants(List<Email> emailList) {
        for (Email email : emailList) {
            sendEmail(email);
        }
    }

     @Override
    public boolean checkSmtpServer() {
        try {
            Transport transport = session.getTransport("smtp");
            transport.connect();
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}