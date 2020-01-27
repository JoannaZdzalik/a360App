package com.avenga.a360.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="id_question")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "id_participant")
    private Participant participant;

    @Column (name = "answer_text")
    private String answerText;

}
