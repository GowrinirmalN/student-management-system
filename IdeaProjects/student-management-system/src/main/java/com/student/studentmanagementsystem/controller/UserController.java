package com.student.studentmanagementsystem.controller;

import com.student.studentmanagementsystem.entity.User;
import com.student.studentmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "User Deleted";
    }

    @PutMapping("/change-password/{username}")
    public String changePassword(@PathVariable String username,
                                 @RequestBody User updatedUser) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return "User Not Found";
        }

        if (!user.getPassword().equals(updatedUser.getPassword())) {
            return "Old Password Incorrect";
        }

        user.setPassword(updatedUser.getRole());
        userRepository.save(user);

        return "Password Updated Successfully";
    }

    @PutMapping("/forgot-password/{username}")
    public String forgotPassword(@PathVariable String username,
                                 @RequestBody User updatedUser) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return "User Not Found";
        }

        user.setPassword(updatedUser.getPassword());
        userRepository.save(user);

        return "Password Reset Successfully";
    }
}