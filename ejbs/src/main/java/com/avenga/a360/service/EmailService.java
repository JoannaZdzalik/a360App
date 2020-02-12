package com.avenga.a360.service;

import com.avenga.a360.model.Email;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Session;

import java.util.List;

public interface EmailService {

    List<Email> createEmailsToParticipantsWithFeedback(Session session);

    List<Email> createEmailsToParticipantsWithLinks(List<Participant> participantList, String sessionName);

}
