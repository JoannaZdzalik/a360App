package com.avenga.a360.dao;

import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.model.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParticipantDao implements Dao<Participant> {

    List<Participant> participants = new ArrayList<>();

    @Override
    public Optional<Participant> get(long id) {
        return Optional.ofNullable(participants.get((int) id));
    }

    @Override
    public List<Participant> getAll() {
        return participants;
    }

    @Override
    public void save(Participant participant) {
        participants.add(participant);
    }

    @Override
    public void delete(Participant participant) {
        participants.remove(participant);
    }
}
