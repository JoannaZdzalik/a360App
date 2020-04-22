package com.avenga.a360.service;

import com.avenga.a360.dto.AnswerDto;
import com.avenga.a360.dto.AnswerSessionDto;
import com.avenga.a360.model.Answer;
import com.avenga.a360.model.response.Status;

import java.util.List;

public interface AnswerService {
    List<Answer> findAllAnswersByParticipantId(Long id);

    Status createAnswer(AnswerDto answerDto);

    List<AnswerDto> findAllAnswersDto();

    List<Status> createAnswersDto(List<AnswerDto> lists);
}
