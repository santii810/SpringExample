package com.sgomez.example.service;


import com.sgomez.example.api.model.UserResponse;
import com.sgomez.example.exceptions.ResourceNotFoundException;
import com.sgomez.example.repository.UserRepository;
import com.sgomez.example.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserService.class);


    public UserResponse findById(String login) {
        logger.info("Find user {}", login);
        return userRepository.findById(login)
                .map(UserMapper::entityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User", "login", login));
    }
}
