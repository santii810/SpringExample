package com.sgomez.example.repository;

import com.sgomez.example.entities.LoginUser;
import com.sgomez.example.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository  extends PagingAndSortingRepository<User, String> {
    LoginUser findOneByLogin(String login);
}
