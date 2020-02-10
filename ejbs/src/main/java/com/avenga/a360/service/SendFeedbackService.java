package com.avenga.a360.service;

import com.avenga.a360.domain.model.*;

import java.util.List;

public interface SendFeedbackService {
    void sendFeedback(Session session);

    Email createFeedbackEmail(Participant participant, Session session);

    List<Answer> findAllAnswersByParticipantIdAndQuestionId(Long idParticipant, Long idQuestion);

    List<Question> findAllQuestionsByParticipantId(Long participantId);
}
