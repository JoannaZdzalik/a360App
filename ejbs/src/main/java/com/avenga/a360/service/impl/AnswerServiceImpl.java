package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dao.SessionDao;
import com.avenga.a360.dto.AnswerDto;
import com.avenga.a360.dto.AnswerSessionDto;
import com.avenga.a360.dto.ParticipantDto;
import com.avenga.a360.dto.SessionDto;
import com.avenga.a360.model.Answer;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Question;
import com.avenga.a360.model.Session;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.model.response.StatusMessage;
import com.avenga.a360.service.AnswerService;
import com.avenga.a360.service.SessionService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AnswerServiceImpl implements AnswerService {

    @Inject
    AnswerDao answerDao;

    @Inject
    ParticipantDao participantDao;

    @Inject
    QuestionDao questionDao;
    @Inject
    SessionDao sessionDao;
    @Inject
    SessionService sessionService;


    @Override
    public List<Answer> findAllAnswersByParticipantId(Long id) {
        return answerDao.findAllAnswersByParticipantId(id);
    }

    @Override
    public Status createAnswer(AnswerDto answerDto) {
        Status status = new Status();
        List<StatusMessage> statusMessages = new ArrayList<>();

        if (answerDto != null) {
            Question question = questionDao.findById(answerDto.getQuestionId());
            Participant participant = participantDao.findByUId(answerDto.getParticipantUId());

            validateIsNull(status, question, statusMessages, "Question list is null.");

            validateIsNull(status, participant, statusMessages, "participant object is null");

            if (participant != null && question != null) {
                if (answerDto.getAnswerText() == null) {
                    status.setStatus("fail");
                    statusMessages.add(new StatusMessage("answer text is empty"));

                } else {
                    answerDao.createAnswer(answerDtoToAnswer(answerDto, question, participant));
                    status.setStatus("success");
                    statusMessages.add(new StatusMessage("Answer created"));
                }
            }
        } else {
            status.setStatus("fail");
            statusMessages.add(new StatusMessage("answerDto is empty"));
        }

        status.setStatusMessageList(statusMessages);
        return status;
    }

    public Answer answerDtoToAnswer(AnswerDto answerDto, Question question, Participant participant) {
        Answer answer = new Answer();
        answer.setAnswerText(answerDto.getAnswerText());
        answer.setQuestion(question);
        answer.setParticipant(participant);
        return answer;
    }

    private boolean validateIsNull(Status status, Object o, List<StatusMessage> statusMessageList, String message) {
        if (o == null) {
            statusMessageList.add((new StatusMessage(message)));
            status.setStatus("fail");
            return false;
        }
        return true;
    }

    @Override
    public List<Status> createAnswersDto(List<AnswerDto> lists) {
        List<Status> statusList = new ArrayList<>();

        for (AnswerDto answerDto : lists) {
            statusList.add(createAnswer(answerDto));

        }
        return statusList;
    }

//    public List<AnswerDto> answerToAnswerDto(List<Answer> answerList) {
//        List<AnswerDto> answerDtoList = new ArrayList<>();
//        for (Answer answer : answerList) {
//            AnswerDto answerDto = new AnswerDto();
//            answerDto.setQuestionId(answer.getQuestion().getId());
//            answerDto.setParticipantId(answer.getParticipant().getId());
//            answerDto.setAnswerText(answer.getAnswerText());
//            answerDtoList.add(answerDto);
//        }
//
//        return answerDtoList;
//    }
@Override
    public List<AnswerSessionDto> amountOfAnswersForSessionActive() {
        List<AnswerSessionDto> answerSessionDtoList = new ArrayList<>();
        List<SessionDto> sessionDtoList = sessionService.sessionListToSessionDtoList(sessionDao.findAllSessionsWhereIsSentFalse());

        for (SessionDto sessionDto : sessionDtoList) {
            AnswerSessionDto answerSessionDto = new AnswerSessionDto();
            answerSessionDto.setSessionName(sessionDto.getSessionName());
            answerSessionDto.setAmountOfAnswers(answerDao.findAllAnswersForOneSession(sessionDto.getSessionName()).size());
            answerSessionDtoList.add(answerSessionDto);

            }

        return answerSessionDtoList;
    }
}

