package com.avenga.a360;

import com.avenga.a360.model.Session;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SessionTest extends JpaTest {
    @Test
    public void shouldFindParticipantListWithAllAnswersFromSessionToSent() {

        //act
        Query query = em.createNamedQuery("getSessionToSent");
        List<Session> result = query.getResultList();

        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getParticipants().size() == 4);
    }
}
