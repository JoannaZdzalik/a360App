package com.avenga.a360.service.impl;

import com.avenga.a360.domain.model.*;
import com.avenga.a360.service.SendEmailsWithLinksService;
import com.avenga.a360.service.SendService;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SendEmailsWithLinksServiceImpl implements SendEmailsWithLinksService {

    private static final String APP_URL = "http://localhost/";

    @Inject
    SendService sendService;

    @Asynchronous
    @Override
    public void sendEmailsWithLinks(Session session) {
        for (Participant participant : session.getParticipants()
        ) {
            sendService.sendEmail(createEmailWithLink(participant, session));
            System.out.println(" watek do linkow  " + Thread.currentThread().getName());
        }

    }

    @Override
    public Email createEmailWithLink(Participant participant, Session session) {

        StringBuilder mailBody = new StringBuilder();
        mailBody.append(participant.getEmail() + ", welcome to a new a360 feedback session ");
        mailBody.append(session.getSessionName() + "\n");
        mailBody.append(" \n");
        mailBody.append("You have been selected to complete a360 feedback session. By clicking the below links, you will be able to fill in feedback form for your colleagues.\n");
        mailBody.append(" \n");
        mailBody.append("Please make sure to complete this task before end of day " + formatEndDate(session.getEndDate()) + ".");
        mailBody.append(" \n");

        for (Participant participantToRate : findParticipantsToBeRatedBySingleParticipant(participant, session)) {
            mailBody.append(participantToRate.getEmail());
            mailBody.append(": ");
            mailBody.append(APP_URL + formatSessionName(session) + "/" + participantToRate.getUid());
            mailBody.append(" \n");
        }

        mailBody.append(" \n");
        mailBody.append("Thank you! ");
        return new Email(participant.getEmail(), createEmailSubject(session), mailBody.toString());
    }

    @Override
    public List<Participant> findParticipantsToBeRatedBySingleParticipant(Participant participant, Session session) {
        List<Participant> participantsToRate = new ArrayList<>();
        for (Participant participantToRate : session.getParticipants()
        ) {
            if (!participant.equals(participantToRate)) {
                participantsToRate.add(participantToRate);
            }
        }
        return participantsToRate;
    }

    public String createEmailSubject(Session session) { //zmienic na nie static
        return "New feedback session " + session.getSessionName() + " to be completed";
    }

    public String formatEndDate(LocalDateTime endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return endDate.format(formatter);
    }

    public String formatSessionName(Session session) {
        return session.getSessionName().toLowerCase().replaceAll("\\s", "");
    }
}
