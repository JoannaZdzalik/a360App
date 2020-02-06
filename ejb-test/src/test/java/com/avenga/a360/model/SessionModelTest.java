package com.avenga.a360.model;

import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SessionModelTest extends JpaTest {
    @Test
    public void shouldFindSessionsToSend() {

        //act
        Query query = em.createNamedQuery("findSessionsToSend");
        List<Session> result = query.getResultList();

        assertTrue(result.size() == 1);
    }

    @Test
    public void shouldFindSessionById(){
        Query query = em.createNamedQuery("findSessionById");
        Session result = (Session) query.setParameter("id", 1L).getSingleResult();

        assertEquals("Avenga Spring edition", result.getName());
    }
}
