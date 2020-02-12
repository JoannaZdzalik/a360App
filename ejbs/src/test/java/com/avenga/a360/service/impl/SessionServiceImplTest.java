package com.avenga.a360.service.impl;


import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.Email;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Question;
import com.avenga.a360.model.Session;
import com.avenga.a360.service.EmailService;
import com.avenga.a360.service.SendService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.any;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SessionServiceImplTest {

    @InjectMocks
    SessionServiceImpl sessionService;

    @Mock
    QuestionDao questionDao;

    @Mock
    ParticipantDao participantDao;

    @Mock
    SessionDao sessionDao;

    @Mock
    SendService sendService;

    @Mock
    EmailService emailService;


    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void init() throws Exception{
        sessionService = new SessionServiceImpl();
    }

    @Test
    public void shouldNotCreateSessionWithoutQuestionsAndParticipants() {

        SessionDto sessionDto = new SessionDto();
        sessionDto.setSessionName("Session name");
        sessionDto.setEndDate(LocalDateTime.of(2020, 02, 25, 00, 00, 00, 01));
        sessionDto.setIsSent(false);

        Mockito.when(questionDao.findAllActiveQuestions()).thenReturn(null);
        Mockito.when(sendService.checkSmtpServer()).thenReturn(true);

        Assert.assertEquals(sessionService.createSession(sessionDto, null).getStatus(), "fail");
        sessionService.createSession(sessionDto, null).getStatusMessageList().forEach(r -> System.out.println(r.getMessage()));
    }

    @Test
    public void shouldNotCreateSessionWithoutQuestions() {
        List<ParticipantDto> participantsDto = new ArrayList<>();
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setEmail("a@a.com");
        participantsDto.add(participantDto);

        Mockito.when(questionDao.findAllActiveQuestions()).thenReturn(null);
        Mockito.when(sendService.checkSmtpServer()).thenReturn(true);

        SessionDto sessionDto = new SessionDto();
        sessionDto.setSessionName("Session name");
        sessionDto.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));

        Assert.assertEquals(sessionService.createSession(sessionDto, null).getStatus(), "fail");
        sessionService.createSession(sessionDto, participantsDto).getStatusMessageList().forEach(r -> System.out.println(r.getMessage()));
    }

    @Test
    public void shouldCreateSessionWithAllValidParameters() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("Question");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        List<ParticipantDto> participantsDto = new ArrayList<>();
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setEmail("a@a.com");
        participantsDto.add(participantDto);

        Mockito.when(questionDao.findAllActiveQuestions()).thenReturn(questions);
        Mockito.when(participantDao.findByUId(sessionService.generateUIdForParticipant(15))).thenReturn(null);
        Mockito.when(sendService.checkSmtpServer()).thenReturn(true);

        SessionDto sessionDto = new SessionDto();
        sessionDto.setSessionName("Session name");
        sessionDto.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));


        Assert.assertEquals(sessionService.createSession(sessionDto, participantsDto).getStatus(), "success");
        sessionService.createSession(sessionDto, participantsDto).getStatusMessageList().forEach(r -> System.out.println(r.getMessage()));
    }

    @Test
    public void shouldNotCreateSessionWithoutSessionNameAndEndDateIsBeforeNow() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("Question");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        List<ParticipantDto> participantsDto = new ArrayList<>();
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setEmail("a@a.com");
        participantsDto.add(participantDto);

        Mockito.when(questionDao.findAllActiveQuestions()).thenReturn(questions);
        Mockito.when(sendService.checkSmtpServer()).thenReturn(true);

        SessionDto sessionDto = new SessionDto();
        sessionDto.setEndDate(LocalDateTime.of(2020, 01, 20, 00, 00, 00, 01));


        Assert.assertEquals(sessionService.createSession(sessionDto, participantsDto).getStatus(), "fail");
        sessionService.createSession(sessionDto, participantsDto).getStatusMessageList().forEach(r -> System.out.println(r.getMessage()));
    }

    @Test
    public void mappingSessionDtoToSession() {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setSessionName("session");
        sessionDto.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));

        Session session = sessionService.sessionDtoToSession(sessionDto);

        Assert.assertEquals(session.getSessionName(),sessionDto.getSessionName());
    }

    @Test
    public void shouldFindAllActiveSessionsWhereEndDateIsAfterNow() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("Question");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        List<Participant> participants = new ArrayList<>();
        Participant participant = new Participant();
        participant.setEmail("a@a.com");
        participants.add(participant);

        Session session = new Session();
        session.setQuestions(questions);
        session.setParticipants(participants);
        session.setSessionName("Session name");
        session.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));
        session.setIsSent(false);

        List<Session> sessions = new ArrayList<>();
        sessions.add(session);

        Mockito.when(sessionDao.findAllSessionsIsSentFalseAndEndDateIsAfterNow()).thenReturn(sessions);

        Assert.assertEquals(sessionService.findAllSessionsIsSentFalseAndEndDateIsAfterNow().get(0).getIsSent(), sessions.get(0).getIsSent());
    }



}