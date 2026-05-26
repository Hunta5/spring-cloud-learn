package com.onycom.score.dto;

import lombok.Data;
import java.util.List;

// 返回给前端的完整数据：学生信息 + 成绩列表
@Data
public class ScoreResponse {
    private Long studentId;
    private String studentName;   // 来自 student-service
    private String studentEmail;  // 来自 student-service
    private List<SubjectScore> scores;

    @Data
    public static class SubjectScore {
        private String subject;
        private Integer score;
    }
}
