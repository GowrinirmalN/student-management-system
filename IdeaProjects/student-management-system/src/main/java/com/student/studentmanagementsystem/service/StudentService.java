package com.student.studentmanagementsystem.service;

import com.student.studentmanagementsystem.entity.Student;
import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student updateStudent(Long id, Student student);

    void deleteStudent(Long id);

    List<Student> searchStudents(String name);

    List<Student> searchStudentsByEmail(String email);

    List<Student> searchStudentsByCourse(String course);
}