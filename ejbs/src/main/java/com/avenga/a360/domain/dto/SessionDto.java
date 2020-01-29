package com.avenga.a360.domain.dto;

import com.avenga.a360.domain.model.Participant;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {

    private Long id;
    private String name;
    private LocalDateTime endDate;
    private boolean isSent;
//
//    private List<ParticipantDto> participants;
//        private List<QuestionDto> questions;

}
