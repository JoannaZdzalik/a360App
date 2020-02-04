package com.avenga.a360.dao;

import com.avenga.a360.domain.model.Participant;

import java.util.List;

public  interface ParticipantDao {

    void save(Participant participant);
    List<Participant> getAllParticipantsBySessionId(Long id);
    Participant findById(Long id);
    Participant findByUid(String uid);
}
