
package com.student.studentmanagementsystem.service;

import com.student.studentmanagementsystem.entity.Student;
import com.student.studentmanagementsystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existing = studentRepository.findById(id).orElseThrow();

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setCourse(student.getCourse());

        if (student.getPhoto() != null) {
            existing.setPhoto(student.getPhoto());
        }

        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> searchStudents(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> searchStudentsByEmail(String email) {
        return studentRepository.findByEmailContainingIgnoreCase(email);
    }

    @Override
    public List<Student> searchStudentsByCourse(String course) {
        return studentRepository.findByCourseContainingIgnoreCase(course);
    }
}