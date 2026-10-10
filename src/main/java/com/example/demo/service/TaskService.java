package com.example.demo.service;


import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {
    public UUID registerNewTask() {
        return UUID.randomUUID();
    }
}
