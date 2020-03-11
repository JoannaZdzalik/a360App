package com.avenga.a360.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "configuration")
//@Singleton

@NamedNativeQuery(
        name = "findEmailConfiguration",
        query = "select * from configuration where id = 1",
        resultClass = EmailConf.class
)
public class EmailConf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String host;
    private String port;
    @Column(name = "fromadress")
    private String from;
    private String username;
    private String password;
    private boolean auth;
    private boolean debug;
}
