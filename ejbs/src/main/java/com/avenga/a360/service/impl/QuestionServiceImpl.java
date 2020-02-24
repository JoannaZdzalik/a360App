package com.avenga.a360.service.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dto.QuestionDto;
import com.avenga.a360.model.Question;
import com.avenga.a360.service.QuestionService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
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

    @Override
    public List<Question> getAllQuestionBySessionId(Long id) {
        return questionDao.getAllQuestionBySessionId(id);
    }

    @Override
    public List<QuestionDto> questionListToQuestionDtoList(List<Question> questionList) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question q : questionList) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setQuestion_id(q.getId());
            questionDto.setQuestion_text(q.getQuestionText());
            questionDto.setQuestion_type(q.getQuestionType());
            questionDto.setDefault_answers(q.getDefaultAnswers());
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

}
