/*
package com.avenga.a360.model;

import com.avenga.a360.domain.model.Answer;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnswerModelTest extends JpaTest {

    @Test
    public void shouldFindAnswerListByParticipantId() {

        Participant participant = new Participant();
        participant.setId(1L);

        Query query = em.createNamedQuery("getAllAnswersByParticipantId");
        List<Answer> result = query.setParameter("idParticipant", participant.getId()).getResultList();

        assertTrue(result.size() == 5);

    }

    @Test
    public void shouldGetAnswersByParticipantIdAndQuestionId() {
        Participant participant = new Participant();
        participant.setId(1L);

        Question question = new Question();
        question.setId(1L);

        Query query = em.createNamedQuery("getAnswersByParticipantIdAndQuestionId");
        query.setParameter("idParticipant", participant.getId());
        query.setParameter("idQuestion", question.getId());

        List<Answer> result = query.getResultList();

        assertEquals(2, result.size());

    }
}
*/
