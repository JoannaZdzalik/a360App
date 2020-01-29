package com.avenga.a360.service.impl;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.service.ParticipantService;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantServiceImpl implements ParticipantService {

    private ParticipantDao participantDao;

    public ParticipantServiceImpl(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

//    @Override
//    public List<ParticipantDto> findAllParticipantsBySessionId(Long sessionId) {
//        return participantDao.getAll().stream()
//                .filter(p-> p.getSessionId().equals(sessionId))
//                .collect(Collectors.toList());
//    }
}
