package com.avenga.a360.model;

import com.avenga.a360.domain.model.Session;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SessionTest extends JpaTest {
    @Test
    public void shouldFindSessionsToSend() {

        //act
        Query query = em.createNamedQuery("findSessionsToSent");
        List<Session> result = query.getResultList();

        assertTrue(result.size() == 1);
    }
}
