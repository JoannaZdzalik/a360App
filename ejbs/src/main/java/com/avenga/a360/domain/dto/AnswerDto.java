package com.avenga.a360.domain.dto;

import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private Long id;
    private Long questionId;
    private Long participantId;
    private String answerText;
}
