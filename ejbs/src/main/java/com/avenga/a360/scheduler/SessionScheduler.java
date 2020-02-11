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

    @Schedule(hour = "*", minute = "*/2", persistent = false)
    public void sendFeedbackAtSchedule() throws InterruptedException {
        List<Session> sessionsToBeSent = sessionService.findSessionsEndedInThePastButNotSent();
        if (!sessionsToBeSent.isEmpty()) {
            System.out.println("Found: " + sessionsToBeSent.size() + " sessions to send");
            for (Session s : sessionsToBeSent
            ) {
                sessionService.updateSession(s);
                sendFeedbackService.sendFeedback(s);

            }
        }
    }
    @Timeout
    public void programaticTimeout(Timer timer){

    }

}
