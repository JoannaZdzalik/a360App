package com.avenga.a360.service.impl;

import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.SessionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SessionServiceImpl implements SessionService {

 SessionDao sessionDao;

    QuestionDao qDao;

    public SessionServiceImpl(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }


//    @Override
//    public List<SessionDto> findSessionsToSend() {
//        List<Session> sessions = sessionDao.getAllSessionsToSend();
//        return sessions.stream()
//              .map(u -> new SessionDto(u.getId(), u.getName(), u.getEndDate(), u.isSent(), u.getParticipants(), u.getQuestions()))
//                .collect(Collectors.toList());
//    }

    //    @Override
    public boolean createNewSession(SessionDto sessionDto, List<ParticipantDto> participantsDto) {
        Session newSession = sessionDtoToSession(sessionDto);
        newSession.setParticipants(participantDtoListTParticipantList(participantsDto,newSession));

        List<Question> questions = qDao.getAll();
        newSession.setQuestions(questions);
    //    newSession.setQuestions(new ArrayList<>(qDao.getAll()));

        if (qDao.getAll().isEmpty() ||
                participantsDto.isEmpty() ||
                sessionDto.getEndDate() == null ||
                sessionDto.getEndDate().isBefore(LocalDateTime.now()) ||
                sessionDto.getName() == null) {
            return false;
        }
        sessionDao.save(newSession);
        return true;
    }

    private Session sessionDtoToSession(SessionDto sessionDto) {
        Session session = new Session();
        session.setId(sessionDto.getId());
        session.setName(sessionDto.getName());
        session.setSent(sessionDto.isSent());
        session.setEndDate(sessionDto.getEndDate());
        return session;
    }

    private SessionDto sessionToSessionDto(Session session) {
        SessionDto sessionDto = new SessionDto();
        sessionDto.setId(session.getId());
        sessionDto.setName(session.getName());
        sessionDto.setEndDate(session.getEndDate());
        sessionDto.setSent(session.isSent());
        return sessionDto;
    }

    public List<Participant> participantDtoListTParticipantList(List<ParticipantDto> participantsDto, Session session) {
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


}
