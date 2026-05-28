package com.onycom.student.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onycom.student.event.StudentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentEventProducer {
    private static final String TOPIC = "student.created";
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendStudentCreated(StudentCreatedEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC,String.valueOf(event.getStudentId()), message);
            log.info("[Kafka] 发送消息成功 → topic: {}, studentId: {}", TOPIC, event.getStudentId());
        } catch (Exception e) {
            log.error("[Kafka] 发送消息失败", e);
        }
    }

}
