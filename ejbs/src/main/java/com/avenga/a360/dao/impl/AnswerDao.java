package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.GenericDao;
import com.avenga.a360.domain.model.Answer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao implements GenericDao<Answer> {

//    @PersistenceContext(unitName = "a360")
//    private EntityManager em;

    List<Answer> answers = new ArrayList<>();

    @Override
    public void save(Answer answer) {
        //tu będzie em.persist, a nawet nic, jesli GenericDao zmienię na klasę abstrakcyjną
        answers.add(answer);
    }

    public List<Answer> getAllAnswersByParticipantId(Long id){
//                List<Answer> answers = em.createNamedQuery("findSessionsToSent", Answer.class)
//                .getResultList();
        return answers;
    }

}
