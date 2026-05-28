package com.onycom.student.service.impl;

import com.onycom.student.entity.Student;
import com.onycom.student.event.StudentCreatedEvent;
import com.onycom.student.kafka.StudentEventProducer;
import com.onycom.student.mapper.StudentMapper;
import com.onycom.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final StudentEventProducer studentEventProducer;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentMapper.findById(id);
    }

    @Override
    public void create(Student student) {
        // 如果传了密码，保存前做 BCrypt 加密
        if (student.getPassword() != null && !student.getPassword().isEmpty()) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }
        studentMapper.insert(student);
        StudentCreatedEvent event = new StudentCreatedEvent(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
        studentEventProducer.sendStudentCreated(event);
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void delete(Long id) {
        studentMapper.deleteById(id);
    }
}
