package com.sgomez.work.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LoginUser {
    private String login;
    private String password;

    public LoginUser(User user) {
        this.login = user.getLogin();
        this.password = user.getPassword();
    }
}
