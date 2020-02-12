package com.avenga.a360.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "participants", uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "id_session"})})
@NamedQueries({
        @NamedQuery(name = "Participant.findById", query = "SELECT c FROM Participant c where c.id = :id"),
        @NamedQuery(name = "Participant.findByUId", query = "SELECT c FROM Participant c where c.uId = :uid")
})
@NamedNativeQuery(
        name = "Participant.findAllBySessionId",
        query = "select * from participants where id_session = :id",
        resultClass = Participant.class
)
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "uid", nullable = false, length = 15, unique = true)
    private String uId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_session")
    private Session session;

    @Override
    public String toString() {
        return "Participant{" +
                "email='" + email + '\'' +
                ", uId='" + uId + '\'' +
                '}';
    }
}
