package com.irithir.service;

import com.irithir.model.Laptop;
import com.irithir.model.Student;
import com.irithir.repository.LaptopRepository;
import com.irithir.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private LaptopService laptopService;

    // StudentRepository studentRepository -> Object of the implement that Hibernate provided
    public StudentService(StudentRepository studentRepository,
                          LaptopService laptopService) {
        this.studentRepository = studentRepository;
        this.laptopService = laptopService;
    }

    public List<Student>  getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getAllStudentById(Long id){
//        return studentRepository.findById(id).get();
        Optional<Student> studentOptional = studentRepository.findById(id);

        // if present return the student otherwise return null
        return studentOptional.orElse(null);
    }

    public List<Student> getStudentsByGender(String gender){
        return studentRepository.findByGender(gender);
    }

    public void saveStudent(Student student){
        if(student.getLaptopList() != null){
            List<Laptop> laptopList = new ArrayList<>();

            for(Laptop laptop: student.getLaptopList()){
                laptopList.add(laptopService.getLaptopById(laptop.getId()));
            }

            student.setLaptopList(laptopList);
        }

        studentRepository.save(student);
    }
}
