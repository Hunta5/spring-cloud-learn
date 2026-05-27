package com.onycom.score.client;

import com.onycom.common.Result;
import com.onycom.common.ResultCode;
import com.onycom.score.dto.StudentDTO;
import org.springframework.stereotype.Component;

// 当 student-service 不可用时，自动调用这里的方法返回降级结果
@Component
public class StudentClientFallback implements StudentClient {

    @Override
    public Result<StudentDTO> findById(Long id) {
        // 返回一个友好的降级结果，而不是报错
        return Result.error(ResultCode.INTERNAL_ERROR, "学生服务暂时不可用，请稍后再试");
    }
}