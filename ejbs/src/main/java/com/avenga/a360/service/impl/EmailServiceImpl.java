package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.model.Answer;
import com.avenga.a360.model.Email;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Session;
import com.avenga.a360.service.EmailService;
import com.avenga.a360.service.SendService;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EmailServiceImpl implements EmailService {

    private static final String APP_URL = "http://localhost:81/servlet/A360/#!/feedback/";

    @Inject
    AnswerDao answerDao;

    @Inject
    SendService sendService;

    @Override
    public List<Email> createEmailsToParticipantsWithFeedback(Session session) {
        List<Email> emailList = new ArrayList<>();
        List<Participant> participantList = session.getParticipants();
        for (Participant participant : participantList) {
            List<String> emailQuestionList = new ArrayList<>();
            List<String> emailAnswerList = new ArrayList<>();
            String mailSubject = "Feedback from survey 360 - " + session.getSessionName();
            StringBuilder mailBodyBuilder = new StringBuilder();
            List<Answer> answersListByParticipantId = answerDao.findAllAnswersByParticipantId(participant.getId());
            if(!(answersListByParticipantId.get(0) == null)){
                for (Answer answer : answersListByParticipantId) {
                    mailBodyBuilder.append(answer.getQuestion().getQuestionText()).append("\n")
                            .append(answer.getAnswerText()).append("\n").append("\n");
                    emailQuestionList.add(new String(answer.getQuestion().getQuestionText()));
                    emailAnswerList.add(new String(answer.getAnswerText()));
                }
            }else {
                mailBodyBuilder.append("No Feedback for You.");
            }
            Email email = new Email(participant.getEmail(), mailSubject, mailBodyBuilder.toString(), null, null, emailQuestionList, emailAnswerList);
            emailList.add(email);
        }
        return emailList;
    }

    @Override
    public List<Email> createEmailsToParticipantsWithLinks(List<Participant> participantList, String sessionName) {
        List<Email> emailList = new ArrayList<>();
        String mailSubject = "Survey 360 - " + sessionName;
        for (Participant mailRecipient : participantList) {
            List<String> emailEmailList = new ArrayList<>();
            List<String> emailLinkList = new ArrayList<>();
            StringBuilder mailBodyBuilder = new StringBuilder();
            for (Participant participant : participantList) {
                if (!mailRecipient.equals(participant)) {
                    mailBodyBuilder.append("Feedback for: ");
                    mailBodyBuilder.append(participant.getEmail());
                    mailBodyBuilder.append(" - ").append(APP_URL).append(participant.getUId()).append("\n");
                    emailEmailList.add(new String(participant.getEmail()));
                    emailLinkList.add(new String(APP_URL + participant.getUId()));
                }
            }
            Email email = new Email((mailRecipient.getEmail()), mailSubject, mailBodyBuilder.toString(), emailEmailList, emailLinkList, null, null);
            emailList.add(email);
        }
        return emailList;
    }
}
