package com.avenga.a360.service;

import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.model.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> findBySessionId(Long id);

    Participant findById(Long id);

    boolean createParticipant(Participant participant);

    Participant findByUId(String uId);

    ParticipantDto participantToParticipantDto(Participant participant);
}
