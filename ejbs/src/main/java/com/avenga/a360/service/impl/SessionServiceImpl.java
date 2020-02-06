package com.avenga.a360.service.impl;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.dao.impl.ParticipantDaoImpl;
import com.avenga.a360.dao.impl.QuestionDaoImpl;
import com.avenga.a360.dao.impl.SessionDaoImpl;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Answer;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.SendEmailsWithLinksService;
import com.avenga.a360.service.SessionService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SessionServiceImpl implements SessionService {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnoprstuwxyz0123456789";

    @Inject
    SessionDao sessionDao;
    @Inject
    QuestionDao questionDao;
    @Inject
    ParticipantDao participantDao;
    @Inject
    SendEmailsWithLinksService sendEmailsWithLinksService;

    @Override
    public List<Session> findSessionsEndedInThePastButNotSent() {
        List<Session> sessions = sessionDao.getAllSessionsToSend();
                return sessions;
    }

    @Override
    public boolean createSession(SessionDto sessionDto, List<ParticipantDto> participantsDto) {
        List<Question> questions = questionDao.getAllActiveQuestions();

        if (!validateSessionDto(sessionDto, participantsDto)) {
            return false;
        }
        Session session = mapSessionDtoToSession(sessionDto);
        session.setParticipants(mapParticipantDtoListToParticipantList(participantsDto, session));
        session.setQuestions(questions);

        if (!validateSession(session)) {
            return false;
        }
        sessionDao.save(session);
        sendEmailsWithLinksService.sendEmailsWithLinks(session);
        return true;
    }

    @Override
    public boolean updateSession(Session session){
        session.setSent(true);
        sessionDao.save(session);
        return true;
    }

    public Session mapSessionDtoToSession(SessionDto sessionDto) {
        Session session = new Session();
        session.setId(sessionDto.getId());
        session.setName(sessionDto.getName());
        session.setSent(sessionDto.isSent());
        session.setEndDate(sessionDto.getEndDate());
        return session;
    }

    public SessionDto mapSessionToSessionDto(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setName(session.getName());
        sessionDto.setEndDate(session.getEndDate());
        sessionDto.setSent(session.isSent());
        return sessionDto;
    }

    public List<Participant> mapParticipantDtoListToParticipantList(List<ParticipantDto> participantsDto, Session session) {
        List<Participant> participants = new ArrayList<>();
        for (ParticipantDto participantDto : participantsDto) {
            Participant participant = new Participant();
            participant.setSession(session);
            participant.setEmail(participantDto.getEmail());
            participant.setUid(generateUniqueUid());
            participants.add(participant);
        }
        return participants;
    }

    private boolean validateSessionDto(SessionDto sessionDto, List<ParticipantDto> participantsDto) {
        if (sessionDto != null) {
            if (sessionDto.getName() == null || sessionDto.getEndDate() == null || sessionDto.getEndDate().isBefore(LocalDateTime.now())
                    || participantsDto == null || participantsDto.size() == 0) {
                throw new IllegalArgumentException("Invalid input parameters: session name, end date and participants must be specified. End date has to be set as a future date!");
            }
        }
        return true;
    }

    private boolean validateSession(Session session) {
        if (session != null) {
            if (session.getQuestions().size() == 0 || session.getQuestions() == null) {
                System.out.println("Session cannot be created : no active questions available");
                return false;
            }
        }
        return true;
    }

    public String generateUniqueUid() {
        String generatedUId;
        while (true) {
            generatedUId = generateUidFromAlphaNumericString(15);
            if (participantDao.findByUid(generatedUId) == null) {
                break;
            }
        }
        return generatedUId;
    }

    public String generateUidFromAlphaNumericString(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
