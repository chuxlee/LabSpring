package com.example.demo.service;

import com.example.demo.dto.OutboxEvent;
import com.example.demo.repository.OutboxRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OutboxRelay {

    private final OutboxRepository outboxRepository;
    private final StringRedisTemplate redisTemplate; // Наш инструмент работы с Redis

    public OutboxRelay(OutboxRepository outboxRepository, StringRedisTemplate redisTemplate) {
        this.outboxRepository = outboxRepository;
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(fixedDelay = 5000) // Метод запускается автоматически каждые 5 секунд!
    public void processOutbox() {
        // 1. Вытаскиваем из СУБД задачи, готовые к отправке
        List<OutboxEvent> pendingEvents = outboxRepository.findByStatus("PENDING");

        if (pendingEvents.isEmpty()) return;

        System.out.println("=== OutboxRelay: Найдено событий для отправки: " + pendingEvents.size() + " ===");

        for (OutboxEvent event : pendingEvents) {
            try {
                // 2. Пушим сообщение в очередь Redis под именем "task-queue"
                redisTemplate.opsForList().leftPush("task-queue", event.getPayload());

                // 3. Если Redis принял пакет — меняем статус в PostgreSQL на PROCESSED
                event.setStatus("PROCESSED");
                outboxRepository.save(event);

                System.out.println("=== OutboxRelay: Событие " + event.getId() + " успешно улетело в Redis! ===");
            } catch (Exception e) {
                System.err.println("Ошибка отправки в брокер для события " + event.getId() + ": " + e.getMessage());
                // Если Redis недоступен — статус остается PENDING, транзакция не теряется!
            }
        }
    }
}
