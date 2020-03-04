package com.avenga.a360.dao;

import com.avenga.a360.model.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAllActiveQuestions();

    List<Question> findAllQuestions();

    List<Question> findAllQuestionsTextAndIdByParticipantId(Long id);

    boolean createQuestion(Question question);

    boolean updateQuestion (Question question);

    Question findById(Long id);

    List<Question> getAllQuestionBySessionId(Long id);
}
