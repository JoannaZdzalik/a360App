package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.dao.impl.QuestionDaoImpl;
import com.avenga.a360.domain.model.*;

import javax.inject.Inject;
import java.util.List;

public class SendFeedbackServiceImpl extends SendService {

    AnswerDao answerDao;
    QuestionDaoImpl questionDao = new QuestionDaoImpl();

    @Inject
    SendService sendService;

    public boolean sendFeedback(Session session) {
        for (Participant participant : session.getParticipants()
        ) {
            sendService.sendEmail(createFeedbackEmail(participant, session));
        }
        return true;
    }

    public static String createEmailSubject(Session session) {
        return session.getName() + " - check your feedback";
    }

    public Email createFeedbackEmail(Participant participant, Session session) {
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("Feedback session has come to an end. Please see your feedback below: \n");

        List<Question> questions = findAllQuestionsByParticipantId(participant.getId());
        for (Question q : questions) { //dla każdego pytania
            mailBody.append(q.getQuestionText() + " : "); //wyświetl jego treść
            List<Answer> answers = findAllAnswersByParticipantIdAndQuestionId(participant.getId(), q.getId()); //znajdź liste odpowiedzi
            for (Answer answer : answers) { //dla każdej odpowiedzi na pytanie
                mailBody.append(answer.getAnswerText()); // wyświetl jej treść
            }
        }
        return new Email(participant.getEmail(), createEmailSubject(session), mailBody.toString());
    }

    public List<Answer> findAllAnswersByParticipantIdAndQuestionId(Long idParticipant, Long idQuestion) {
        return answerDao.getAllAnswersByParticipantIdAndQuestionId(idParticipant, idQuestion);
    }

    public List<Question> findAllQuestionsByParticipantId(Long participantId) {
        return questionDao.getAllQuestionsByParticipantId(participantId);
    }
}
