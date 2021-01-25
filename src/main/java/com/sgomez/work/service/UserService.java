package com.sgomez.work.service;


import com.sgomez.work.api.model.UserResponse;
import com.sgomez.work.exceptions.ResourceNotFoundException;
import com.sgomez.work.repository.UserRepository;
import com.sgomez.work.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserResponse findById(String login) {
        return userRepository.findById(login)
                .map(UserMapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "login", login));
    }
}
