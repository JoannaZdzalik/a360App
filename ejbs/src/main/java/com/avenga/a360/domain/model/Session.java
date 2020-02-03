package com.avenga.a360.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "findSessionsToSent",
                query="SELECT s FROM Session s\n" +
                        "WHERE s.endDate < now() AND s.isSent = false\n"),
        @NamedQuery(name="findSessionById", query="SELECT s FROM Session s where s.id = :id")
})

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="sessions",
        indexes = {@Index(name="indexedEndDate", columnList="end_date")}
        )
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL UNIQUE")
    private String name;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "is_sent", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isSent = false;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "session")
    private List<Participant> participants;

    @ManyToMany
    @JoinTable(
        name = "sessions_questions",
        joinColumns = @JoinColumn(name = "id_session"),
        inverseJoinColumns = @JoinColumn(name = "id_question"))
    private List<Question> questions;

    public boolean isSent() {
        return isSent;
    }
}
