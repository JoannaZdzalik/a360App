package com.avenga.a360.dao;

import com.avenga.a360.model.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> findAllAnswersByParticipantId(Long id);

    List<Answer> findAllAnswersByParticipantIdAndQuestionId(Long idParticipant, Long idQuestion);

    List<Answer> findAllAnswersForOneSession(String name);

    boolean createAnswer(Answer answer);

    List<Answer> findAllAnswers();
}
