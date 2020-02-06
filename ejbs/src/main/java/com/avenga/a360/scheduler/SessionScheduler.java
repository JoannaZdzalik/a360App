package com.avenga.a360.scheduler;

import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.domain.dto.AnswerDto;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Answer;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.SendEmailsWithLinksService;
import com.avenga.a360.service.SendFeedbackService;
import com.avenga.a360.service.SessionService;
import com.avenga.a360.service.impl.SendFeedbackServiceImpl;
import com.avenga.a360.service.impl.SessionServiceImpl;

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

    @Inject
    SendEmailsWithLinksService sendEmailsWithLinksService;

    @Inject
    SessionDao sessionDao;

    @Schedule(second = "*/5", minute = "*", hour = "*", persistent = false)
    public void sendFeedbackAtSchedule() throws InterruptedException {
        List<Session> sessionsToBeSent = sessionDao.getAllSessionsToSend();
        for (Session s : sessionsToBeSent
        ) {
            sendFeedbackService.sendFeedback(s);
            s.setSent(true);
        }
    }

    @PostConstruct
    public void initialize(){
        List<ParticipantDto> participants = new ArrayList<>();
        ParticipantDto asia = new ParticipantDto();
        asia.setId(1L);
        asia.setEmail("asia@zdz");
        asia.setUid("asdfghjklzxcvbn");
        participants.add(asia);

        ParticipantDto jagna = new ParticipantDto();
        jagna.setId(2L);
        jagna.setEmail("jagienka@dvdv");
        jagna.setUid("999rrr222hhhAAA");
        participants.add(jagna);

        SessionDto session1 = new SessionDto();
        session1.setName("Second session");
        session1.setEndDate(LocalDateTime.now().plusMinutes(2));

        sessionService.createSession(session1, participants);

    }

    @Timeout

    public void programmaticTimeout(Timer timer) {
        System.out.println("rrrr");
    }
}
