package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.domain.model.*;
import com.avenga.a360.service.SendFeedbackService;
import com.avenga.a360.service.SendService;

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

    @Override
    public boolean sendFeedback(Session session) {
        for (Participant participant : session.getParticipants()
        ) {
            sendService.sendEmail(createFeedbackEmail(participant, session));
        }
        return true;
    }

    public String createEmailSubject(Session session) {
        return session.getName() + " - check your feedback";
    }

    @Override
    public Email createFeedbackEmail(Participant participant, Session session) {
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("Feedback session has come to an end. Please see your feedback below: \n");
        mailBody.append(" \n");

        List<Question> questions = findAllQuestionsByParticipantId(participant.getId());

        for (Question q : questions) { //dla każdego pytania
            mailBody.append(q.getQuestionText() + " : \n"); //wyświetl jego treść
            List<Answer> answers = findAllAnswersByParticipantIdAndQuestionId(participant.getId(), q.getId()); //znajdź liste odpowiedzi
            if (!answers.isEmpty()) {
                for (Answer answer : answers) { //dla każdej odpowiedzi na pytanie
                    mailBody.append(answer.getAnswerText() + " \n"); // wyświetl jej treść
                }
            } else {
                mailBody.append("No answers for this question. \n");
                mailBody.append(" \n");
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
