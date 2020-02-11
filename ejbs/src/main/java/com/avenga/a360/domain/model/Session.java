package com.avenga.a360.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(name="findSessionById", query="SELECT s FROM Session s where s.id = :id")
})

@NamedNativeQuery(
        name = "findSessionsToSend",
        query = "select * from sessions where now() > end_date and is_sent = 'false'",
        resultClass = Session.class
)


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

    @Column(name = "session_name", unique = true)
    private String sessionName;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "is_sent", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isSent = false;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "session")
    private List<Participant> participants;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "sessions_questions", joinColumns = {
            @JoinColumn(name = "id_session", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_question", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_session", "id_question"})
            }
    )

    public boolean isSent() {
        return isSent;
    }
}
