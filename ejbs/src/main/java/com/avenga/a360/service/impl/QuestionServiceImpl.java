package com.avenga.a360.service.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dao.impl.QuestionDaoImpl;
import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.service.QuestionService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class QuestionServiceImpl implements QuestionService {

    @Inject
    QuestionDao questionDao;

   // public QuestionServiceImpl(QuestionDaoImpl questionDao) {
 //       this.questionDao = questionDao;
  //  }

    @Override
    public List<QuestionDto> findAllActiveQuestions() {
        List<Question> questions = questionDao.getAllActiveQuestions();
        return questions.stream()
                .map(u-> mapQuestionToQuestionDto(u))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> findQuestionsByParticipantId(Long id) {
        List<Question> questions = questionDao.getAllQuestionsByParticipantId(id);
        return questions.stream()
                .map(u-> mapQuestionToQuestionDto(u))
                .collect(Collectors.toList());
    }

    public QuestionDto mapQuestionToQuestionDto(Question question){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setQuestionText(question.getQuestionText());
        questionDto.setQuestionType(question.getQuestionType());
        questionDto.setDefaultAnswers(question.getDefaultAnswers());
        questionDto.setIsActive(question.getIsActive());
        return questionDto;
    }


}

