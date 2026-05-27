package com.onycom.student.controller;

import com.onycom.common.JwtUtil;
import com.onycom.common.Result;
import com.onycom.common.ResultCode;
import com.onycom.student.dto.LoginRequest;
import com.onycom.student.entity.Student;
import com.onycom.student.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final StudentMapper studentMapper;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginRequest request){
//        1.根据用户名查学生
        Student student = studentMapper.findByUsername(request.getUsername());
//        2.用户存在不存在
        if (student == null){
            return Result.error(ResultCode.BAD_REQUEST, "用户名或密码错误");
        }
//        3.密码不匹配
        if (!student.getPassword().equals(request.getPassword())){
            return Result.error(ResultCode.BAD_REQUEST, "用户名或密码错误");
        }

//        4.生成JWT token 返回
        String token = JwtUtil.generateToken(student.getId(), student.getUsername());
        return Result.success(token);
    }
}
