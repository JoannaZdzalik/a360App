package com.avenga.a360.service.impl;

import com.avenga.a360.dao.impl.AnswerDaoImpl;
import com.avenga.a360.dao.impl.ParticipantDaoImpl;
import com.avenga.a360.dao.impl.QuestionDaoImpl;
import com.avenga.a360.domain.dto.AnswerDto;
import com.avenga.a360.domain.model.Answer;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.service.AnswerService;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerServiceImpl implements AnswerService {

    ParticipantDaoImpl participantDao;
    QuestionDaoImpl questionDao;
    AnswerDaoImpl answerDao;

    @Override
    public boolean createAnswer(AnswerDto answerDto) {
        Question question = questionDao.findById(answerDto.getQuestionId());
        Participant participant = participantDao.findById(answerDto.getQuestionId());

        if (!validateAnswerDto(answerDto, question, participant)) {
            return false;
        }
        Answer answer = mapAnswerDtoToAnswer(answerDto, question, participant);
        answerDao.save(answer);
        return true;
    }

    @Override
    public List<AnswerDto> findAllAnswersByParticipantId(Long id) {
        List<Answer> answers = answerDao.getAllAnswersByParticipantId(id);
        return answers.stream()
                .map(u -> mapAnswerToAnswerDto(u))
                .collect(Collectors.toList());
    }

    public boolean validateAnswerDto(AnswerDto answerDto, Question question, Participant participant) {
        if (answerDto != null) {
            if (question == null || participant == null || answerDto.getAnswerText() == null) {
                System.err.println("Answer cannot be saved: question, participant or answetText is null");
                return false;
            }
            return true;
        }
        System.err.println("AnswerDto is null");
        return false;
    }

    public Answer mapAnswerDtoToAnswer(AnswerDto answerDto, Question question, Participant participant) {
        Answer answer = new Answer();
        answer.setId(answerDto.getId());
        answer.setAnswerText(answerDto.getAnswerText());
        answer.setParticipant(participant);
        answer.setQuestion(question);
        return answer;
    }

    public AnswerDto mapAnswerToAnswerDto(Answer answer) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(answer.getId());
        answerDto.setAnswerText(answer.getAnswerText());
        answerDto.setQuestionId(answer.getQuestion().getId());
        answerDto.setParticipantId(answer.getParticipant().getId());
        return answerDto;
    }
}
