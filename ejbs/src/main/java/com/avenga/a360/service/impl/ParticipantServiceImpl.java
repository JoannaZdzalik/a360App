package com.avenga.a360.service.impl;

import com.avenga.a360.dao.impl.ParticipantDao;
import com.avenga.a360.service.ParticipantService;

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
