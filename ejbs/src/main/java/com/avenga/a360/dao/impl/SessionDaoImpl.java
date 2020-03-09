package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.model.Participant;
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

    @Override
    public boolean checkIfSessionNameExistsInDB(String sessionName) {
        Session session = null;
        try {
            session = em.createNamedQuery("findSessionByName", Session.class).setParameter("session_name", sessionName).getSingleResult();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Session findSessionByName(String name) {
        Session session = null;
        try {
            session = em.createNamedQuery("findSessionByName", Session.class)
                    .setParameter("session_name", name)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }


    @Override
    public List<Session> findAllSessions() {
        return em.createNamedQuery("findAllSessions", Session.class)
                .getResultList();
    }

    @Override
    public Session findSessionByParticipantUid(String uId){
        Session session = null;
        try {
            session = em.createNamedQuery("findSessionByParticipantUid", Session.class)
                    .setParameter("uid", uId)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }

    @Override
    public boolean removeSession(Long id) {
        Session session = em.find(Session.class, id);
        try {
            em.remove(session);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateSession(Session session) {
        try {
            em.merge(session);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
