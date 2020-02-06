package com.avenga.a360.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="participants",
        uniqueConstraints={@UniqueConstraint(columnNames = {"email", "id_session"})}
)
@NamedQueries({
        @NamedQuery(name="findParticipantById", query="SELECT p FROM Participant p where p.id = :id"),
        @NamedQuery (name= "findParticipantByUid", query = "SELECT p FROM Participant p where p.uid = :uid")
})

@NamedNativeQuery(
        name = "getAllParticipantsListBySessionId",
        query = "select * from participants where id_session = :id",
        resultClass = Participant.class
)

public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String uid;

    @Column(columnDefinition ="VARCHAR(255) NOT NULL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="participant")
    private List<Answer> answers;

}



