package com.avenga.a360.service;

import com.avenga.a360.dao.impl.AnswerDao;
import com.avenga.a360.dao.impl.ParticipantDao;
import com.avenga.a360.dao.impl.QuestionDao;
import com.avenga.a360.domain.dto.AnswerDto;
import com.avenga.a360.domain.model.Answer;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.impl.AnswerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
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
        asia.setUid("123456789012345");
        asia.setEmail("asia@yzdz");

        Session session = new Session();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.of(2021, 11, 02, 20, 00));

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
    public void shouldNotSaveAnswerWhenAnswerTextIsNull() {
        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);

        Participant asia = new Participant();
        asia.setId(1L);
        asia.setEmail("asia@yzdz");

        Session session = new Session();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.of(2021, 10, 02, 20, 00));

        AnswerDto newAnswer = new AnswerDto();
        newAnswer.setId(1L);
        newAnswer.setQuestionId(1L);
        newAnswer.setParticipantId(1L);
        newAnswer.setAnswerText(null);

        when(questionDao.findById(1L)).thenReturn(question1);
        when(participantDao.findById(1L)).thenReturn(asia);

        assertFalse(answerService.createAnswer(newAnswer));
    }

    @Test
    public void shouldNotSaveAnswerWhenQuestionIsNull(){
        Participant asia = new Participant();
        asia.setId(1L);
        asia.setEmail("asia@yzdz");
        asia.setUid("123456789012345");

        Session session = new Session();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.of(2021, 11, 02, 20, 00));

        AnswerDto newAnswer = new AnswerDto();
        newAnswer.setId(1L);
        newAnswer.setParticipantId(1L);
        newAnswer.setAnswerText("He is OK");

        when(participantDao.findById(1L)).thenReturn(asia);

        assertFalse(answerService.createAnswer(newAnswer));
    }

    @Test
    public void shouldNotSaveAnswerWhenParticipantIsNull(){
        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);

        Session session = new Session();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.of(2021, 11, 02, 20, 00));

        AnswerDto newAnswer = new AnswerDto();
        newAnswer.setId(1L);
        newAnswer.setQuestionId(1L);
        newAnswer.setAnswerText("He is OK");

        when(questionDao.findById(1L)).thenReturn(question1);

        assertFalse(answerService.createAnswer(newAnswer));
    }

    @Test
    public void shouldMapAnswerDtoToAnswer() {
        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);

        Participant asia = new Participant();
        asia.setId(1L);
        asia.setEmail("asia@yzdz");

        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(1L);
        answerDto.setAnswerText("He is OK");
        answerDto.setQuestionId(1L);
        answerDto.setParticipantId(1L);

        Answer answer = answerService.mapAnswerDtoToAnswer(answerDto, question1, asia);

        assertAll(
                () -> assertEquals(answer.getAnswerText(), answerDto.getAnswerText()),
                () -> assertEquals(answer.getQuestion().getId(), answerDto.getQuestionId()),
                () -> assertEquals(answer.getParticipant().getId(), answerDto.getParticipantId()),
                () -> assertEquals(answer.getId(), answerDto.getId())
        );
    }

    @Test
    public void shouldFindAllAnswersByParticipantId() {
        Question question = new Question();
        question.setId(1L);
        question.setQuestionText("'What do you think of this person?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);

        List<Participant> participants = new ArrayList<>();
        Participant asia = new Participant();
        asia.setId(1L);
        asia.setEmail("asia@zdz");
        participants.add(asia);

        Session session = new Session();
        session.setId(1L);
        session.setParticipants(participants);
        session.setName("Avenga first edition");
        session.setEndDate(LocalDateTime.of(2020, 06, 20, 00, 00, 00, 01));
        session.setSent(false);

        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(1L, question, asia, "She is cool"));
        answers.add(new Answer(2L, question, asia, "Very nice"));
        answers.add(new Answer(3L, question, asia, "OK"));

        Mockito.when(answerDao.getAllAnswersByParticipantId(asia.getId())).thenReturn(answers);

        assertAll(
                () ->  assertEquals(answerService.findAllAnswersByParticipantId(1L).get(0).getAnswerText(), answers.get(0).getAnswerText(), "She is cool" ),
                () ->  assertEquals(answerService.findAllAnswersByParticipantId(1L).get(1).getAnswerText(), answers.get(1).getAnswerText() ),
                () ->  assertEquals(answerService.findAllAnswersByParticipantId(1L).get(2).getAnswerText(), answers.get(2).getAnswerText() ),
                () -> Assertions.assertEquals(3, answers.size())
        );
    }
}

