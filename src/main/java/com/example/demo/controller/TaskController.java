package com.example.demo.controller;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskRepository taskRepository; // Внедряем нашу базу

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        // 1. Собираем объект задачи
        TaskResponse task = new TaskResponse(
                null,
                "queued",
                request.getUrls().size()
        );

        // 2. СОХРАНЯЕМ В ПОСТГРЕС ОДНОЙ СТРОЧКОЙ!
        TaskResponse savedTask = taskRepository.save(task);

        // 3. Возвращаем клиенту
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }
}
