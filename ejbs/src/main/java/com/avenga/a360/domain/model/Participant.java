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
        @NamedQuery(name = "getAllParticipantsListBySessionId",
                query = "SELECT p FROM Participant p WHERE p.session = :idSession"),
        @NamedQuery(name="findParticipantById", query="SELECT p FROM Participant p where p.id = :id")
})

public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition ="VARCHAR(255) NOT NULL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_session")
    private Session session;

    @OneToMany(mappedBy="participant") // cascade = CascadeType.ALL, mappedBy = "participant"
    private List<Answer> answers;

}



