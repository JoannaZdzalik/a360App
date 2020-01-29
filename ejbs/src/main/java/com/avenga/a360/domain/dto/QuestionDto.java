package com.avenga.a360.domain.dto;

import com.avenga.a360.domain.model.Answer;
import com.avenga.a360.domain.model.Question;
import com.avenga.a360.domain.model.Session;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;
    private String questionText;
    private Question.QuestionType questionType;
    private String defaultAnswers;
    private Boolean isActive;
    private List<SessionDto> sessions;
    private List<AnswerDto> answers;


}
