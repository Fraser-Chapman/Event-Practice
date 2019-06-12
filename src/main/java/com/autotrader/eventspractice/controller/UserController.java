package com.autotrader.eventspractice.controller;

import com.autotrader.eventspractice.entity.User;
import com.autotrader.eventspractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value = "/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{userId}", produces = "application/json")
    @ResponseBody()
    public User getUser(@PathVariable(value = "userId") int userId) {
        return userService.getUser(userId);
    }
}
