package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.GenericDao;
import com.avenga.a360.domain.model.Question;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


public class QuestionDao implements GenericDao<Question> {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    List<Question> questions = new ArrayList<>();
    Question question = new Question();

    @Override
    public void save(Question question) {
        questions.add(question);
    }

    public List<Question> getAllActiveQuestions() {
//        List<Question> questions = em.createNamedQuery("getActiveQuestionList", Question.class)
//                .getResultList();
        return questions;
    }

    public List<Question> getAllQuestionsByParticipantId(Long id) {
//        List<Question> questions = em.createNamedQuery("findAllQuestionsByParticipantId", Question.class)
//                .setParameter("idParticipant", id)
//                .getResultList();
        return questions;
    }

    public Question findById(Long id){
//                Question question = em.createNamedQuery("findQuestionById", Question.class)
//                .setParameter("id", id)
//                .getSingleResult();
        return question;
    }
}
