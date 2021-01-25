package com.sgomez.work.service.mapper;

import com.sgomez.work.api.model.UserResponse;
import com.sgomez.work.entities.User;

public class UserMapper {
    public static UserResponse entityToDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setLogin(user.getLogin());
        userResponse.setName(user.getName());
        return userResponse;
    }
}
