package com.avenga.a360.service;

import com.avenga.a360.model.Email;

import javax.ejb.Asynchronous;
import java.util.List;

public interface SendService {

    boolean sendEmail(Email email);

    @Asynchronous
    void sendEmailsToAllParticipants(List<Email> emailList);

    boolean checkSmtpServer();
}
