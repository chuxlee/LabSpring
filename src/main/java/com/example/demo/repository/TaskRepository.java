package com.example.demo.repository;

import com.example.demo.dto.TaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskResponse, UUID> {
    // Готовый CRUD-функционал из коробки!
}
