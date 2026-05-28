package com.onycom.score.event;

import lombok.Data;

@Data
public class StudentCreatedEvent {
    private Long studentId;
    private String name;
    private String email;
}
