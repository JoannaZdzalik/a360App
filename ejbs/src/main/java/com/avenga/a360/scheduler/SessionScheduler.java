package com.avenga.a360.scheduler;

import com.avenga.a360.model.Session;
import com.avenga.a360.service.EmailService;
import com.avenga.a360.service.SendService;
import com.avenga.a360.service.SessionService;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.inject.Inject;
import java.util.List;

@Singleton
public class SessionScheduler {
    @Inject
    SessionService sessionService;

    @Inject
    EmailService emailService;

    @Inject
    SendService sendService;

    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void checkSessionsWhereNowIsAfterEndDateAndIsSentFalse() throws InterruptedException {
        if (sendService.checkSmtpServer()) {
            List<Session> sessionList = sessionService.findAllSessionsIsSentFalseAndEndDateIsAfterNow();
            if (!(sessionList == null)) {
                for (Session session : sessionList) {
                    sendService.sendEmailsToAllParticipants(emailService.createEmailsToParticipantsWithFeedback(session));
                    session.setIsSent(true);
                }
            }
        }

    }
    @Timeout
    public void programmaticTimeout(Timer timer) {
    }
}