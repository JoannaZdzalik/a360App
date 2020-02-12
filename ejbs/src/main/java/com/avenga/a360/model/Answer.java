package com.avenga.a360.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
@NamedNativeQuery(name = "Answer.shouldFindAllAnswersByParticipantId",
        query = "SELECT a.* FROM participants p left join answers a on p.id = a.id_participant where p.id = :id",
        resultClass = Answer.class)
public class Answer implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_text")
    private String answerText;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_question")
    private Question question;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_participant")
    private Participant participant;

}
