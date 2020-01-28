package com.avenga.a360.service.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;


public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<QuestionDto> findAllActiveQuestions() {
        return questionDao.getAll().stream()
                .filter(QuestionDto::getIsActive)
                .collect(Collectors.toList());
    }

}

