package com.onycom.score.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onycom.score.entity.Score;
import com.onycom.score.event.StudentCreatedEvent;
import com.onycom.score.mapper.ScoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentEventConsumer {
    private final ObjectMapper objectMapper;
    private final ScoreMapper scoreMapper;
    @KafkaListener(topics = "student.created", groupId = "score-service-group")
    public void onStudentCreated(String message) {
        try {
            StudentCreatedEvent event = objectMapper.readValue(message, StudentCreatedEvent.class);
            log.info("[Kafka] 收到消息 → studentId: {}, name: {}", event.getStudentId(), event.getName());

            // 新学生自动初始化一条默认成绩记录
            Score score = new Score();
            score.setStudentId(event.getStudentId());
            score.setSubject("数学");
            score.setScore(0);
            scoreMapper.insert(score);
            log.info("[Kafka] 初始化成绩完成 → studentId: {}", event.getStudentId());

        } catch (Exception e) {
            log.error("[Kafka] 消息处理失败", e);
        }
    }
}
