package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.model.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SessionDaoImpl implements SessionDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    @Override
    public List<Session> findAllSessionsIsSentFalseAndEndDateIsAfterNow() {

        List<Session> sessions = em.createNamedQuery("Session.findAllSessionsIsSentFalseAndEndDateIsAfterNow", Session.class)
                .getResultList();
        return sessions;
    }
    @Override
    public List<Session> findAllSessionsWhereIsSentFalse() {

        List<Session> sessions = em.createNamedQuery("Session.findAllSessionsWhereIsSentFalse", Session.class)
                .getResultList();
        return sessions;
    }

    @Override
    public boolean createSession(Session session) {
        try {
            em.persist(session);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
