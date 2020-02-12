package com.avenga.a360.dao;

import com.avenga.a360.model.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> findAllAnswersByParticipantId(Long id);

    boolean createAnswer(Answer answer);
}
