package com.student.studentmanagementsystem.controller;

import com.student.studentmanagementsystem.entity.User;
import com.student.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User validUser = userService.login(user.getUsername(), user.getPassword());

        if (validUser != null) {
            return validUser.getRole();
        } else {
            return "Invalid";
        }
    }
}
