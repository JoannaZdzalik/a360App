package com.avenga.a360.model;


import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Optional;

@XmlRootElement
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

@NamedQueries({
        @NamedQuery(name = "findUserByLogin", query = "SELECT u FROM User u where u.login = :login"),
        @NamedQuery(name = "findAllUsers", query = "SELECT u FROM User u")
})

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NonNull
    private String login;

    @Column
    @NonNull
    private String password;

    @Column(name = "role")
    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN,
        DESIGNER
    }
}
