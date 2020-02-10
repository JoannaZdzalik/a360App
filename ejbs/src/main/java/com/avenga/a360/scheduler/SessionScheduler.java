package com.avenga.a360.scheduler;

import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.SendFeedbackService;
import com.avenga.a360.service.SessionService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SessionScheduler {

    @Inject
    SessionService sessionService;

    @Inject
    SendFeedbackService sendFeedbackService;

    public SessionScheduler() {
    }

    @Schedule(second = "*", minute = "*/5", hour = "*", persistent = false)
    public void sendFeedbackAtSchedule() throws InterruptedException {
        List<Session> sessionsToBeSent = sessionService.findSessionsEndedInThePastButNotSent();
        System.out.println("found: " + sessionsToBeSent.size() + " sessions to send");
        if (!sessionsToBeSent.isEmpty()) {
            for (Session s : sessionsToBeSent
            ) {
                sendFeedbackService.sendFeedback(s);
                sessionService.updateSession(s);
            }
        }
    }

    @PostConstruct
    public void initialize() { //this method is only for chceking if created sessionDto will be sent to participants right after saving, method will be removed later
        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto asia = new ParticipantDto();
        asia.setEmail("asia@zdz");
        participants.add(asia);

        ParticipantDto jagna = new ParticipantDto();
        jagna.setEmail("jagienka@dvdv");
        participants.add(jagna);

        ParticipantDto kasia = new ParticipantDto();
        kasia.setEmail("katria@dvdv");
        participants.add(kasia);

        SessionDto session1 = new SessionDto();
        session1.setName("Second session");
        session1.setEndDate(LocalDateTime.now().plusMinutes(2));

        sessionService.createSession(session1, participants);
    }

}
