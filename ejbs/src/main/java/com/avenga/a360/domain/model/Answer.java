package com.avenga.a360.domain.model;

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

@NamedQueries({
        @NamedQuery(name = "getAllAnswersByParticipantId",
                query = "SELECT a FROM Answer a WHERE a.participant = :id"),
})
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_question")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "id_participant")
    private Participant participant;

    @Column (name = "answer_text")
    private String answerText;

}
