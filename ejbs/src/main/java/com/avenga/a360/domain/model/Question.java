package com.avenga.a360.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "getActiveQuestionList",
                query="SELECT q FROM Question q WHERE q.isActive = true"),

        @NamedQuery(name = "findAllQuestionsByParticipantId",
                query = "SELECT q \n" +
                        "FROM Question q\n" +
                        "LEFT JOIN q.sessions s\n" +
                        "LEFT JOIN s.participants p\n" +
                        "WHERE p = :idParticipant")
})

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="questions",
        indexes = {@Index(name="indexedIsActive", columnList="is_active")}
        )
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "question_text", columnDefinition ="VARCHAR(255) NOT NULL")
    private String questionText;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    private QuestionType questionType;

    @Column(name = "default_answers")
    private String defaultAnswers;

    @Column(name = "is_active", columnDefinition ="BOOLEAN DEFAULT TRUE")
    private Boolean isActive = true;

    @ManyToMany(mappedBy="questions")
    private List<Session> sessions;

    @OneToMany (mappedBy = "question")
    private List<Answer> answers;


    public enum QuestionType {
        TEXT,
        RADIO,
    }
}
