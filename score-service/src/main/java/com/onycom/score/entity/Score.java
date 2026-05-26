package com.onycom.score.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Score {
    private Long id;
    private Long studentId;   // 关联学生ID
    private String subject;   // 科目（数学、英语...）
    private Integer score;    // 分数
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
