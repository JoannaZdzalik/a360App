package com.avenga.a360.service;

import com.avenga.a360.dao.impl.AnswerDaoImpl;
import com.avenga.a360.dao.impl.QuestionDaoImpl;
import com.avenga.a360.domain.model.*;
import com.avenga.a360.service.impl.SendFeedbackServiceImpl;
import com.avenga.a360.service.impl.SendServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SendFeedbackServiceImplTest {

    @Mock
    SendServiceImpl sendService;

    @Mock
    private AnswerDaoImpl answerDao;

    @Mock
    private QuestionDaoImpl questionDao;

    @InjectMocks
    SendFeedbackServiceImpl sendFeedbackService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void init() throws Exception {
        sendService = new SendServiceImpl();
    }

    @Test
    public void shouldVerifyHowManyEmailsWithFeedbackHasBeenSent() {
        List<Participant> participants = new ArrayList<>();

        Participant kasia = new Participant();
        kasia.setEmail("kasia@yzdz");
        participants.add(kasia);

        Participant asia = new Participant();
        asia.setEmail("asia@zdz");
        participants.add(asia);

        Participant jagna = new Participant();
        jagna.setEmail("jagienka@dvdv");
        participants.add(jagna);

        Session session = new Session();
        session.setSessionName("Avenga First Edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));
        session.setParticipants(participants);

        sendFeedbackService.sendFeedback(session);

        verify(sendService, times(session.getParticipants().size())).sendEmail(any(Email.class));
    }

    @Test
    public void shouldCreateSessionSubjectUsingGivenSessionName() {
        Session session = new Session();
        session.setSessionName("Avenga First Edition");

        SendFeedbackServiceImpl s = new SendFeedbackServiceImpl();

        assertEquals("Avenga First Edition - check your feedback", s.createEmailSubject(session));
    }

    @Test
    public void shouldFindAllQuestionsByParticipantId() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1L, "What do you value her for?", Question.QuestionType.TEXT, null, true, null, null));
        questions.add(new Question(2L, "What you could change?", Question.QuestionType.TEXT, null, true, null, null));

        List<Participant> participants = new ArrayList<>();
        Participant asia = new Participant();
        asia.setId(1L);
        participants.add(asia);

        when(questionDao.getAllQuestionsByParticipantId(asia.getId())).thenReturn(questions);

        assertAll(
                () -> assertEquals(sendFeedbackService.findAllQuestionsByParticipantId(1L).get(0).getQuestionText(), questions.get(0).getQuestionText()),
                () -> assertEquals(sendFeedbackService.findAllQuestionsByParticipantId(1L).get(1).getQuestionText(), questions.get(1).getQuestionText())

        );
    }

    @Test
    public void shouldFindAllAnswersByParticipantIdAndQuestionId() {
        List<Question> questions = new ArrayList<>();
        Question question1 = new Question();
        question1.setId(1L);
        question1.setQuestionText("How do you like her?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        questions.add(question1);

        Question question2 = new Question();
        question2.setId(2L);
        question2.setQuestionText("What is wrong?");
        question2.setQuestionType(Question.QuestionType.TEXT);
        questions.add(question2);

        List<Participant> participants = new ArrayList<>();
        Participant asia = new Participant();
        asia.setId(1L);
        asia.setEmail("asia@zdz");
        asia.setUid("asdfghjklzxcvbn");
        participants.add(asia);

        Participant jagna = new Participant();
        jagna.setId(2L);
        jagna.setEmail("jagienka@dvdv");
        jagna.setUid("999rrr222hhhAAA");
        participants.add(jagna);

        Session session = new Session();
        session.setSessionName("Avenga First Edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));
        session.setParticipants(participants);

        List<Answer> answerListAsia1stQuestion = new ArrayList<>(List.of(
                new Answer(1L, question1, asia, "asia first answer")
                ));


        List<Answer> answerListJagna2ndQuestion = new ArrayList<>(List.of(
                new Answer(4L, question2, jagna, "jagna 2nd answer"),
                new Answer(5L, question2, jagna, "jagna another answer for second question")
        ));


        Mockito.when(answerDao.getAllAnswersByParticipantIdAndQuestionId(1L, 1L)).thenReturn(answerListAsia1stQuestion);
        Mockito.when(answerDao.getAllAnswersByParticipantIdAndQuestionId(2L, 2L)).thenReturn(answerListJagna2ndQuestion);

        List<Answer> answersAsia1stQuestion = sendFeedbackService.findAllAnswersByParticipantIdAndQuestionId(asia.getId(), question1.getId());
        List<Answer> answersJagna2ndQuestion = sendFeedbackService.findAllAnswersByParticipantIdAndQuestionId(jagna.getId(), question2.getId());

        assertAll(
                () -> assertEquals(1, answersAsia1stQuestion.size()),
                () -> assertEquals("asia first answer", answersAsia1stQuestion.get(0).getAnswerText()),

                () -> assertEquals(2, answersJagna2ndQuestion.size()),
                () -> assertEquals("jagna 2nd answer", answersJagna2ndQuestion.get(0).getAnswerText()),
                () -> assertEquals("jagna another answer for second question", answersJagna2ndQuestion.get(1).getAnswerText())
        );
    }
}
