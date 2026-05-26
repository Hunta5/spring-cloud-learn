package com.onycom.score.service;

import com.onycom.score.dto.ScoreResponse;
import com.onycom.score.entity.Score;

public interface ScoreService {
    // 查询某学生的所有成绩（同时获取学生信息）
    ScoreResponse findByStudentId(Long studentId);

    // 新增成绩
    void create(Score score);
}
