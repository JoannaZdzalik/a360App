package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.domain.model.Question;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


public class QuestionDaoImpl implements QuestionDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;


    @Override
    public void save(Question question) {
        try {
            em.persist(question);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Question> getAllActiveQuestions() {
        List<Question> questions = em.createNamedQuery("getActiveQuestionList", Question.class)
                .getResultList();
        return questions;
    }

    public List<Question> getAllQuestionsByParticipantId(Long id) {
        List<Question> questions = em.createNamedQuery("findAllQuestionsByParticipantId", Question.class)
                .setParameter("idParticipant", id)
                .getResultList();
        return questions;
    }

    public Question findById(Long id) {
        Question question = null;
        try {
               question = em.createNamedQuery("findQuestionById", Question.class)
                .setParameter("id", id)
                .getSingleResult();
        } catch (Exception e) {
        }
        return question;
    }
}
