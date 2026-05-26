package com.onycom.score.service.impl;

import com.onycom.common.Result;
import com.onycom.score.client.StudentClient;
import com.onycom.score.dto.ScoreResponse;
import com.onycom.score.dto.StudentDTO;
import com.onycom.score.entity.Score;
import com.onycom.score.mapper.ScoreMapper;
import com.onycom.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoreMapper scoreMapper;
    private final StudentClient studentClient;  // OpenFeign 客户端，自动注入

    @Override
    public ScoreResponse findByStudentId(Long studentId) {
        // 1. 通过 OpenFeign 调用 student-service 获取学生信息
        log.info("【Feign调用】调用 student-service 查询学生 ID={}", studentId);
        Result<StudentDTO> studentResult = studentClient.findById(studentId);
        StudentDTO student = studentResult.getData();

        // 2. 查询本服务数据库获取成绩列表
        List<Score> scores = scoreMapper.findByStudentId(studentId);

        // 3. 组合数据返回
        ScoreResponse response = new ScoreResponse();
        response.setStudentId(studentId);
        response.setStudentName(student.getName());
        response.setStudentEmail(student.getEmail());

        List<ScoreResponse.SubjectScore> subjectScores = scores.stream().map(s -> {
            ScoreResponse.SubjectScore ss = new ScoreResponse.SubjectScore();
            ss.setSubject(s.getSubject());
            ss.setScore(s.getScore());
            return ss;
        }).toList();

        response.setScores(subjectScores);
        return response;
    }

    @Override
    public void create(Score score) {
        scoreMapper.insert(score);
    }
}
