package com.avenga.a360.service.impl;

import com.avenga.a360.domain.model.*;
import com.avenga.a360.service.SendService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SendEmailsWithLinksServiceImpl {

    private static final String APP_URL = "http://localhost/";

    @Inject
    SendService sendService;

 //   public SendEmailsWithLinksServiceImpl(SendService sendService) {
  //      this.sendService = sendService;
  //  }

    public boolean sendEmailsWithLinks(Session session) {
        for (Participant participant : session.getParticipants()
        ) {
            sendService.sendEmail(createEmailWithLink(participant, session));
        }
        return true;
    }

    public Email createEmailWithLink(Participant participant, Session session) {
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("Welcome to a360 feedback session: ");
        mailBody.append(session.getName() + "\n");
        mailBody.append(" \n");
        mailBody.append("You have been selected to complete a360 feedback session. By clicking the below links, you will be able to fill in feedback form for your colleagues.\n");
        mailBody.append(" \n");
        mailBody.append("Please make sure to complete this task before end of day ");
        mailBody.append(formatEndDate(session.getEndDate()) + "\n");
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

    public static String createEmailSubject(Session session) {
        return "New feedback session " + session.getName() + " to be completed";
    }

    public static String formatEndDate(LocalDateTime endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return endDate.format(formatter);
    }

    public static String formatSessionName(Session session) {
        return session.getName().toLowerCase().replaceAll("\\s", "");
    }


    }
