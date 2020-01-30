package com.avenga.a360.service;

import com.avenga.a360.domain.dto.AnswerDto;
import com.avenga.a360.domain.model.Answer;

import java.util.List;

public interface AnswerService {

 //   List<AnswerDto> findAllAnswersByParticipantId(Long id);

    boolean createAnswer(AnswerDto answerDto);
}
