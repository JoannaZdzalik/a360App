package com.avenga.a360.service;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.dao.impl.ParticipantDaoImpl;
import com.avenga.a360.dao.impl.QuestionDaoImpl;
import com.avenga.a360.dao.impl.SessionDaoImpl;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.impl.SendEmailsWithLinksServiceImpl;
import com.avenga.a360.service.impl.SessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class SessionServiceImplTest {

    @InjectMocks
    SessionServiceImpl sessionService;

    @Mock
    SendEmailsWithLinksServiceImpl sendEmailsWithLinksService;

    @Mock
    private SessionDaoImpl sessionDao;

    @Mock
    private QuestionDaoImpl questionDao;

    @Mock
    private ParticipantDaoImpl participantDao;


    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveCompleteSessionWithCorrectParameters() {
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);
        questions.add(question1);

        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto kasia = new ParticipantDto();
        kasia.setUid("UIDasdfgh123456");
        kasia.setEmail("kasia@yzdz");
        participants.add(kasia);

        SessionDto newSession = new SessionDto();
        newSession.setName("Avenga First Edition");
        newSession.setEndDate(LocalDateTime.now().plusDays(10L));

     //   Session s = new Session();
        Session s = sessionService.mapSessionDtoToSession(newSession);


        when(questionDao.getAllActiveQuestions()).thenReturn(questions); //mocki do serwisów, daosów itp. z których korzystam w sprawdzanej metodzie
        when(participantDao.findByUid(sessionService.generateUidFromAlphaNumericString(15))).thenReturn(null);

        assertTrue(sessionService.createSession(newSession, participants)); //injectMocks do tego serwisu który sprawdzam
   }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenListOfParticipantsIsNull() {
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);
        questions.add(question1);

        SessionDto newSession = new SessionDto();
        newSession.setName("Avenga First Edition");
        newSession.setEndDate(LocalDateTime.now().plusDays(10L));

        when(questionDao.getAllActiveQuestions()).thenReturn(questions);

        assertThrows(IllegalArgumentException.class, () -> sessionService.createSession(newSession, null));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenListOfParticipantsIsEmpty() {
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);
        questions.add(question1);

        List<ParticipantDto> participants = new ArrayList<>();

        SessionDto newSession = new SessionDto();
        newSession.setName("Avenga First Edition");
        newSession.setEndDate(LocalDateTime.now().plusDays(10L));

        when(questionDao.getAllActiveQuestions()).thenReturn(questions);

        assertThrows(IllegalArgumentException.class, () -> sessionService.createSession(newSession, participants));

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSetEndDateIsInThePast() {

        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);
        questions.add(question1);

        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto asia = new ParticipantDto();
        asia.setEmail("asia@zdz");
        asia.setUid("123456789012345");
        participants.add(asia);

        SessionDto newSession = new SessionDto();
        newSession.setName("Avenga First Edition");
        newSession.setSent(false);
        newSession.setEndDate(LocalDateTime.of(2019, 02, 02, 20, 00));

        when(questionDao.getAllActiveQuestions()).thenReturn(questions);

        assertThrows(IllegalArgumentException.class, () -> sessionService.createSession(newSession, participants));

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSetEndDateIsNull() {
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);
        questions.add(question1);

        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto asia = new ParticipantDto();
        asia.setEmail("asia@zdz");
        asia.setUid("123456789012345");
        participants.add(asia);

        SessionDto newSession = new SessionDto();
        newSession.setName("Java Academy A360");
        newSession.setSent(false);
        newSession.setEndDate(null);

        when(questionDao.getAllActiveQuestions()).thenReturn(questions);

        assertThrows(IllegalArgumentException.class, () -> sessionService.createSession(newSession, participants), "endDate in a test must be left null!");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSetSessionNameIsNull() {
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);
        questions.add(question1);

        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto asia = new ParticipantDto();
        asia.setEmail("asia@zdz");
        asia.setUid("123456789012345");
        participants.add(asia);

        SessionDto newSession = new SessionDto();
        newSession.setName(null);
        newSession.setSent(false);
        newSession.setEndDate(LocalDateTime.now().plusDays(10L));

        when(questionDao.getAllActiveQuestions()).thenReturn(questions);

        assertThrows(IllegalArgumentException.class, () -> sessionService.createSession(newSession, participants), "To pass test, session name must be left null!");
    }

    @Test
    public void shouldNotCreateSessionWhenListOfQuestionsIsEmpty() {
        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto asia = new ParticipantDto();
        asia.setEmail("asia@zdz");
        participants.add(asia);

        SessionDto newSession = new SessionDto();
        newSession.setName("Avenga First Edition");
        newSession.setSent(false);
        newSession.setEndDate(LocalDateTime.now().plusDays(10L));

        List<Question> questions = new ArrayList<>();

        when(questionDao.getAllActiveQuestions()).thenReturn(questions);
        when(participantDao.findByUid(sessionService.generateUidFromAlphaNumericString(15))).thenReturn(null);

        assertFalse(sessionService.createSession(newSession, participants));
    }

    @Test
    public void shouldNotCreateSessionWhenListOfQuestionsIsNull() {
        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto asia = new ParticipantDto();
        asia.setEmail("asia@zdz");
        participants.add(asia);

        SessionDto newSession = new SessionDto();
        newSession.setName("Avenga First Edition");
        newSession.setSent(false);
        newSession.setEndDate(LocalDateTime.now().plusDays(10L));

        when(participantDao.findByUid(sessionService.generateUidFromAlphaNumericString(15))).thenReturn(null);

        assertFalse(sessionService.createSession(newSession, participants));
    }

    @Test
    public void shouldFindSessionsEndedInThePastButNotSent() {
        List<Session> allSessions = new ArrayList<>();
        allSessions.add(new Session(1L, "AvengaFirstEdition", LocalDateTime.now().plusDays(10L), false, null, null));

        when(sessionDao.getAllSessionsToSend()).thenReturn(allSessions);

        assertEquals(1, sessionService.findSessionsEndedInThePastButNotSent().size());
    }

    @Test
    public void shouldMapSessionDtoToSession() {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(1L);
        sessionDto.setName("SessionOne");
        sessionDto.setEndDate(LocalDateTime.now().plusDays(10L));

        Session session = sessionService.mapSessionDtoToSession(sessionDto);

        assertEquals(session.getId(), sessionDto.getId());
    }

    @Test
    public void shouldMapSessionToSessionDto() {
        Session session = new Session();
        session.setId(1L);
        session.setSessionName("Session First Edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));

        SessionDto sessionDto = sessionService.mapSessionToSessionDto(session);

        assertEquals(session.getId(), sessionDto.getId());
        assertEquals(session.getSessionName(), sessionDto.getName());
        assertEquals(session.getEndDate(), sessionDto.getEndDate());
    }

    @Test
    public void shouldGenerateUid() {
        String generatedUid = sessionService.generateUidFromAlphaNumericString(15);
        assertEquals(generatedUid.length(), 15);
    }

    @Test
    @DisplayName("Should regenerate uid if generated String already exists in a database")
    public void shouldGenerateUidAsLongAsGeneratedStringIsNotUnique(){}
}
