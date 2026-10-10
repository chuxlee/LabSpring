package com.example.demo.dto;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class TaskRequest {
    private List<String> urls;


    public List<String> getUrls() {
        return urls;
    }
}