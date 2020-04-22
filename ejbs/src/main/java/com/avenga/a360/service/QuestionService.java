package com.avenga.a360.service;

import com.avenga.a360.dto.QuestionDto;
import com.avenga.a360.dto.EditDto.QuestionEditDto;
import com.avenga.a360.model.Question;

import java.util.List;

public interface QuestionService {


    List<QuestionDto> findAllDefaultQuestions();

    List<QuestionDto> findAllQuestions();

    Question findQuestionsById(Long id);

    List<Question> findAllQuestionsTextAndIdByParticipantId(Long id);

    boolean createQuestion(QuestionDto questionDto);

    List<Question> getAllQuestionBySessionId(Long id);

    List<QuestionDto> convertQuestionListToQuestionDtoList(List<Question> questionList);

    boolean updateQuestion(QuestionEditDto questionEditDto);
}
