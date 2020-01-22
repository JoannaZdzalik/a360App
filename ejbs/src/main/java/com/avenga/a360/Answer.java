package com.avenga.a360;

import javax.persistence.*;

@Entity
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

    public Answer() {
    }

    public Answer(Question question, Participant participant, String answerText) {
        this.question = question;
        this.participant = participant;
        this.answerText = answerText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
