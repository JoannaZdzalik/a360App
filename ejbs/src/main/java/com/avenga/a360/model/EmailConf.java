package com.avenga.a360.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "configuration")
//Singleton
@NamedNativeQuery(
        name = "findEmailConfiguration",
        query = "select * from configuration where id = 1",
        resultClass = EmailConf.class
)
public class EmailConf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String host;
    @Column(nullable = false)
    private int port;
    @Column(name = "fromadress", nullable = false)
    private String from;
    private String username;
    private String password;

    @Column(nullable = false)
    private boolean auth;
    @Column(nullable = false)
    private boolean debug;
}
