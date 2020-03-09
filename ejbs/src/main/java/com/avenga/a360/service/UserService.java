package com.avenga.a360.service;

import com.avenga.a360.dto.UserDto;
import com.avenga.a360.dto.EditDto.UserEditDto;
import com.avenga.a360.model.response.Status;

import java.util.List;

public interface UserService {

    Status createUser (UserDto userDto);
    boolean updateUserRole(UserEditDto userEditDto);
    List<UserDto> findAllUsers();

}
