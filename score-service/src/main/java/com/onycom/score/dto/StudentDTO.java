package com.onycom.score.dto;

import lombok.Data;

// 用来接收 student-service 返回的学生信息
// 只需要我们用到的字段，不需要全部
@Data
public class StudentDTO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
