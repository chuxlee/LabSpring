package com.example.demo.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.UUID;

@Entity // Магия: превращаем DTO в таблицу бэка!
@Table(name = "check_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID task_id; // Наш UUID задачи теперь является PRIMARY KEY!

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int urls_count;
}
