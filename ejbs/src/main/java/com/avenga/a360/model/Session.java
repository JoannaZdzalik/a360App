package com.avenga.a360.model;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sessions")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Session.findAllSessionsIsSentFalseAndEndDateIsAfterNow",
                query = "select * from sessions where now() > end_date and is_sent = false",
                resultClass = Session.class
        ),
        @NamedNativeQuery(
                name = "findSessionByParticipantUid",
                query = "select s.* from sessions s left join participants p on p.id_session = s.id where p.uid = :uid",
                resultClass = Session.class
        ),
        @NamedNativeQuery(
                name = "Session.findAllSessionsWhereIsSentFalse",
                query = "select * from sessions where is_sent = false",
                resultClass = Session.class
        )}
)
@NamedQueries({
        @NamedQuery(name="findSessionByName",query = "SELECT c FROM Session c where c.sessionName = :session_name")
})

public class Session implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_name", unique = true)
    @NonNull
    private String sessionName;

    @NonNull
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_sent", columnDefinition = "Boolean default 'false'", nullable = false)
    private Boolean isSent;

    @OneToMany(mappedBy = "session", cascade = {CascadeType.ALL})
    private List<Participant> participants;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "sessions_questions", joinColumns = {
            @JoinColumn(name = "id_session", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_question", referencedColumnName = "id")},
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_session", "id_question"})
            }
    )
    private List<Question> questions;
}
