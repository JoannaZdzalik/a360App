package com.avenga.a360.service;

import com.avenga.a360.dao.impl.ParticipantDaoImpl;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.impl.ParticipantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ParticipantServiceImplTest {

    @InjectMocks
    ParticipantServiceImpl participantService;

    @Mock
    private ParticipantDaoImpl participantDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

/*    @Test
    public void shouldMapParticipantToParticipantDto() {
        Session session = new Session();
        session.setId(1L);
        session.setSessionName("Session First Edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));

        Participant participant = new Participant(1L, "123456789012345", "asai@gfd", session, null);

        ParticipantDto participantDto = participantService.mapParticipantToParticipantDto(participant);

        assertAll(
                () -> assertEquals(participant.getId(), participantDto.getId()),
                () -> assertEquals(participant.getUid(), participantDto.getUid()),
                () -> assertEquals(participant.getEmail(), participantDto.getEmail())
        );
    }*/

    @Test
    public void shouldFindAllParticipantsBySessionId() {
        List<Participant> participants = new ArrayList<>();

        Participant asia = new Participant();
        asia.setId(1L);
        asia.setUid("123456789012345");
        asia.setEmail("asia@aaaa");
        participants.add(asia);

        Participant nikodem = new Participant();
        nikodem.setId(2L);
        nikodem.setUid("789456123078945");
        nikodem.setEmail("ignacy@paaa");
        participants.add(nikodem);

        Session session = new Session();
        session.setId(1L);
        session.setParticipants(participants);
        session.setSessionName("Avenga first edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));
        session.setSent(false);

        Mockito.when(participantDao.getAllParticipantsBySessionId(session.getId())).thenReturn(participants);

        assertAll(
                () -> assertEquals(participantService.findAllParticipantsBySessionId(1L).get(0).getEmail(), participants.get(0).getEmail()),
                () -> assertEquals(participantService.findAllParticipantsBySessionId(1L).get(1).getEmail(), participants.get(1).getEmail())
        );
    }
}
