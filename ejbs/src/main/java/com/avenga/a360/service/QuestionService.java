package com.avenga.a360.service;

import com.avenga.a360.dto.QuestionDto;
import com.avenga.a360.model.Question;

import java.util.List;

public interface QuestionService {

    List<Question> findAllActiveQuestions();

    Question findQuestionsById(Long id);

    List<Question> findAllQuestionsTextAndIdByParticipantId(Long id);

    boolean createQuestion(Question question);

    List<Question> getAllQuestionBySessionId(Long id);

    List<QuestionDto> questionListToQuestionDtoList(List<Question> questionList);
}
