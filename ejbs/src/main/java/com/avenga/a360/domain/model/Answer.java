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
@Table(name = "answers")

@NamedQueries({
//        @NamedQuery(name = "getAllAnswersByParticipantId",
//                query = "SELECT a FROM Answer a WHERE a.participant= :id"),
        // @NamedQuery(name= "getAnswersByParticipantIdAndQuestionId", query = "SELECT a FROM Answer a WHERE a.participant = :id AND a.question= :idQuestion")
})

@NamedNativeQuery(name = "getAllAnswersByParticipantId",
        query = "SELECT a.* FROM participants p left join answers a on p.id = a.id_participant where p.id = :idParticipant",
        resultClass = Answer.class)
@NamedNativeQuery(
        name = "getAnswersByParticipantIdAndQuestionId",
        query = "select * from answers a left join participants p on ( a.id_participant = p.id )" +
                "left join questions q on (a.id_question = q.id) where p.id = :idParticipant and q.id= :idQuestion",
        resultClass = Answer.class)

public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_question")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "id_participant")
    private Participant participant;

    @Column(name = "answer_text")
    private String answerText;

}
