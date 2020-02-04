package com.avenga.a360.service.impl;

import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.SendService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SendEmailsWithLinksServiceImpl extends SendService {

     private static final String APP_URL = "http://localhost/";

    public static boolean sendParticipantsEmailsWithLinks(SessionDto sessionDto) {
        for (ParticipantDto participantDto : sessionDto.getParticipants()
        ) {
            createEmailSubject(sessionDto);
            createEmailBodyWithLinks(participantDto, sessionDto);
            sendEmail(participantDto.getEmail(), createEmailSubject(sessionDto), createEmailBodyWithLinks(participantDto, sessionDto));
        }
        return true;
    }

    public static String createEmailBodyWithLinks(ParticipantDto participantDto, SessionDto sessionDto) {
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("Welcome to a360 feedback session: ");
        mailBody.append(sessionDto.getName() + "\n");
        mailBody.append(" \n");
        mailBody.append("You have been selected to complete a360 feedback session. By clicking the below links, you will be able to fill in feedback form for your colleagues.\n");
        mailBody.append(" \n");
        mailBody.append("Please make sure to complete this task before end of day ");
        mailBody.append(formatEndDate(sessionDto.getEndDate()) + "\n");
        mailBody.append(" \n");

        for (ParticipantDto participantToRate : sessionDto.getParticipants()
        ) {
            if (!participantDto.equals(participantToRate)) {
                mailBody.append(participantToRate.getEmail());
                mailBody.append(": ");
                mailBody.append(APP_URL + formatSessionName(sessionDto) + "/" + participantToRate.getUid());
                mailBody.append(" \n");
            }
        }
        mailBody.append(" \n");
        mailBody.append("Thank you! ");
        return mailBody.toString();
    }

    public static String createEmailSubject(SessionDto sessionDto) {
        return "New feedback session " + sessionDto.getName() + " to be completed";
    }

    public static String formatEndDate(LocalDateTime endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return endDate.format(formatter);
    }

    public static String formatSessionName(SessionDto sessionDto){
        return sessionDto.getName().toLowerCase().replaceAll("\\s","");
    }

    public static void main(String[] args) {
        List<QuestionDto> questions = new ArrayList<>();
        QuestionDto question1 = new QuestionDto();
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);
        questions.add(question1);

        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto kasia = new ParticipantDto();
        kasia.setEmail("kasia@yzdz");
        kasia.setUid("123456789012345");
        participants.add(kasia);

        ParticipantDto asia = new ParticipantDto();
        asia.setEmail("asia@zdz");
        asia.setUid("asdfghjklzxcvbn");
        participants.add(asia);

        ParticipantDto jagna = new ParticipantDto();
        jagna.setEmail("jagienka@dvdv");
        jagna.setUid("999rrr222hhhAAA");
        participants.add(jagna);

        SessionDto session = new SessionDto();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));
        session.setParticipants(participants);

        sendParticipantsEmailsWithLinks(session);
    }
}
