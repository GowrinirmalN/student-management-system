package com.student.studentmanagementsystem;

import com.student.studentmanagementsystem.entity.User;
import com.student.studentmanagementsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        userRepository.deleteAll();

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin123");
        admin.setRole("ADMIN");

        User student = new User();
        student.setUsername("student");
        student.setPassword("student123");
        student.setRole("STUDENT");

        userRepository.save(admin);
        userRepository.save(student);
    }
}