package com.avenga.a360.model;


import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;

import org.junit.Test;

import javax.persistence.Query;
import java.util.List;
import static org.junit.Assert.*;

public class ParticipantTest extends JpaTest {

    @Test
    public void shouldFindParticipantListBySessionId() {

        //arrange
        Session session = new Session();
        session.setId(1L);

        //act
        Query query = em.createNamedQuery("getAllParticipantsListBySessionId");
        List<Participant> result = query.setParameter("idSession", session).getResultList();

        //assert
        assertTrue(result.size() == 4);
    }

    @Test
    public void shouldFindParticipantById(){
        Query query = em.createNamedQuery("findParticipantById");
        Participant result = (Participant) query.setParameter("id", 1L).getSingleResult();

        assertEquals("anna@avenga.com", result.getEmail());
    }
}
