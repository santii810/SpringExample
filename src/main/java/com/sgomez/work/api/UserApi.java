package com.sgomez.work.api;

import com.sgomez.work.entities.User;
import com.sgomez.work.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/attendance")
public class UserApi {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getAllAttendances(@PathVariable("login") String login, Pageable inputPage) throws NotFoundException {
        return userService.findById(login);
    }
}
