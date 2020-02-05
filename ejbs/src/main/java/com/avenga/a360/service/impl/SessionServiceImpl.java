package com.avenga.a360.service.impl;

import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.dao.impl.ParticipantDaoImpl;
import com.avenga.a360.dao.impl.QuestionDaoImpl;
import com.avenga.a360.dao.impl.SessionDaoImpl;
import com.avenga.a360.domain.dto.ParticipantDto;
import com.avenga.a360.domain.dto.SessionDto;
import com.avenga.a360.domain.model.Answer;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import com.avenga.a360.service.SessionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SessionServiceImpl implements SessionService {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnoprstuwxyz0123456789";

    SessionDaoImpl sessionDao;
    QuestionDaoImpl questionDao;
    ParticipantDao participantDao;

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
       String generatedUid = generateUidForParticipant(15);

        for (ParticipantDto participantDto : participantsDto) {
            Participant participant = new Participant();
            participant.setEmail(participantDto.getEmail());
//            while (true) {
//                String generatedUId = generateUidForParticipant(15);
//                if (participantDao.findByUid(generatedUId) == null) {
//                    participant.setUid(generatedUId);
//                    break;
//                }
//            }
            participant.setUid(generatedUid);
            participant.setSession(session);
            participants.add(participant);
        }
        return participants;
        //do dodać pętlę która sprawdza czy uid juz istnieje w bazie
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

    public String generateUidForParticipant(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

}
