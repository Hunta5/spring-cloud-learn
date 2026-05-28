package com.onycom.student.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreatedEvent {
    private Long StudentId;
    private String name;
    private String email;
}
