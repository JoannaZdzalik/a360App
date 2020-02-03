package com.avenga.a360.service.impl;

import com.avenga.a360.dao.impl.AnswerDao;
import com.avenga.a360.dao.impl.ParticipantDao;
import com.avenga.a360.dao.impl.QuestionDao;
import com.avenga.a360.domain.dto.AnswerDto;
import com.avenga.a360.domain.model.Answer;
import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.service.AnswerService;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerServiceImpl implements AnswerService {

    ParticipantDao participantDao;
    QuestionDao questionDao;
    AnswerDao answerDao;

    @Override
    public List<AnswerDto> findAllAnswersByParticipantId(Long id) {
        List<Answer> answers = answerDao.getAllAnswersByParticipantId(id);
        return answers.stream()
                .map(u -> new AnswerDto(u.getId(), u.getParticipant().getId(), u.getQuestion().getId(), u.getAnswerText()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean createAnswer(AnswerDto answerDto) {
        Question question = questionDao.findById(answerDto.getQuestionId());
        Participant participant = participantDao.findById(answerDto.getQuestionId());

     //   validateAnswerDto(answerDto, question, participant);
        if (!validateAnswerDto(answerDto, question, participant)) {
            return false;
        }
        Answer answer = mapAnswerDtoToAnswer(answerDto, question, participant);
        answerDao.save(answer);
        return true;
    }

    public Answer mapAnswerDtoToAnswer(AnswerDto answerDto, Question question, Participant participant) {
        Answer answer = new Answer();
        answer.setId(answerDto.getId());
        answer.setAnswerText(answerDto.getAnswerText());
        answer.setParticipant(participant);
        answer.setQuestion(question);
        return answer;
    }

    public boolean validateAnswerDto(AnswerDto answerDto, Question question, Participant participant) {
        if (answerDto != null) {
            if (question == null) {
                System.err.println("Answer cannot be saved, question is null");
                return false;
            } else if (participant == null) {
                System.err.println("Answer cannot be saved, participant is null");
                return false;
            } else if (answerDto.getAnswerText() == null) {
                System.err.println("Answer cannot be saved, answerText is null");
                return false;
            } else {
                return true;
            }
        }
        System.err.println("AnswerDto is null");
        return false;
    }
}
