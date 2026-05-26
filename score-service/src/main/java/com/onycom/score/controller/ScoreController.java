package com.onycom.score.controller;

import com.onycom.common.Result;
import com.onycom.score.dto.ScoreResponse;
import com.onycom.score.entity.Score;
import com.onycom.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    // 查询某学生的所有成绩（会调用 student-service）
    @GetMapping("/student/{studentId}")
    public Result<ScoreResponse> findByStudentId(@PathVariable("studentId") Long studentId) {
        return Result.success(scoreService.findByStudentId(studentId));
    }

    // 新增成绩
    @PostMapping
    public Result<String> create(@RequestBody Score score) {
        scoreService.create(score);
        return Result.success("成绩添加成功");
    }
}
