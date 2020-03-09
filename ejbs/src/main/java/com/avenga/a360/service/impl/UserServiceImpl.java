package com.avenga.a360.service.impl;

import com.avenga.a360.dao.UserDao;
import com.avenga.a360.dto.UserDto;
import com.avenga.a360.dto.EditDto.UserEditDto;
import com.avenga.a360.model.User;
import com.avenga.a360.model.response.Status;
import com.avenga.a360.model.response.StatusMessage;
import com.avenga.a360.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    UserDao userDao;

    @Override
    public Status createUser(UserDto userDto) {
        Status status = new Status();
        List<StatusMessage> statusMessages = new ArrayList<>();
        if (userDto != null) {
            if (userDto.getLogin() == null || userDto.getPassword() == null) {
                status.setStatus("Fail");
                statusMessages.add(new StatusMessage("Login or password are null"));
            } else if (userDao.findLoginInDB(userDto.getLogin())) {
                status.setStatus("Fail");
                statusMessages.add(new StatusMessage("Login already exists in a database"));
            } else {
                userDao.createUser(convertUserDtoToUser(userDto));
                status.setStatus("Success");
                statusMessages.add(new StatusMessage("User created"));
            }
        }
        status.setStatusMessageList(statusMessages);
        return status;
    }

    @Override
    public boolean updateUserRole(UserEditDto userEditDto) {
        User user = userDao.findUserByLogin(userEditDto.getLogin());
        if (user != null) {
            user.setRole(userEditDto.getRole());
            userDao.updateUser(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> userList = userDao.findAllUsers();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : userList) {
            usersDto.add(convertUserToUserDto(user));
        }
        return usersDto;
    }

    public User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(PasswordServiceImpl.hashPassword(userDto.getPassword()));
        user.setRole(User.Role.DESIGNER);
        return user;
    }

    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        //  userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
