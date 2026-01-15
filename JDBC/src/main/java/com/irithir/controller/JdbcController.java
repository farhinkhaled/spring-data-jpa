package com.irithir.controller;

import com.irithir.model.Student;
import com.irithir.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JdbcController {
    private StudentService studentService;

    public JdbcController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save/student")
    public ResponseEntity<?> saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);

        return new ResponseEntity<>("Student info saved successfully!",
                HttpStatus.CREATED);
    }

    @GetMapping("/student/name/{id}")
    public ResponseEntity<?> getStudentName(@PathVariable Integer id){
        String studentName = studentService.getStudentNameById(id);

        return new ResponseEntity<>(studentName, HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Integer id){
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
