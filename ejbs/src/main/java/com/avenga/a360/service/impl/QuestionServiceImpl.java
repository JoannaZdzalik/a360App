package com.avenga.a360.service.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.model.Question;
import com.avenga.a360.service.QuestionService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class QuestionServiceImpl implements QuestionService {

    @Inject
    QuestionDao questionDao;

    @Override
    public List<Question> findAllActiveQuestions() {
        return questionDao.findAllActiveQuestions();

    }

    @Override
    public Question findQuestionsById(Long id) {
        return questionDao.findById(id);

    }

    @Override
    public List<Question> findAllQuestionsTextAndIdByParticipantId(Long id) {
        return questionDao.findAllQuestionsTextAndIdByParticipantId(id);
    }

    @Override
    public boolean createQuestion(Question question) {
        if (question == null) {
            return false;
        } else {
            if (question.getDefaultAnswers() == null || question.getQuestionText() == null ||
                    question.getQuestionType() == null) {
                return false;
            }
        }
        questionDao.createQuestion(question);
        return true;
    }

}
