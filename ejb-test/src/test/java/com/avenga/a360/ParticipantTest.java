package com.avenga.a360;

import com.avenga.a360.model.Answer;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Question;
import com.avenga.a360.model.Session;
import org.junit.Test;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class ParticipantTest extends JpaTest {

    @Test
    public void testGetAllParticipantsFromSession() {

        Session session = new Session();
        session.setId(1L);
        Query query = em.createNamedQuery("getAllParticipantsListBySessionId");
        List<Participant> result = query.setParameter("idSession", session).getResultList();

        assertTrue(result.size() == 4);
    }

    @Test
    public void testGetAllAnswersFromEndSessionParticipant() {

        Query query = em.createNamedQuery("getAllAnswersFromSessionToSent");
        List<Participant> result = query.getResultList();

        assertTrue(result.size() == 4);
        assertTrue(result.get(0).getAnswers().size() == 5);
    }

}
