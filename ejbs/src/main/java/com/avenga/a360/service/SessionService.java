package com.avenga.a360.service;

import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.response.Status;

import java.util.List;

public interface SessionService {

    Status createSession(SessionDto sessionDto, List<ParticipantDto> participantsDto);

    List<Session> findAllSessionsIsSentFalseAndEndDateIsAfterNow();
}
