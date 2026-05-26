package com.onycom.student.service;

import com.onycom.student.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(Long id);
    void create(Student student);
    void update(Student student);
    void delete(Long id);
}
