package com.onycom.score.client;

import com.onycom.common.Result;
import com.onycom.score.dto.StudentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name = Nacos에 등록된 서비스 이름 (student-service의 application.yml의 spring.application.name)
@FeignClient(name = "student-service")
public interface StudentClient {

    // student-service의 GET /students/{id} 를 그대로 선언
    @GetMapping("/students/{id}")
    Result<StudentDTO> findById(@PathVariable("id") Long id);
}
