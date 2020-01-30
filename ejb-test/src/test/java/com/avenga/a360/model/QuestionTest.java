package com.avenga.a360.model;

import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionTest extends JpaTest {

    @Test
    public void shouldFindActiveQuestionList() {

        //when
        Query query = em.createNamedQuery("getActiveQuestionList");
        List<Question> result = query.getResultList();

        //then
        assertTrue(result.size() == 4);
    }

    @Test
    public void shouldFindQuestionListByParticipantId() {

        //given
        Participant participant = new Participant();
        participant.setId(1L);

        //when
        Query query = em.createNamedQuery("findAllQuestionsByParticipantId");
        List<Question> result = query.setParameter("idParticipant", participant).getResultList();

        //then
        assertTrue(result.size() == 4);

    }

    @Test
    public void shouldFindQuestionById(){
        Query query = em.createNamedQuery("findQuestionById");
        Question result = (Question) query.setParameter("id", 1L).getSingleResult();

        assertEquals("What do you value him/her for", result.getQuestionText());
    }


}
