package com.avenga.a360.domain.dto;

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

    @Override
    public String toString() {
        return "AnswerDto{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", participantId=" + participantId +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
