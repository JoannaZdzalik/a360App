package com.avenga.a360.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "getAllParticipantsListBySessionId",
                query = "SELECT p FROM Participant p WHERE p.session = :idSession"),
})

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="participants",
        uniqueConstraints={@UniqueConstraint(columnNames = {"email", "id_session"})}
        )

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

}
