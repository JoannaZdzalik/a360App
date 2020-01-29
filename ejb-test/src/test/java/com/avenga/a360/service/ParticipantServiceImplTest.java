package com.avenga.a360.service;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.service.impl.ParticipantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ParticipantServiceImplTest {

    @InjectMocks
    ParticipantServiceImpl participantServiceImpl;

    @Mock
    private ParticipantDao participantDao;

//    @Test
//    public void shouldFindAllParticipantsBySessionId() {
//
//        //given
//
//
//
//        List<ParticipantDto> partticipants1 = new ArrayList<>();
//
//
//
//        List<ParticipantDto> allParticipants = new ArrayList<>();
//        allParticipants.add(new ParticipantDto(1L, "asia@zdz", 1L));
//        allParticipants.add(new ParticipantDto(2L, "lukasz@aven", 1L));
//        allParticipants.add(new ParticipantDto(3L, "ania@ii", 1L));
//        allParticipants.add(new ParticipantDto(4L, "jacek@fii", 2L));
//        allParticipants.add(new ParticipantDto(5L, "kuba@op.pl", 2L));
//
//        List<SessionDto> allSessions = new ArrayList<>();
//        allSessions.add(new SessionDto(1L, "AvengaFirstEdition", LocalDateTime.now(), false, null, null));
//        allSessions.add(new SessionDto(2L, "Second edition", LocalDateTime.of(2019,10,12,20, 00), true, null, null));
//
//        //when
//        when(participantDao.getAll()).thenReturn(allParticipants);
//
//        //then
//        assertTrue(participantServiceImpl.findAllParticipantsBySessionId(1L).size() == 3);
//
//    }
}
