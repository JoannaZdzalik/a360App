package com.avenga.a360.service.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dto.QuestionDto;
import com.avenga.a360.dto.EditDto.QuestionEditDto;
import com.avenga.a360.model.Question;
import com.avenga.a360.service.QuestionService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class QuestionServiceImpl implements QuestionService {

    @Inject
    QuestionDao questionDao;

    @Override
    public List<QuestionDto> findAllActiveQuestions() {
        List<Question> questions = questionDao.findAllActiveQuestions();
        return questions.stream()
                .map(question -> convertQuestionToQuestionDto(question))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> findAllDefaultQuestions() {
        List<Question> questions = questionDao.findAllDefaultQuestions();
        return questions.stream()
                .map(question -> convertQuestionToQuestionDto(question))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> findAllQuestions() {
        List<Question> questions = questionDao.findAllQuestions();
        return questions.stream()
                .map(question -> convertQuestionToQuestionDto(question))
                .collect(Collectors.toList());
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
    public boolean createQuestion(QuestionDto questionDto) {
        if (questionDto == null) {
            return false;
        } else {
            if (questionDto.getQuestion_text() == null ||
                    questionDto.getQuestion_type() == null) {
                return false;
            }
        }
        Question question = convertQuestionDtoToQuestion(questionDto);
        questionDao.createQuestion(question);
        return true;
    }

    @Override
    public boolean updateQuestion(QuestionEditDto questionEditDto) {
        Question question = questionDao.findById(questionEditDto.getQuestion_id());
        question.setIsActive(questionEditDto.getIs_active());
        question.setIsDefault(questionEditDto.getIs_default());
//        if (questionEditDto.getIs_active()) {
//            question.setIsActive(true);
//        } else {
//            question.setIsActive(false);
//        }
        questionDao.updateQuestion(question);
        return true;
    }


    @Override
    public List<Question> getAllQuestionBySessionId(Long id) {
        return questionDao.getAllQuestionBySessionId(id);
    }

    @Override
    public List<QuestionDto> convertQuestionListToQuestionDtoList(List<Question> questionList) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        for (Question q : questionList) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setQuestion_id(q.getId());
            questionDto.setQuestion_text(q.getQuestionText());
            questionDto.setQuestion_type(q.getQuestionType());
            questionDto.setDefault_answers(q.getDefaultAnswers());
            questionDto.setIs_active(q.getIsActive());
            questionDto.setIs_default(q.getIsDefault());
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    private QuestionDto convertQuestionToQuestionDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestion_id(question.getId());
        questionDto.setQuestion_text(question.getQuestionText());
        questionDto.setQuestion_type(question.getQuestionType());
        questionDto.setDefault_answers(question.getDefaultAnswers());
        questionDto.setIs_active(question.getIsActive());
        questionDto.setIs_default(question.getIsDefault());
        return questionDto;
    }

    private Question convertQuestionDtoToQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionText(questionDto.getQuestion_text());
        question.setQuestionType(questionDto.getQuestion_type());
        question.setDefaultAnswers(questionDto.getDefault_answers());
        question.setIsDefault(questionDto.getIs_default());
        return question;
    }
}
