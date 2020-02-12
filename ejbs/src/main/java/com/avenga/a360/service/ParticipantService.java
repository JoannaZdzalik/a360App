package com.avenga.a360.service;

import com.avenga.a360.model.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> findBySessionId(Long id);

    Participant findById(Long id);

    boolean createParticipant(Participant participant);
}
