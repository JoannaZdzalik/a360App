package com.avenga.a360;


import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Session;

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
//        assertTrue(result.get(0).getClass().equals(Participant.class));
    }

    @Test
    public void shouldFindParticipantListWithAllAnswersFromSessionToSent() {

        //act
        Query query = em.createNamedQuery("getAllParticipantsAnswersFromSessionToSent");
        List<Participant> result = query.getResultList();

        assertTrue(result.size() == 4);
        assertTrue(result.get(0).getAnswers().size() == 5);

        //assert
//        assertAll("shouldFindParticipantListWithAllAnswersFromSessionToSent",
//                () ->  assertTrue(result.size() == 4),
//                () ->  assertTrue(result.get(0).getAnswers().size() == 5)
//        );
    }

}
