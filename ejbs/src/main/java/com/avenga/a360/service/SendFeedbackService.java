package com.avenga.a360.service;

import com.avenga.a360.domain.model.Email;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Session;

public interface SendFeedbackService {
    boolean sendFeedback(Session session);

    Email createFeedbackEmail(Participant participant, Session session);
}
