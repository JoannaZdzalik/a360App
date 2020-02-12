package com.avenga.a360.dao;

import com.avenga.a360.model.Session;

import java.util.List;

public interface SessionDao {

    List<Session> findAllSessionsIsSentFalseAndEndDateIsAfterNow();

    boolean createSession(Session session);
}
