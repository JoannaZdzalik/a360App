package com.avenga.a360.service.impl;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.model.Participant;
import com.avenga.a360.service.ParticipantService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ParticipantServiceImpl implements ParticipantService {

    @Inject
    ParticipantDao participantDao;

  /*  @Inject
    ParticipantDto participantDto;*/

    @Override
    public List<Participant> findBySessionId(Long id) {
        return participantDao.findBySessionId(id);
    }

    @Override
    public Participant findById(Long id) {
        return participantDao.findById(id);
    }

    @Override
    public boolean createParticipant(Participant participant) {
        if (participant != null) {
            participantDao.createParticipant(participant);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Participant findByUId(String uId) {
        return participantDao.findByUId(uId);
    }
    @Override
    public ParticipantDto ParticipantToParticipantDto(Participant participant){
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setEmail(participant.getEmail());
        participantDto.setUId(participant.getUId());
        participantDto.setSessionId(participant.getSession().getId());
        return participantDto;
    }

}
