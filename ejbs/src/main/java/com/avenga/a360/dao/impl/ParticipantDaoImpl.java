package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.domain.model.Participant;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


public class ParticipantDaoImpl implements ParticipantDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    @Override
    public void save(Participant participant) {
        try {
            em.persist(participant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Participant> getAllParticipantsBySessionId(Long id) {
        List<Participant> participants = em.createNamedQuery("getAllParticipantsListBySessionId", Participant.class)
                .setParameter("idSession", id)
                .getResultList();
        return participants;
    }

    public Participant findById(Long id) {
        Participant participant = null;
        try {
            participant = em.createNamedQuery("findParticipantById", Participant.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return participant;
    }

    public Participant findByUid(String uid) {
        Participant participant = null;
        try {
            participant = em.createNamedQuery("findParticipantByUid", Participant.class)
                    .setParameter("uid", uid)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return participant;
    }


}
