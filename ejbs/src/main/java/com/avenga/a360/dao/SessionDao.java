package com.avenga.a360.dao;


import com.avenga.a360.domain.model.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SessionDao implements Dao<Session> {

    List<Session> sessions = new ArrayList<>();

    @Override
    public Optional<Session> get(long id) {
        return Optional.ofNullable(sessions.get((int) id));
    }

    @Override
    public List<Session> getAll() {
        return sessions;
    }

    @Override
    public void save(Session session) {
        sessions.add(session);
    }

    @Override
    public void delete(Session session) {
        sessions.remove(session);
    }

    public List<Session> getAllSessionsToSend() {
        return sessions;
        //tutaj będzie pobranie z bazy naszym query
    }
}
