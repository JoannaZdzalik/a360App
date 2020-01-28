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
        List<QuestionDto> questions = new ArrayList<>();

        questions.add(new QuestionDto(1L, "How do you like him?", Question.QuestionType.TEXT, null, true, null, null));
        questions.add(new QuestionDto(2L, "How are u?", Question.QuestionType.TEXT, null, true, null, null));
        questions.add(new QuestionDto(3L, "Anything to change?", Question.QuestionType.TEXT, null, false, null, null));
        questions.add(new QuestionDto(4L, "Your comment please!", Question.QuestionType.TEXT, null, true, null, null));

        //when
        when(questionDao.getAll()).thenReturn(questions);

        //then
        assertTrue(questionServiceImpl.findAllActiveQuestions().size() == 3);
    }


//    @Test
//    public void shouldFindListOfActiveQuestions() {
//        //given
//
//        QuestionServiceImpl qs = new QuestionServiceImpl();
//
//       //then
//        assertTrue(qs.findAllActiveQuestions().size() == 3);
//    }
}
