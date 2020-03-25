package com.avenga.a360.dto.EditDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionEditDto {

    private Long question_id;
    private Boolean is_active;
    private Boolean is_default;
}
