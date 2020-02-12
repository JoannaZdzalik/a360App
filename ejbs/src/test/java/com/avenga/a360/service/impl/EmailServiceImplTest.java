package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.model.*;
import org.junit.Before;
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

public class EmailServiceImplTest {

    @InjectMocks
    EmailServiceImpl emailService;

    @Mock
    AnswerDao answerDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void init() throws Exception {
        emailService = new EmailServiceImpl();
    }

    @Test
    public void shouldReturnEmailObjectWithMailListForParticipants() {
        List<Participant> participantList = new ArrayList<>();

        Participant participant1 = new Participant();
        participant1.setEmail("maziak01@gmail.com");
        participantList.add(participant1);

        Participant participant2 = new Participant();
        participant2.setEmail("lukasz.dela@avenga.com");
        participantList.add(participant2);

        Participant participant3 = new Participant();
        participant3.setEmail("lukasz.stepien@avenga.com");
        participantList.add(participant3);

        Session session = new Session();
        session.setSessionName("Test session.");
        session.setParticipants(participantList);

        assertEquals(emailService.createEmailsToParticipantsWithLinks(session.getParticipants(), session.getSessionName()).size(), participantList.size());
    }


    @Test
    public void shouldReturnEmailListObjectToParticipantsWithFeedback() {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionText("'What do you value him for?");
        question.setQuestionType(Question.QuestionType.TEXT);
        question.setDefaultAnswers(null);
        questions.add(question);

        Question question2 = new Question();
        question2.setQuestionText("'Do you like him??");
        question2.setQuestionType(Question.QuestionType.TEXT);
        question2.setDefaultAnswers(null);
        questions.add(question2);

        Session session = new Session();
        session.setSessionName("Session name");
        session.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));
        session.setIsSent(false);
        session.setQuestions(questions);

        List<Participant> participantList = new ArrayList<>();

        Participant participant1 = new Participant();
        participant1.setId(1L);
        participant1.setEmail("maziak01@gmail.com");
        participantList.add(participant1);

        Participant participant2 = new Participant();
        participant2.setId(2L);
        participant2.setEmail("lukasz.dela@avenga.com");
        participantList.add(participant2);

        Participant participant3 = new Participant();
        participant3.setId(3L);
        participant3.setEmail("lukasz.stepien@avenga.com");
        participantList.add(participant3);

        session.setParticipants(participantList);

        List<Answer> answerListParticipant1 = new ArrayList<>(List.of(
                new Answer(1L, "answer maziak1", question, participant1),
                new Answer(2L, "answer maziak2", question2, participant1)
        ));

        List<Answer> answerListParticipant2 = new ArrayList<>(List.of(
                new Answer(3L, "answer lukasz.dela1", question, participant2),
                new Answer(4L, "answer lukasz.dela2", question2, participant2)
        ));

        List<Answer> answerListParticipant3 = new ArrayList<>(List.of(
                new Answer(5L, "answer lukasz.stepien1", question, participant3),
                new Answer(6L, "answer lukasz.stepien2", question2, participant3)
        ));

        Mockito.when(answerDao.findAllAnswersByParticipantId(1L)).thenReturn(answerListParticipant1);
        Mockito.when(answerDao.findAllAnswersByParticipantId(2L)).thenReturn(answerListParticipant2);
        Mockito.when(answerDao.findAllAnswersByParticipantId(3L)).thenReturn(answerListParticipant3);

        assertEquals(emailService.createEmailsToParticipantsWithFeedback(session).size(), participantList.size());
    }

    @Test
    public void shouldCreateEmailWithCorrectNumberOfLinksForSingleParticipant(){
        List<Participant> participantList = new ArrayList<>();

        Participant participant1 = new Participant();
        participant1.setEmail("maziak01@gmail.com");
        participantList.add(participant1);

        Participant participant2 = new Participant();
        participant2.setEmail("lukasz.dela@avenga.com");
        participantList.add(participant2);

        Participant participant3 = new Participant();
        participant3.setEmail("lukasz.stepien@avenga.com");
        participantList.add(participant3);

        Session session = new Session();
        session.setSessionName("Test session.");
        session.setParticipants(participantList);

        assertEquals(emailService.createEmailsToParticipantsWithLinks(session.getParticipants(), session.getSessionName()).get(0).getLinkList().size(), 2);
    }

    @Test
    public void shouldCreateEmailWithCorrectOfLinksForSingleParticipant(){
        List<Participant> participantList = new ArrayList<>();

        Participant participant1 = new Participant();
        participant1.setEmail("maziak01@gmail.com");
        participantList.add(participant1);

        Participant participant2 = new Participant();
        participant2.setEmail("lukasz.dela@avenga.com");
        participantList.add(participant2);

        Participant participant3 = new Participant();
        participant3.setEmail("lukasz.stepien@avenga.com");
        participantList.add(participant3);

        Session session = new Session();
        session.setSessionName("Test session.");
        session.setParticipants(participantList);

        Email emailForSingleParticipant = emailService.createEmailsToParticipantsWithLinks(session.getParticipants(), session.getSessionName()).get(0);

        assertEquals(emailForSingleParticipant.getBody().contains("maziak01@gmail.com"), false);
        assertEquals(emailForSingleParticipant.getBody().contains("lukasz.dela@avenga.com"), true);
        assertEquals(emailForSingleParticipant.getBody().contains("lukasz.stepien@avenga.com"), true);
    }

}
