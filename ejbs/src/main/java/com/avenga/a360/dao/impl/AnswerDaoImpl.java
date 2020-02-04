package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.domain.model.Answer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    List<Answer> answers = new ArrayList<>();

    @Override
    public void save(Answer answer) {
        //tu będzie em.persist, a nawet nic, jesli GenericDao zmienię na klasę abstrakcyjną
        answers.add(answer);
    }

    public List<Answer> getAllAnswersByParticipantId(Long id) {
//                List<Answer> answers = em.createNamedQuery("getAllAnswersByParticipantId", Answer.class)
//                .getResultList();
        return answers;
    }

    @Override
    public List<Answer> getAllAnswersByParticipantIdAndQuestionId(Long idParticipant, Long idQuestion) {
        //         List<Answer> answers = em.createNamedQuery("getAnswersByParticipantIdAndQuestionId", Answer.class)
//                .getResultList();
        return answers;
    }
}
