package com.sgomez.example.service.mapper;

import com.sgomez.example.api.model.UserResponse;
import com.sgomez.example.entities.User;

public class UserMapper {
    public static UserResponse entityToDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setLogin(user.getLogin());
        userResponse.setName(user.getName());
        return userResponse;
    }
}
