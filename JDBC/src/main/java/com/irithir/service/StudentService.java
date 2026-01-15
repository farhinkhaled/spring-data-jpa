package com.irithir.service;

import com.irithir.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private JdbcTemplate jdbcTemplate;

    public StudentService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveStudent(Student student){
        String sql = "insert into students(id, name, gender) values (?, ?, ?)";
        jdbcTemplate.update(sql, student.getId(), student.getName(), student.getGender());
    }

    public String getStudentNameById(Integer id){
        String sql = "select name from students where id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }

    public Student getStudentById(Integer id){
        String sql = "select * from students where id = ?";

        // the middle parameter is rowMapper function

        return jdbcTemplate.queryForObject(sql, (result, tableClms) -> {
            Student student = new Student();

            student.setId(result.getLong("id"));
            student.setName(result.getString("name"));
            student.setGender(result.getString("gender"));

            return student;
        }, id);
    }
}
