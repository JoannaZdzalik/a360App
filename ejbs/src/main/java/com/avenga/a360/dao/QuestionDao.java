package com.avenga.a360.dao;

import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.service.impl.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class QuestionDao implements Dao<QuestionDto> {

    List<QuestionDto> questions = new ArrayList<>();


    @Override
    public Optional<QuestionDto> get(long id) {
        return Optional.ofNullable(questions.get((int) id));
    }

    @Override
    public List<QuestionDto> getAll() {
        return questions;
    }

    @Override
    public void save(QuestionDto question) {
        questions.add(question);
    }

    @Override
    public void delete(QuestionDto question) {
        questions.remove(question);
    }
}
