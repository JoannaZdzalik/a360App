package com.avenga.a360.dao;

import com.avenga.a360.domain.model.Answer;

import java.util.List;

public interface AnswerDao {

    void save(Answer answer);
    List<Answer> getAllAnswersByParticipantId(Long id);
}
