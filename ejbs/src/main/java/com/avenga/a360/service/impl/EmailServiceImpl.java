package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.model.*;
import com.avenga.a360.service.EmailService;
import com.avenga.a360.service.SendService;
import org.jboss.resteasy.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class EmailServiceImpl implements EmailService {

    private final static Logger LOGGER = Logger.getLogger(EmailServiceImpl.class);

    private static final String APP_URL = "http://localhost:81/servlet/A360/#!/feedback/";

    @Inject
    AnswerDao answerDao;

    @Inject
    SendService sendService;

    @Inject
    QuestionDao questionDao;

    @Inject
    SessionDao sessionDao;

    @Override
    public List<Email> createEmailsToParticipantsWithFeedback(Session session) {
        List<Email> emailList = new ArrayList<>();
        List<Participant> participantList = session.getParticipants();
        for (Participant participant : participantList) {
            List<String> emailQuestionList = new ArrayList<>();
            List<String> emailAnswerList = new ArrayList<>();
            String mailSubject = "Feedback from survey 360 - " + session.getSessionName();
            StringBuilder mailBodyBuilder = new StringBuilder();
            mailBodyBuilder.append("Feedback session ").append(session.getSessionName()).append(" has come to an end. Please see yor feedback below:").append("\n");
            List<Question> questionsByParticipantId = questionDao.findAllQuestionsTextAndIdByParticipantId(participant.getId());
            if (!(questionsByParticipantId.get(0) == null)) {
                for (Question q : questionsByParticipantId) {
                    mailBodyBuilder.append(" \n").append(q.getQuestionText()).append("\n");
                    List<Answer> answersByParticipantIdAndQuestionId = answerDao.findAllAnswersByParticipantIdAndQuestionId(participant.getId(), q.getId());
                    if (!answersByParticipantIdAndQuestionId.isEmpty()) {
                        for (Answer answer : answersByParticipantIdAndQuestionId) {
                            mailBodyBuilder.append(answer.getAnswerText()).append(" \n");
                        }
                    } else {
                        mailBodyBuilder.append("No feedback for this question.").append("\n");
                    }
                }
            } else {
                mailBodyBuilder.append("You did not receive any feedback in this session.");
            }
            Email email = new Email(participant.getEmail(), mailSubject, mailBodyBuilder.toString(), null, null, emailQuestionList, emailAnswerList);
            emailList.add(email);
            LOGGER.info("Email with feedback created for " + participant.getEmail());
        }
        return emailList;
    }

    @Override
    public List<Email> createEmailsToParticipantsWithLinks(List<Participant> participantList, Session session) {
        List<Email> emailList = new ArrayList<>();
        String mailSubject = "Survey 360 - " + session.getSessionName();
        if (sessionDao.checkIfSessionNameExistsInDB(session.getSessionName())) {
            for (Participant mailRecipient : participantList) {
                List<String> emailEmailList = new ArrayList<>();
                List<String> emailLinkList = new ArrayList<>();
                StringBuilder mailBodyBuilder = new StringBuilder();
                mailBodyBuilder.append("You have been chosen to take part in ").append(session.getSessionName()).append(" feedback session.  By clicking the below links, you will be able to fill in feedback form for your colleagues.\n").append("\n");
                mailBodyBuilder.append("Please make sure to complete this task until ")
                        .append(formatEndDate(session.getEndDate())).append(".\n").append("\n");
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
                LOGGER.info("Email with links created for " + mailRecipient.getEmail());
            }
        }
        return emailList;
    }

    public String formatEndDate(LocalDateTime endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return endDate.format(formatter);
    }
}
