package com.avenga.a360.service;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.domain.dto.QuestionDto;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.service.impl.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class QuestionServiceImplTest {

    @InjectMocks
    QuestionServiceImpl questionServiceImpl;

    @Mock
    public QuestionDao questionDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindListOfActiveQuestions() {
        //given
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(1L, "How do you like him?", Question.QuestionType.TEXT, null, true, null, null));
        questions.add(new Question(2L, "How are u?", Question.QuestionType.TEXT, null, true, null, null));
        questions.add(new Question(3L, "Your comment please!", Question.QuestionType.TEXT, null, true, null, null));
        questions.add(new Question(4L, "Overall experience", Question.QuestionType.RADIO, null, true, null, null));

        //when
        when(questionDao.getAllActiveQuestions()).thenReturn(questions);

        //then
        assertEquals(4, questionServiceImpl.findAllActiveQuestions().size());

    }

}
