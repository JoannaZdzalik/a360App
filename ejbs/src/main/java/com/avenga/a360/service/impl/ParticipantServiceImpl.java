package com.avenga.a360.service.impl;

import com.avenga.a360.dao.impl.ParticipantDaoImpl;
import com.avenga.a360.dao.impl.SessionDaoImpl;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.service.ParticipantService;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantDaoImpl participantDao;
    private SessionDaoImpl sessionDao;

    public ParticipantServiceImpl(ParticipantDaoImpl participantDao) {
        this.participantDao = participantDao;
    }

    @Override
    public boolean createParticipant(Participant participant) {
        if (participant == null) {
            return false;
        }
        participantDao.save(participant);
        return true;
    }

    @Override
    public List<ParticipantDto> findAllParticipantsBySessionId(Long id) {
        List<Participant> participants = participantDao.getAllParticipantsBySessionId(id);
        return participants.stream()
                .map(u -> mapParticipantToParticipantDto(u))
                .collect(Collectors.toList());
    }

    public ParticipantDto mapParticipantToParticipantDto(Participant participant) {
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setId(participant.getId());
        participantDto.setUid(participant.getUid());
        participantDto.setEmail(participant.getEmail());
        return participantDto;
    }
}
