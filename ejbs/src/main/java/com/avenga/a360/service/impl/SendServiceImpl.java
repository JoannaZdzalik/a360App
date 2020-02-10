package com.avenga.a360.service.impl;

import com.avenga.a360.domain.model.Email;
import com.avenga.a360.service.SendService;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class SendServiceImpl implements SendService {

    @Resource(mappedName = "java:/mail")
    Session session;

    @Override
    public void sendEmail(Email email) { //properties, host, port, sender are set in wildfly
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));
            message.setSubject(email.getSubject());
            message.setText(email.getMailBody());
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
