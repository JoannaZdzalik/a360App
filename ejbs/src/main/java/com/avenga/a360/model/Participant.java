package com.avenga.a360.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="participants", uniqueConstraints={
        @UniqueConstraint(columnNames = {"email", "id_session"})
})

//@Table (name="participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition ="VARCHAR(255) NOT NULL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;

    @OneToMany(mappedBy="participant")
    private Set<Answer> answers;

    public Participant() {
    }

    public Participant(String email, Session session, Set<Answer> answers) {
        this.email = email;
        this.session = session;
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
