package com.student.studentmanagementsystem.repository;

import com.student.studentmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByEmailContainingIgnoreCase(String email);

    List<Student> findByCourseContainingIgnoreCase(String course);
}