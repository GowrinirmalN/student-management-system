package com.student.studentmanagementsystem.controller;

import com.student.studentmanagementsystem.entity.User;
import com.student.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userRepository.save(user);
        return "User Registered Successfully";
    }
}
