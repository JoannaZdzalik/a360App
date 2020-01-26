package com.avenga.a360;

import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Question;
import org.junit.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionTest extends JpaTest {

    @Test
    public void testGetAllActiveQuestion() {

        Query query = em.createNamedQuery("getActiveQuestionList");
        List<Question> result = query.getResultList();

        assertTrue(result.size() == 4);

    }

    @Test
    public void testGetAllQuestionByParticipant() {


//        Session session = new Session();
//        session.setId(1L);
//        session.setName("Avenga Spring edition");
//        LocalDateTime end = LocalDateTime.of(2020, Month.JANUARY, 20, 07, 00, 00);
//        session.setEndDate(end);
        Participant participant = new Participant();
        participant.setId(1L);
//        Set<Participant> participantSet = new HashSet<Participant>();
//        participantSet.add(participant);

        Query query = em.createNamedQuery("getAllQuestionByParticipant");
        List<Question> result = query.setParameter("idParticipant", participant).getResultList();

        assertTrue(result.size() == 4);

    }


}
