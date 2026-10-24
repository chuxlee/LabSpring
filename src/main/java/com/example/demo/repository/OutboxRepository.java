package com.example.demo.repository;

import com.example.demo.dto.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxEvent, Long> {
    // Ищем только необработанные события
    List<OutboxEvent> findByStatus(String status);
}
