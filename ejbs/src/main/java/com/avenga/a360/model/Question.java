package com.avenga.a360.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
@NamedQueries({
        @NamedQuery(name = "Question.findAllActiveQuestions", query = "SELECT c FROM Question c where c.isActive = true"),
        @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q where q.id = :id"),
        @NamedQuery(name = "findAllQuestions", query = "SELECT q FROM Question q")
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Question.findAllQuestionsByParticipantId",
                query = "select q.* from participants p left join sessions s on ( p.id_session = s.id )" +
                        "left join sessions_questions sq on (s.id = sq.id_session)" +
                        "left join questions q on (sq.id_question = q.id) where p.id = :id",
                resultClass = Question.class),

        @NamedNativeQuery(
                name = "Question.findAllQuestionsBySessionId",
                query = "select q.* from questions q left  join sessions_questions sq on q.id = sq.id_question where sq.id_session = :id",
                resultClass = Question.class)

})
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;

    @Column(name = "is_active", columnDefinition = "Boolean default 'true'", nullable = false)
    private Boolean isActive = true;

    @Column(name = "default_answers")
    private String defaultAnswers;

    @ManyToMany(mappedBy = "questions")
    private List<Session> sessions;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public enum QuestionType {
        TEXT,
        RADIO,
        CHECKBOX
    }

}