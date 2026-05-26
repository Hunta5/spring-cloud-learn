package com.onycom.student.controller;

import com.onycom.common.Result;
import com.onycom.student.entity.Student;
import com.onycom.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // 查询所有学生
    @GetMapping
    public Result<List<Student>> findAll() {
        return Result.success(studentService.findAll());
    }

    // 根据ID查询
    @GetMapping("/{id}")
    public Result<Student> findById(@PathVariable("id") Long id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return Result.error(com.onycom.common.ResultCode.NOT_FOUND, "学生不存在");
        }
        return Result.success(student);
    }

    // 新增学生
    @PostMapping
    public Result<String> create(@RequestBody Student student) {
        studentService.create(student);
        return Result.success("新增成功");
    }

    // 更新学生
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable("id") Long id, @RequestBody Student student) {
        student.setId(id);
        studentService.update(student);
        return Result.success("更新成功");
    }

    // 删除学生
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable("id") Long id) {
        studentService.delete(id);
        return Result.success("删除成功");
    }
}
