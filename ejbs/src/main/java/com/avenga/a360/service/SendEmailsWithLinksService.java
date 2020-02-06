package com.avenga.a360.service;

import com.avenga.a360.domain.model.Email;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Session;

import java.util.List;

public interface SendEmailsWithLinksService {
    boolean sendEmailsWithLinks(Session session);

    Email createEmailWithLink(Participant participant, Session session);

    List<Participant> findParticipantsToBeRatedBySingleParticipant(Participant participant, Session session);
}
