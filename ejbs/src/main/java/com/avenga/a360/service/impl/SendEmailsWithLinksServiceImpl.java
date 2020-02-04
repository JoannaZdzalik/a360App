package com.avenga.a360.service.impl;

import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SendEmailsWithLinksServiceImpl {

    private static final String APP_URL = "http://localhost/";

    public boolean sendParticipantsEmailsWithLinks(Session session) {
        SendService sendService = new SendService();
        createEmailSubject(session);
        for (Participant participant : session.getParticipants()
        ) {
            sendService.sendEmail(participant.getEmail(), createEmailSubject(session), createEmailBodyWithLinks(participant, session));
        }
        return true;
    }

    public String createEmailBodyWithLinks(Participant participant, Session session) {
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("Welcome to a360 feedback session: ");
        mailBody.append(session.getName() + "\n");
        mailBody.append(" \n");
        mailBody.append("You have been selected to complete a360 feedback session. By clicking the below links, you will be able to fill in feedback form for your colleagues.\n");
        mailBody.append(" \n");
        mailBody.append("Please make sure to complete this task before end of day ");
        mailBody.append(formatEndDate(session.getEndDate()) + "\n");
        mailBody.append(" \n");

        for (Participant participantToRate : session.getParticipants()
        ) {
            if (!participant.equals(participantToRate)) {
                mailBody.append(participantToRate.getEmail());
                mailBody.append(": ");
                mailBody.append(APP_URL + formatSessionName(session) + "/" + participantToRate.getUid());
                mailBody.append(" \n");
            }
        }
        mailBody.append(" \n");
        mailBody.append("Thank you! ");
        return mailBody.toString();
    }

    public List<Participant> findAllParticipantsToRate(Participant participant, Session session){
//        StringBuilder mailBody = new StringBuilder();
//        for (Participant participantToRate : session.getParticipants()
//        ) {
//            if (!participant.equals(participantToRate)) {
//                mailBody.append(participantToRate.getEmail());
//                mailBody.append(": ");
//                mailBody.append(APP_URL + formatSessionName(session) + "/" + participantToRate.getUid());
//                mailBody.append(" \n");
//            }
//        }
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
