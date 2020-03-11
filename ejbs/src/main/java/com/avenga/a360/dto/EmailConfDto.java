package com.avenga.a360.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailConfDto {

    private Long id;
    private String host;
    private int port;
    private String from;
    private String username;
    private String password;
    private boolean auth;
    private boolean debug;
}
