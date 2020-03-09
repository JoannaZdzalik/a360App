package com.avenga.a360.service;

import com.avenga.a360.dto.EditDto.SessionEditDto;
import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.response.Status;

import java.util.List;

public interface SessionService {

    Status createSession(SessionDto sessionDto, List<ParticipantDto> participantsDto);
    boolean removeSession(Long id);

    boolean updateSession(SessionEditDto sessionEditDto);

    List<Session> findAllSessionsIsSentFalseAndEndDateIsAfterNow();
    SessionDto findSessionByParticipantUid(String uid);
    List<SessionDto> findAllSessions();
    List<SessionDto> sessionListToSessionDtoList(List<Session> sessionList);
    List<SessionDto> findAllSessionsWhereIsSentFalse();
}
