package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.domain.model.*;
import com.avenga.a360.service.QuestionService;
import com.avenga.a360.service.SendFeedbackService;
import com.avenga.a360.service.SendService;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class SendFeedbackServiceImpl implements SendFeedbackService {

    @Inject
    AnswerDao answerDao;

    @Inject
    QuestionDao questionDao;

    @Inject
    SendService sendService;

    @Inject
    QuestionService questionService;

    @Override
    @Asynchronous
    public void sendFeedback(Session session) {
        for (Participant participant : session.getParticipants()
        ) {
            sendService.sendEmail(createFeedbackEmail(participant, session));
            System.out.println(" Watek do feedbacku:  " + Thread.currentThread().getName());
        }
    }

    public String createEmailSubject(Session session) {
        return session.getName() + " - check your feedback";
    }

    @Override
    public Email createFeedbackEmail(Participant participant, Session session) {
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("Feedback session has come to an end. Please see your feedback below: \n");

        List<Question> questions = findAllQuestionsByParticipantId(participant.getId());

        for (Question q : questions) {
            mailBody.append(" \n");
            mailBody.append(q.getQuestionText() + "\n");
            List<Answer> answers = findAllAnswersByParticipantIdAndQuestionId(participant.getId(), q.getId());
            if (!answers.isEmpty()) {
                for (Answer answer : answers) {
                    mailBody.append(answer.getAnswerText() + " \n");
                }
            } else {
                mailBody.append("No answers for this question. \n");
            }
        }

        return new Email(participant.getEmail(), createEmailSubject(session), mailBody.toString());
    }

    @Override
    public List<Answer> findAllAnswersByParticipantIdAndQuestionId(Long idParticipant, Long idQuestion) {
        return answerDao.getAllAnswersByParticipantIdAndQuestionId(idParticipant, idQuestion);
    }

    @Override
    public List<Question> findAllQuestionsByParticipantId(Long participantId) {
        return questionDao.getAllQuestionsByParticipantId(participantId);
    }


}
