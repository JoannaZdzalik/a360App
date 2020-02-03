package com.avenga.a360.service;

import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.domain.model.Question;

import java.util.List;

public interface QuestionService {

    List<QuestionDto> findAllActiveQuestions();

    List<QuestionDto> findQuestionsByParticipantId(Long id);
}


