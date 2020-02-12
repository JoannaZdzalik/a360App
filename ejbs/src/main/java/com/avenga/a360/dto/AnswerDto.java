package com.avenga.a360.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private String answerText;
    private Long questionId;
    private String participantUId;
    @JsonIgnore
    private Long participantId;
}
