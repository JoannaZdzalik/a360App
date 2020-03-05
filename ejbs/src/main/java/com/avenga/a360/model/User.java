package com.avenga.a360.model;


import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    @NonNull
    private String login;

    @Column
    @NonNull
    private String password;

    @Column
    @NonNull
    @Enumerated(EnumType.STRING)
    private RoleType role;

    public enum RoleType {
        ADMIN,
        DESIGNER
    }

}
