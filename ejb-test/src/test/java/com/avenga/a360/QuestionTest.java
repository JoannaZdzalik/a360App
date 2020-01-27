package com.avenga.a360;

import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Question;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionTest extends JpaTest {

    @Test
    public void shouldFindActiveQuestionList() {

        //act
        Query query = em.createNamedQuery("getActiveQuestionList");
        List<Question> result = query.getResultList();

        //assert
        assertTrue(result.size() == 4);
//        assertTrue(result.get(0).getClass().equals(Question.class));
    }

    @Test
    public void shouldFindQuestionListByParticipantId() {

        //arrange
        Participant participant = new Participant();
        participant.setId(1L);

        //act
        Query query = em.createNamedQuery("getAllQuestionByParticipant");
        List<Question> result = query.setParameter("idParticipant", participant).getResultList();

        //assert
        assertTrue(result.size() == 4);

    }


}
