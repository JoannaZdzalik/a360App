package com.avenga.a360;

import com.avenga.a360.model.Answer;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SessionTest extends JpaTest {

    @Test
    public void testGetAllAnswersFromEndSessionParticipant() {

        Query query = em.createNamedQuery("getAllAnswersFromSessionToSent");
        List<Answer> result = query.getResultList();

        assertTrue(result.size() == 8);

    }
}
