package com.avenga.a360.service.impl;

import com.avenga.a360.model.Email;
import com.avenga.a360.service.SendService;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Stateless
public class SendServiceImpl implements SendService {

    @Resource(mappedName="java:/mail")
    Session session;

    @Override
    public boolean sendEmail(Email email) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getSendTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
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