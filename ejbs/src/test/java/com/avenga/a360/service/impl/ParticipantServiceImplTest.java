package com.avenga.a360.service.impl;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Session;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParticipantServiceImplTest {

    @InjectMocks
    ParticipantServiceImpl participantService;

    @Mock
    ParticipantDao participantDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAllParticipantBySessionId() {
        List<Participant> participants = new ArrayList<>();
        Participant participant = new Participant();
        participant.setEmail("a@a.com");
        participants.add(participant);

        Session session = new Session();
        session.setId(1L);
        session.setParticipants(participants);
        session.setSessionName("Session name");
        session.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));
        session.setIsSent(false);

        Mockito.when(participantDao.findBySessionId(session.getId())).thenReturn(participants);

        Assert.assertEquals(participantService.findBySessionId(session.getId()).get(0).getEmail(), participant.getEmail());
    }

    @Test
    public void shouldFindParticipantById() {
        List<Participant> participants = new ArrayList<>();
        Participant participant = new Participant();
        participant.setId(1L);
        participant.setEmail("a@a.com");
        participants.add(participant);

        Session session = new Session();
        session.setId(1L);
        session.setParticipants(participants);
        session.setSessionName("Session name");
        session.setEndDate(LocalDateTime.of(2020, 02, 20, 00, 00, 00, 01));
        session.setIsSent(false);

        Mockito.when(participantDao.findById(participant.getId())).thenReturn(participant);

        Assert.assertEquals(participantService.findById(participant.getId()).getEmail(), participant.getEmail());
    }

}
