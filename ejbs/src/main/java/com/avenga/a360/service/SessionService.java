package com.avenga.a360.service;

import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Question;

import java.util.List;

public interface SessionService {

    List<SessionDto> findSessionsEndedInThePastButNotSent();

    boolean createSession(SessionDto sessionDto, List<ParticipantDto> participantsDto);
}
