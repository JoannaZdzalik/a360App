package com.avenga.a360.service.impl;

import com.avenga.a360.dao.impl.QuestionDao;
import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;


public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

 //   @Override
//    public List<QuestionDto> findAllActiveQuestions() {
//        List<Question> questions = questionDao.getAllActiveQuestions();
//        return questions.stream()
//                .map(u-> new QuestionDto(u.getId(), u.getQuestionText(), u.getQuestionType(), u.getDefaultAnswers(), u.getIsActive(), null, null))
//                .collect(Collectors.toList());
//    }




}

