package com.avenga.a360.dao;

import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.service.impl.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class QuestionDao implements Dao<Question> {

    List<Question> questions = new ArrayList<>();

    @Override
    public Optional<Question> get(long id) {
        return Optional.ofNullable(questions.get((int) id));
    }

    @Override
    public List<Question> getAll() {
        return questions;
    }

    @Override
    public void save(Question question) {
        questions.add(question);
    }

    @Override
    public void delete(Question question) {
        questions.remove(question);
    }

    public List<Question> getAllActiveQuestions() {
        return questions;
    }
}
