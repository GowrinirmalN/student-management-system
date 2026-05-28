package com.student.studentmanagementsystem.controller;

import com.student.studentmanagementsystem.entity.User;
import com.student.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class StudentProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile/{username}")
    public User getProfile(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}
