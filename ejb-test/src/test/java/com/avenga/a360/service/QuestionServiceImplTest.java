package com.avenga.a360.service;

import com.avenga.a360.dao.impl.QuestionDaoImpl;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

public class QuestionServiceImplTest {

    @InjectMocks
    QuestionServiceImpl questionService;

    @Mock
    public QuestionDaoImpl questionDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAllActiveQuestions() {
        List<Question> activeQuestions = new ArrayList<>();
        Question question1 = new Question();
        question1.setQuestionText("How do you like him?");
        question1.setQuestionType(Question.QuestionType.TEXT);
        question1.setDefaultAnswers(null);
        activeQuestions.add(question1);

        activeQuestions.add(new Question(2L, "What do you value most?", Question.QuestionType.TEXT, null, true, null, null));

        when(questionDao.getAllActiveQuestions()).thenReturn(activeQuestions);

        assertEquals(2, questionService.findAllActiveQuestions().size());
    }

    @Test
    public void shouldFindAllQuestionsByParticipantId() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1L, "What do you value her for?", Question.QuestionType.TEXT, null, true, null, null));
        questions.add(new Question(2L, "What you could change?", Question.QuestionType.TEXT, null, true, null, null));
        questions.add(new Question(3L, "What is she doing wrong?", Question.QuestionType.TEXT, null, true, null, null));
        questions.add(new Question(4L, "Overall exp", Question.QuestionType.RADIO, "POSITIVE;NEUTRAL;NEGATIVE", true, null, null));

        List<Participant> participants = new ArrayList<>();
        Participant asia = new Participant();
        asia.setId(1L);
        asia.setUid("123456789012345");
        asia.setEmail("asia@aaaa");
        participants.add(asia);

        Session session = new Session();
        session.setId(1L);
        session.setParticipants(participants);
        session.setSessionName("Avenga first edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));
        session.setSent(false);

        when(questionDao.getAllQuestionsByParticipantId(asia.getId())).thenReturn(questions);

        assertAll(
                () -> assertEquals(questionService.findQuestionsByParticipantId(1L).get(0).getQuestionText(), questions.get(0).getQuestionText()),
                () -> assertEquals(questionService.findQuestionsByParticipantId(1L).get(1).getQuestionText(), questions.get(1).getQuestionText()),
                () -> assertEquals(questionService.findQuestionsByParticipantId(1L).get(2).getQuestionText(), questions.get(2).getQuestionText()),
                () -> assertEquals(questionService.findQuestionsByParticipantId(1L).get(3).getQuestionText(), questions.get(3).getQuestionText())
        );
    }

    @Test
    public void shouldMapQuestionToQuestionDto(){

    }

}
