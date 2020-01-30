package com.avenga.a360.service;

import com.avenga.a360.dao.impl.AnswerDao;
import com.avenga.a360.dao.impl.ParticipantDao;
import com.avenga.a360.dao.impl.QuestionDao;
import com.avenga.a360.domain.dto.AnswerDto;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.impl.AnswerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AnswerServiceImplTest {

    @InjectMocks
    AnswerServiceImpl answerService;

    @Mock
    private AnswerDao answerDao;

    @Mock
    private QuestionDao questionDao;

    @Mock
    private ParticipantDao participantDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveCompleteAnswerWithAllValidParameters() {
        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);

        Participant asia = new Participant();
        asia.setId(1L);
        asia.setEmail("asia@yzdz");

        Participant kasia = new Participant();
        asia.setId(2L);
        asia.setEmail("asetia@yzdz");

        Session session = new Session();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.of(2021, 02, 02, 20, 00));

        AnswerDto newAnswer = new AnswerDto();
        newAnswer.setId(1L);
        newAnswer.setQuestionId(1L);
        newAnswer.setParticipantId(1L);
        newAnswer.setAnswerText("He is OK");

        when(questionDao.findById(1L)).thenReturn(question1);
        when(participantDao.findById(1L)).thenReturn(asia);

        assertTrue(answerService.createAnswer(newAnswer));
        System.out.println(newAnswer);
    }

    @Test
    public void shouldSaveAnswerWhenAnswerTextIsNull(){
        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);

        Participant asia = new Participant();
        asia.setId(1L);
        asia.setEmail("asia@yzdz");

        Participant kasia = new Participant();
        asia.setId(2L);
        asia.setEmail("asetia@yzdz");

        Session session = new Session();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.of(2021, 02, 02, 20, 00));

        AnswerDto newAnswer = new AnswerDto();
        newAnswer.setId(1L);
        newAnswer.setQuestionId(1L);
        newAnswer.setParticipantId(1L);
        newAnswer.setAnswerText(null);

        when(questionDao.findById(1L)).thenReturn(question1);
        when(participantDao.findById(1L)).thenReturn(asia);

        assertTrue(answerService.createAnswer(newAnswer));
        System.out.println(newAnswer);
    }
}
