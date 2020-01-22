package com.avenga.a360;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL UNIQUE")
    private String name;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "is_sent", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isSent;

    @OneToMany (mappedBy = "session")
    private Set<Participant> participants;

    @ManyToMany
    @JoinTable(
        name = "sessions_questions",
        joinColumns = @JoinColumn(name = "id_session"),
        inverseJoinColumns = @JoinColumn(name = "id_question"))
    private Set<Question> questions;

    public Session() {
    }

    public Session(String name, LocalDateTime endDate, boolean isSent, Set<Participant> participants, Set<Question> questions) {

        this.name = name;
        this.endDate = endDate;
        this.isSent = isSent;
        this.participants = participants;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean getIsSent() {
        return isSent;
    }

    public void setIsSent(boolean isSent) {
        this.isSent = isSent;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
