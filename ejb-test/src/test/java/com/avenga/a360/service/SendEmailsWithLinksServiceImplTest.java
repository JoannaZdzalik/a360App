package com.avenga.a360.service;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.impl.SendEmailsWithLinksServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SendEmailsWithLinksServiceImplTest {

    @InjectMocks
    SendEmailsWithLinksServiceImpl sendEmailsWithLinksService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateEmailWithLinksForEveryParticipantThatHasToBeRated() {
        List<Participant> participants = new ArrayList<>();
        Participant kasia = new Participant();
        kasia.setId(1L);
        kasia.setEmail("kasia@yzdz");
        kasia.setUid("123456789012345");
        participants.add(kasia);

        Participant asia = new Participant();
        asia.setId(2L);
        asia.setEmail("asia@zdz");
        asia.setUid("asdfghjklzxcvbn");
        participants.add(asia);

        Participant jagna = new Participant();
        jagna.setId(3L);
        jagna.setEmail("jagienka@dvdv");
        jagna.setUid("999rrr222hhhAAA");
        participants.add(jagna);

        Session session = new Session();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));
        session.setParticipants(participants);

    }

    @Test
    public void shouldFormatEndDateToYearMonthDay() {
        Session session = new Session();
        session.setEndDate(LocalDateTime.of(2020, 10,18,15, 00, 00));

        assertEquals("2020-10-18", SendEmailsWithLinksServiceImpl.formatEndDate(session.getEndDate()));
    }

    @Test
    public void shouldFormatSessionNameToLowerCaseWithoutWhitespace() {
        Session session = new Session();
        session.setName("Avenga First Edition");

        assertEquals("avengafirstedition", SendEmailsWithLinksServiceImpl.formatSessionName(session));
    }

    @Test
    public void shouldCreateEmailSubjectUsingGivenSessionName() {
        Session session = new Session();
        session.setName("Avenga First Edition");

        assertEquals("New feedback session Avenga First Edition to be completed", SendEmailsWithLinksServiceImpl.createEmailSubject(session));
    }
}
