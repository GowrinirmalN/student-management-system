package com.student.studentmanagementsystem.controller;

import com.student.studentmanagementsystem.entity.Student;
import com.student.studentmanagementsystem.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET ALL STUDENTS
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET STUDENT BY ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getAllStudents()
                .stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // SAVE STUDENT
    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // UPDATE STUDENT
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
                                 @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    // UPLOAD PHOTO
    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadPhoto(@PathVariable Long id,
                              @RequestParam("file") MultipartFile file) {

        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";

            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileName = file.getOriginalFilename();

            File destination = new File(uploadDir + fileName);

            file.transferTo(destination);

            Student student = studentService.getAllStudents()
                    .stream()
                    .filter(s -> s.getId().equals(id))
                    .findFirst()
                    .orElse(null);

            if (student != null) {
                student.setPhoto("/uploads/" + fileName);
                studentService.updateStudent(id, student);
            }

            return "Photo Uploaded Successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "Upload Failed";
        }
    }

    // DELETE STUDENT
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // SEARCH NAME
    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String name) {
        return studentService.searchStudents(name);
    }

    // SEARCH EMAIL
    @GetMapping("/search/email")
    public List<Student> searchStudentsByEmail(@RequestParam String email) {
        return studentService.searchStudentsByEmail(email);
    }

    // SEARCH COURSE
    @GetMapping("/search/course")
    public List<Student> searchStudentsByCourse(@RequestParam String course) {
        return studentService.searchStudentsByCourse(course);
    }
}