package com.avenga.a360.service.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.model.Question;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImplTest {

    @InjectMocks
    QuestionServiceImpl questionService;

    @Mock
    public QuestionDao questionDao;

    @BeforeEach
    void steUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAllActiveQuestions() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("'What do you value him for?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        Mockito.when(questionDao.findAllActiveQuestions()).thenReturn(questions);

        Assert.assertEquals(questionService.findAllActiveQuestions().get(0).getQuestion_text(), questions.get(0).getQuestionText());
    }

    @Test
    public void shouldReturnAllNoActiveQuestion() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("'What do you value him for?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        question.setIsActive(false);
        questions.add(question);

        Mockito.when(questionDao.findAllActiveQuestions()).thenReturn(questions);

        Assert.assertEquals(false, questions.get(0).getIsActive());

    }

    @Test
    public void shouldFindAllQuestionsByParticipantId() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("'What do you value him for?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        Mockito.when(questionDao.findAllQuestionsTextAndIdByParticipantId(question.getId())).thenReturn(questions);

        Assert.assertEquals(questionService.findAllQuestionsTextAndIdByParticipantId(question.getId()).get(0).getQuestionText(), question.getQuestionText());
    }

    @Test
    public void shouldFindQuestionById() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setId(1L);
        question.setQuestionText("'What do you value him for?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        Mockito.when(questionDao.findById(question.getId())).thenReturn(question);

        Assert.assertEquals(questionService.findQuestionsById(question.getId()).getQuestionText(), question.getQuestionText());
    }

}