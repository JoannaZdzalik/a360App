package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.model.Answer;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    @Override
    public List<Answer> findAllAnswersByParticipantId(Long id) {
        List<Answer> answers = em.createNamedQuery("Answer.shouldFindAllAnswersByParticipantId", Answer.class)
                .setParameter("id", id)
                .getResultList();
        return answers;
    }
    @Override
    public List<Answer> findAllAnswersForOneSession(String name) {
        List<Answer> answers = em.createNamedQuery("Answer.shouldFindAllAnswersForOneSession", Answer.class)
                .setParameter("name",name)
                .getResultList();
        return answers;
    }
    @Override
    public boolean createAnswer(Answer answer){
        try {
            em.persist(answer);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
