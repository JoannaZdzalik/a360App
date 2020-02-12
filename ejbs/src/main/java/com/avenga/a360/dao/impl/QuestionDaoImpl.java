package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.model.Question;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    @Override
    public List<Question> findAllActiveQuestions() {
        List<Question> questions = em.createNamedQuery("Question.findAllActiveQuestions", Question.class)
                .getResultList();
        return questions;
    }

    @Override
    public List<Question> findAllQuestionsTextAndIdByParticipantId(Long id) {
        List<Question> questions = em.createNamedQuery("Question.findAllQuestionsByParticipantId", Question.class)
                .setParameter("id", id)
                .getResultList();
        return questions;
    }

    @Override
    public boolean createQuestion(Question question) {
        try {
            em.persist(question);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Question findById(Long id) {
        Question question = null;
        try {
            question = em.createNamedQuery("Question.findById", Question.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
        }
        return question;
    }

    @Override
    public List<Question> getAllQuestionBySessionId(Long id) {
        List<Question> questions = em.createNamedQuery("Question.findAllQuestionsBySessionId", Question.class)
                .setParameter("id", id)
                .getResultList();
        return questions;
    }
}
