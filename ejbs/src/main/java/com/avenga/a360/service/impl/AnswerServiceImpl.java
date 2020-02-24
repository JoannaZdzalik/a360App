package com.avenga.a360.service.impl;

import com.avenga.a360.dao.AnswerDao;
import com.avenga.a360.dao.ParticipantDao;
import com.avenga.a360.dao.QuestionDao;
import com.avenga.a360.dto.AnswerDto;
import com.avenga.a360.model.Answer;
import com.avenga.a360.model.Participant;
import com.avenga.a360.model.Question;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.model.response.StatusMessage;
import com.avenga.a360.service.AnswerService;

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
    public List<Status> createAnswersDto(List<AnswerDto> lists){
        List<Status> statusList = new ArrayList<>();

        for(AnswerDto answerDto : lists){
            statusList.add(createAnswer(answerDto));

        }
        return statusList;
    }

}
