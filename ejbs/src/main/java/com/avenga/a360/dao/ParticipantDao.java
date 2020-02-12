package com.avenga.a360.dao;

import com.avenga.a360.model.Participant;

import java.util.List;

public interface ParticipantDao {

    List<Participant> findBySessionId(Long id);

    Participant findById(Long id);

    Participant findByUId(String uId);

    boolean createParticipant(Participant participant);
}
