package com.avenga.a360.service.impl;

import com.avenga.a360.dao.impl.QuestionDao;
import com.avenga.a360.dao.impl.SessionDao;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.SessionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SessionServiceImpl implements SessionService {

    SessionDao sessionDao;

    QuestionDao questionDao;

//    public SessionServiceImpl(SessionDao sessionDao) {
//        this.sessionDao = sessionDao;
//    }


    @Override
    public List<SessionDto> findSessionsEndedInThePastButNotSent() {
        List<Session> sessions = sessionDao.getAllSessionsToSend();
        return sessions.stream()
                .map(u -> new SessionDto(u.getId(), u.getName(), u.getEndDate(), u.isSent()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean createSession(SessionDto sessionDto, List<ParticipantDto> participantsDto) {
        List<Question> questions = questionDao.getAllActiveQuestions();

        if (sessionDto == null || questions == null || participantsDto == null) {
            sessionDtoValidator(sessionDto, participantsDto);
            return false;
        } else {
            Session session = sessionDtoToSession(sessionDto);
            session.setParticipants(participantDtoListToParticipantList(participantsDto, session));
            session.setQuestions(questions);

            if ((session.getQuestions().size() == 0) || (session.getParticipants().size() == 0) ||
                    (session.getEndDate() == null || session.getEndDate().isBefore(LocalDateTime.now())) ||
                    (session.getName() == null)) {
                sessionDtoValidator(sessionDto, participantsDto);
                return false;
            }
            sessionDao.save(session);
        }
        return true;
    }


    public Session sessionDtoToSession(SessionDto sessionDto) {
        Session session = new Session();
        session.setId(sessionDto.getId());
        session.setName(sessionDto.getName());
        session.setSent(sessionDto.isSent());
        session.setEndDate(sessionDto.getEndDate());
        return session;
    }

    public SessionDto sessionToSessionDto(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setName(session.getName());
        sessionDto.setEndDate(session.getEndDate());
        sessionDto.setSent(session.isSent());
        return sessionDto;
    }

    public List<Participant> participantDtoListToParticipantList(List<ParticipantDto> participantsDto, Session session) {
        List<Participant> participants = new ArrayList<>();
        for (ParticipantDto participantDto : participantsDto) {
            Participant participant = new Participant();
            participant.setEmail(participantDto.getEmail());
            participant.setSession(session);
//            participant.setId(participantDto.getId());
            participants.add(participant);
        }
        return participants;
    }

    private void sessionDtoValidator(SessionDto sessionDto, List<ParticipantDto> participantsDto) {
        if (sessionDto.getName() == null) {
            throw new IllegalArgumentException("Session name must be specified");
        } else if (sessionDto.getEndDate() == null) {
            throw new IllegalArgumentException("End date must be specified");
        } else if (sessionDto.getEndDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("End date has to be set as a future date!");
        }
       participantDtoValidator(participantsDto);

    }

    private void participantDtoValidator(List<ParticipantDto> participantsDto) {
        if (participantsDto == null || participantsDto.size() == 0) {
            throw new IllegalArgumentException("List of Participants cannot be empty");
        }
    }
}
