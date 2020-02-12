import com.avenga.a360.model.Participant;
import entity.JpaTest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ParticipantTest extends JpaTest {
    @Test
    public void shouldFindAllBySessionId() {
        //given

        //when
        List<Participant> questionsDb = em.createNamedQuery("Participant.findAllBySessionId", Participant.class)
                .setParameter("id", 1L)
                .getResultList();

        //then
        assertTrue(questionsDb.get(0).getSession().getSessionName().equals("sesja1"));
    }

    @Test
    public void shouldFindById() {
        //given


        //when
        Participant participantDb = em.createNamedQuery("Participant.findById", Participant.class)
                .setParameter("id", 1L)
                .getSingleResult();

        //then
        assertTrue(participantDb.getEmail().equals("asia.zdz@gmail.com"));
    }
}
