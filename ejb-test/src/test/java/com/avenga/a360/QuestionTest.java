package com.avenga.a360;

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


}
