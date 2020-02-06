package com.avenga.a360.scheduler;

import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.service.SessionService;
import com.avenga.a360.service.impl.SessionServiceImpl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Startup
@Singleton
public class SessionScheduler {

    @Inject
    SessionService sessionService;

    @Resource
    TimerService timerService;

//    @Schedule(second = "*/5")
//    public void atSchedule() throws InterruptedException{
//        System.out.println("DeclarativeScheduler:: In atSchedule()");
//    }

    @PostConstruct
    public void initialize() {
        timerService.createTimer(0, 4000, "Every four second timer with no delay");
        List<ParticipantDto> participantsDto = new ArrayList<>();
        ParticipantDto participantDto1 = new ParticipantDto();
        participantDto1.setEmail("a@a.com");
        participantsDto.add(participantDto1);

        ParticipantDto participantDto2 = new ParticipantDto();
        participantDto2.setEmail("b@a.com");
        participantsDto.add(participantDto2);

        ParticipantDto participantDto3 = new ParticipantDto();
        participantDto3.setEmail("c@a.com");
        participantsDto.add(participantDto3);

        SessionDto sessionDto = new SessionDto();
        sessionDto.setName("Session name");
        sessionDto.setEndDate(LocalDateTime.now().plusDays(10));


        sessionService.createSession(sessionDto, participantsDto);
        System.out.println(sessionDto);
    }

    @Timeout
    public void programmaticTimeout(Timer timer) {
        System.out.println("ProgrammaticScheduler:: in programmaticTimeout");
    }

}
