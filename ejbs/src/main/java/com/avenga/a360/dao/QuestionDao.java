package com.avenga.a360.dao;

import com.avenga.a360.domain.model.Question;

import java.util.List;

public interface QuestionDao {

    void save (Question question);
    Question findById(Long id);
    List<Question> getAllQuestionsByParticipantId(Long id);
    List<Question> getAllActiveQuestions();
}
