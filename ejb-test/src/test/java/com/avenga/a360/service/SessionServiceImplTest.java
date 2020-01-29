package com.avenga.a360.service;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.impl.SessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class SessionServiceImplTest {

    @InjectMocks
    SessionServiceImpl sessionServiceImpl;

//    @Mock
//    private SessionDao sessionDao;

    @Mock
    private QuestionDao questionDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void shouldFindSessionsEndedInThePastButNotSent() {
//        List<Session> allSessions = new ArrayList<>();
//        allSessions.add(new Session(1L, "AvengaFirstEdition", LocalDateTime.of(2019, 10, 10, 12, 00), false, null, null));
//
//        when(sessionDao.getAllSessionsToSend()).thenReturn(allSessions);
//
//        assertEquals(1, sessionServiceImpl.findSessionsToSend().size());
//    }


    @Test
    public void shouldSaveCompleteSessionWithAllValidParameters() {
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);
        questions.add(question1);

        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto asia = new ParticipantDto();
        asia.setEmail("asia@zdz");
        ParticipantDto kasia = new ParticipantDto();
        kasia.setEmail("kasia@yzdz");
        participants.add(kasia);

        SessionDto newSession = new SessionDto();
        newSession.setName("Avenga First Edition");
        newSession.setEndDate(LocalDateTime.of(2020, 02, 02, 20, 00));

        when(questionDao.getAll()).thenReturn(questions);

        assertEquals(sessionServiceImpl.createNewSession(newSession, participants), true);
    }
//
//    @Test
//    public void shouldNotSaveSessionWhenEndDateIsInThePast() {
//        List<QuestionDto> questions = new ArrayList<>();
//        QuestionDto question1 = new QuestionDto();
//        question1.setQuestionText("How do you like him?");
//        question1.setQuestionType(Question.QuestionType.TEXT);
//        question1.setDefaultAnswers(null);
//        questions.add(question1);
//
//        List<ParticipantDto> participants = new ArrayList<>();
//        ParticipantDto asia = new ParticipantDto();
//        asia.setEmail("asia@zdz");
//        ParticipantDto kasia = new ParticipantDto();
//        kasia.setEmail("kasia@yzdz");
//        participants.add(kasia);
//
//        SessionDto newSession = new SessionDto();
//        newSession.setName("Avenga First Edition");
//        newSession.setSent(false);
//        newSession.setEndDate(LocalDateTime.of(2019, 02, 02, 20, 00));
//        newSession.setParticipants(participants);
//        newSession.setQuestions(questions);
//
//        assertEquals(sessionServiceImpl.createNewSession(newSession), false);
//
//    }
//
//
//    @Test
//    public void shouldNotSaveSessionWhenListOfQuestionsIsEmpty() {
//        List<QuestionDto> questions = new ArrayList<>();
//        QuestionDto question1 = new QuestionDto();
//        question1.setQuestionText("How do you like him?");
//        question1.setQuestionType(Question.QuestionType.TEXT);
//        question1.setDefaultAnswers(null);
//        questions.add(question1);
//
//        SessionDto newSession = new SessionDto();
//        newSession.setName("Avenga First Edition");
//        newSession.setSent(false);
//        newSession.setEndDate(LocalDateTime.of(2020, 02, 02, 20, 00));
//        newSession.setParticipants(null);
//        newSession.setQuestions(questions);
//
//        assertEquals(sessionServiceImpl.createNewSession(newSession), false);
//    }
//
//    @Test
//    public void shouldNotSaveSessionWhenListOfParticipantsIsEmpty() {
//        List<ParticipantDto> participants = new ArrayList<>();
//        ParticipantDto asia = new ParticipantDto();
//        asia.setEmail("asia@zdz");
//        ParticipantDto kasia = new ParticipantDto();
//        kasia.setEmail("kasia@yzdz");
//        participants.add(kasia);
//
//        SessionDto newSession = new SessionDto();
//        newSession.setName("Avenga First Edition");
//        newSession.setSent(false);
//        newSession.setEndDate(LocalDateTime.of(2020, 02, 02, 20, 00));
//        newSession.setParticipants(participants);
//        newSession.setQuestions(null);
//
//        assertEquals(sessionServiceImpl.createNewSession(newSession), false);
//    }


//    @Test
//    public void shouldFindSessionsEndedInThePastButNotSent() {
//        //given
//        List<Session> allSessions = new ArrayList<>();
//        allSessions.add(new Session(1L, "AvengaFirstEdition", LocalDateTime.of(2019, 10, 10, 12, 00), false, null, null));
//        allSessions.add(new Session(2L, "Second edition", LocalDateTime.of(2019, 10, 12, 20, 00), true, null, null));
//        allSessions.add(new Session(3L, "Third edition", LocalDateTime.of(2020, 02, 05, 20, 00), false, null, null));
//        allSessions.add(new Session(4L, "Fourth edition", LocalDateTime.of(2020, 02, 02, 20, 00), false, null, null));
//
//        when(sessionDao.getAllSessionsToSend()).thenReturn(allSessions.stream()
//                .filter(p -> !p.isSent() && p.getEndDate().isBefore(LocalDateTime.now()))
//                .collect(Collectors.toList()));
//        //then
//        assertEquals(1, sessionServiceImpl.findSessionsToSend().size());
//
//    }


}
