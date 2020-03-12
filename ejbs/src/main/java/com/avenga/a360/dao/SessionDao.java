package com.avenga.a360.dao;

import com.avenga.a360.model.Session;

import java.util.List;

public interface SessionDao {

    List<Session> findAllSessionsIsSentFalseAndEndDateIsAfterNow();

    List<Session> findAllSessionsWhereIsSentFalse();

    boolean createSession(Session session);

    boolean checkIfSessionNameExistsInDB(String name);

    Session findSessionByName(String name);

    Session findSessionById(Long id);

    List<Session> findAllSessions();

    Session findSessionByParticipantUid(String uId);

    boolean removeSession(Long id);

    boolean updateSession(Session session);
}
