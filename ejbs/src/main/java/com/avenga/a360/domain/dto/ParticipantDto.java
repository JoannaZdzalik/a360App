package com.avenga.a360.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto {

    private Long id;
    private String email;
    private Long idSession;
    private List<AnswerDto> answers;
}
