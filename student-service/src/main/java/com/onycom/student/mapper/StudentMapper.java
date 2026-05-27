package com.onycom.student.mapper;

import com.onycom.student.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> findAll();
    Student findById(Long id);
    void insert(Student student);
    void update(Student student);
    void deleteById(Long id);
    Student findByUsername(String username);
}
