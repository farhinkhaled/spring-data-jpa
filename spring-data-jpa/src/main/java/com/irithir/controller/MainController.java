package com.irithir.controller;

import com.irithir.model.Student;
import com.irithir.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private StudentService studentService;

    public MainController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/all")
    public List<Student> allStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Student studentById(@PathVariable Long id){
        return studentService.getAllStudentById(id);
    }

    @GetMapping("/students/{gender}")
    public List<Student> studentsByGender(@PathVariable String gender){
        return studentService.getStudentsByGender(gender);
    }

    @PostMapping("/save/student")
    public void saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);
    }
}
