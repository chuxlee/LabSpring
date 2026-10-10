package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TaskResponse {
    private UUID task_id;
    private String status;
    private int urls_count;
}
