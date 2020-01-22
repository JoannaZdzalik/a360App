package com.avenga.a360;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "question_text", columnDefinition ="VARCHAR(255) NOT NULL")
    private String questionText;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_type")
    private QuestionType questionType;

    @Column(name = "default_answers", length=255)
    private String defaultAnswers;

    @Column(name = "is_active", columnDefinition ="BOOLEAN DEFAULT FALSE")
    private Boolean isActive;

    @ManyToMany(mappedBy="questions")
    private Set<Session> sessions;

    @OneToMany (mappedBy = "question")
    private Set<Answer> answers;

    public Question() {
    }

    public Question(String questionText, QuestionType questionType, String defaultAnswers, Boolean isActive, Set<Session> sessions, Set<Answer> answers) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.defaultAnswers = defaultAnswers;
        this.isActive = isActive;
        this.sessions = sessions;
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getDefaultAnswers() {
        return defaultAnswers;
    }

    public void setDefaultAnswers(String defaultAnswers) {
        this.defaultAnswers = defaultAnswers;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public enum QuestionType {
        TEXT,
        RADIO,
    }
}
