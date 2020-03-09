package com.avenga.a360.dto.EditDto;

import com.avenga.a360.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEditDto {

    private String login;
    private User.Role role;
}
