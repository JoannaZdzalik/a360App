package com.avenga.a360.dao.impl;


import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.domain.model.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


public class SessionDaoImpl implements SessionDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    List<Session> sessions = new ArrayList<>();
    Session session = new Session();

    @Override
    public void save(Session session) {
        sessions.add(session);
    }

    public List<Session> getAllSessionsToSend() {
//        List<Session> sessions = em.createNamedQuery("findSessionsToSent", Session.class)
//                .getResultList();
        return sessions;
    }

    public Session findById(Long id){
//                Session session = em.createNamedQuery("findSessionById", Session.class)
//                .setParameter("id", id)
//                .getSingleResult();
        return session;
    }

}
