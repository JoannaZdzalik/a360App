package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dao.impl.QuestionDaoImpl;
import com.avenga.a360.domain.model.Answer;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;

import java.util.List;

public class SendFeedbackServiceImpl extends SendService {

    AnswerDao answerDao;
    QuestionDaoImpl questionDao = new QuestionDaoImpl();

    public static String createEmailSubject(Session session) {
        return session.getName() + " - check your feedback";
    }

    public String createFeedbackEmailBody(Participant participant) {
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("Feedback session has come to an end. Please see your feedback below: \n");

        List<Question> questionsByParticipantId = questionDao.getAllQuestionsByParticipantId(participant.getId());
        Question singleQuestion = new Question();
        List<Answer> answersByParticipantIdAndQuestionId = answerDao.getAllAnswersByParticipantIdAndQuestionId(participant.getId(), singleQuestion.getId());

        for (Question question : questionsByParticipantId) { //tu dla każdego pytania mam wyświetlić liste odp
            mailBody.append(question.getQuestionText() + " : ");
            for (Answer answer : answersByParticipantIdAndQuestionId
            ) {
                mailBody.append(answer.getAnswerText());
            }
        }
        return mailBody.toString();
    }

    public boolean sendFeedback(Session session) { //na razie będzie wyswietlac tylko odpowiedzi tekstowe
        SendService sendService = new SendService();
        createEmailSubject(session);
        for (Participant participant : session.getParticipants()
        ) {
            sendService.sendEmail(participant.getEmail(), createEmailSubject(session), createFeedbackEmailBody(participant));
        }
        return true;
    }
}
