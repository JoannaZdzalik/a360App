package com.avenga.a360.scheduler;

import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.service.EmailService;
import com.avenga.a360.service.SessionService;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SessionSchedulerFullTest {
  /*  public int counter = 0;
    @Inject
    SessionService sessionService;

    @Inject
    EmailService emailService;
*/
    //@Schedule(hour = "*", minute = "*/1", persistent = false)
   /* public void sessionScheulder() throws InterruptedException {
        List<ParticipantDto> participantsDto = new ArrayList<>();
        ParticipantDto participantDto1 = new ParticipantDto();
        participantDto1.setEmail("lukasz.stepien@avenga.com");
        participantsDto.add(participantDto1);

        ParticipantDto participantDto2 = new ParticipantDto();
        participantDto2.setEmail("lukasz.dela@avenga.com");
        participantsDto.add(participantDto2);
        ParticipantDto participantDto3 = new ParticipantDto();
        participantDto3.setEmail("maziak01@gmial.com");
        participantsDto.add(participantDto3);

        SessionDto sessionDto = new SessionDto();
        sessionDto.setSessionName("Session name" + counter);
        sessionDto.setEndDate(LocalDateTime.of(2020, 03, 20, 00, 00, 00, 01));


        Status status = sessionService.createSession(sessionDto, participantsDto);
        System.out.println(status.getStatus());
        counter++;
    }


    @Timeout
    public void programmaticTimeout(Timer timer) {
    }*/
}