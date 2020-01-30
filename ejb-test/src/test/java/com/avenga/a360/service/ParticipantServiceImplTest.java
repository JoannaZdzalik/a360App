package com.avenga.a360.service;

import com.avenga.a360.dao.impl.ParticipantDao;
import com.avenga.a360.service.impl.ParticipantServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ParticipantServiceImplTest {

    @InjectMocks
    ParticipantServiceImpl participantServiceImpl;

    @Mock
    private ParticipantDao participantDao;

}
