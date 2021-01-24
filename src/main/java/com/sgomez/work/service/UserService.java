package com.sgomez.work.service;

import com.sgomez.work.entities.User;
import com.sgomez.work.exceptions.ResourceNotFoundException;
import com.sgomez.work.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findById(String login) throws NotFoundException {
        return userRepository.findById(login)
                .orElseThrow(() -> new ResourceNotFoundException("User", "login", login));
    }
}
