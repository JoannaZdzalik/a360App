package com.avenga.a360.service;

import com.avenga.a360.domain.model.Email;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.impl.SendEmailsWithLinksServiceImpl;
import com.avenga.a360.service.impl.SendServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SendEmailsWithLinksServiceImplTest {

    @Mock
    SendServiceImpl sendService;

    @InjectMocks
    SendEmailsWithLinksServiceImpl sendEmailsWithLinksService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void init() throws Exception {
        sendService = new SendServiceImpl();
    }



    @Test
    public void shouldFindParticipantsToBeRatedBySingleParticipant() {
        List<Participant> participants = new ArrayList<>();

        Participant kasia = new Participant();
        kasia.setEmail("kasia@yzdz");
        participants.add(kasia);

        Participant asia = new Participant();
        asia.setEmail("asia@zdz");
        participants.add(asia);

        Participant jagna = new Participant();
        jagna.setEmail("jagienka@dvdv");
        participants.add(jagna);

        Session session = new Session();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));
        session.setParticipants(participants);

        List<Participant> participantsToRate = sendEmailsWithLinksService.findParticipantsToBeRatedBySingleParticipant(asia, session);

        assertAll(
                () -> assertEquals(2, participantsToRate.size()),
                () -> assertEquals("kasia@yzdz",participantsToRate.get(0).getEmail()),
                () -> assertEquals("jagienka@dvdv", participantsToRate.get(1).getEmail()),
                () -> assertFalse( participantsToRate.contains(asia))
        );
    }

    @Test
    public void shouldFormatEndDateToYearMonthDay() {
        Session session = new Session();
        session.setEndDate(LocalDateTime.of(2020, 10, 18, 15, 00, 00));

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

    @Test
    public void shouldVerifyHowManyEmailsWithLinksHasBeenSent() {
        List<Participant> participants = new ArrayList<>();

        Participant kasia = new Participant();
        kasia.setEmail("kasia@yzdz");
        participants.add(kasia);

        Participant asia = new Participant();
        asia.setEmail("asia@zdz");
        participants.add(asia);

        Participant jagna = new Participant();
        jagna.setEmail("jagienka@dvdv");
        participants.add(jagna);

        Session session = new Session();
        session.setName("Avenga First Edition");
        session.setEndDate(LocalDateTime.now().plusDays(10L));
        session.setParticipants(participants);

        sendEmailsWithLinksService.sendEmailsWithLinks(session);

        verify(sendService, times(session.getParticipants().size())).sendEmail(any(Email.class));
    }
}
