package com.avenga.a360.dao.impl;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.domain.model.Participant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDaoImpl implements ParticipantDao {

    @PersistenceContext(unitName = "a360")
    private EntityManager em;

    //to bedzie do usunięcia jak polacze sie z bazą
    List<Participant> participants = new ArrayList<>();
    Participant participant = new Participant();

    @Override
    public void save(Participant participant) {
        //generalnie tu bedzie em.persist
        participants.add(participant); //a to bedzie do wywalenia
    }

    public List<Participant> getAllParticipantsBySessionId(Long id) {
//        List<Participant> participants = em.createNamedQuery("getAllParticipantsListBySessionId", Participant.class)
//                .setParameter("idSession", id)
//                .getResultList();
        return participants;
    }

    public Participant findById(Long id) {
//        Participant participant = em.createNamedQuery("findParticipantById", Participant.class)
//                .setParameter("id", id)
//                .getSingleResult();
        return participant;
    }

    public Participant findByUid(String uid){
//        Participant participant = em.createNamedQuery("findParticipantByUid", Participant.class)
//                .setParameter("uid", uid)
//                .getSingleResult();
        return participant;
    }


}