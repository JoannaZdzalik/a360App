package com.avenga.a360.dto;


import com.avenga.a360.model.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long question_id;
    private String question_text;
    private Question.QuestionType question_type;
    private Boolean is_default;
    private String default_answers;
}
