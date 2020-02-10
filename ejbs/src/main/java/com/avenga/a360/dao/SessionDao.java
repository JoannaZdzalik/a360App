package com.avenga.a360.dao;

import com.avenga.a360.domain.model.Session;

import java.util.List;

public interface SessionDao {

    void save(Session session);
    List<Session> getAllSessionsToSend();
}
