package com.avenga.a360.service;

import com.avenga.a360.dto.UserDto;
import com.avenga.a360.model.User;

public interface LoginService {
    //czesc moze do UserService?

    User getLoggedUser();
    String getLoggedUserLogin();
}
