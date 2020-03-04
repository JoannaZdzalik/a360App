package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.model.Participant;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ParticipantDaoImpl implements ParticipantDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    @Override
    public List<Participant> findBySessionId(Long id) {
        return em.createNamedQuery("Participant.findAllBySessionId", Participant.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Participant findById(Long id) {
        Participant participant = null;
        try{
            participant = em.createNamedQuery("Participant.findById", Participant.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return participant;
    }

    @Override
    public Participant findByUId(String uId) {
        Participant participant = null;
        try {
            participant = em.createNamedQuery("Participant.findByUId", Participant.class)
                    .setParameter("uid", uId)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return participant;
    }

    @Override
    public boolean createParticipant(Participant participant) {
        try {
            em.persist(participant);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
