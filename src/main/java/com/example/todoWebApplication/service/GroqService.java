package com.example.todoWebApplication.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GroqService {

    private static final String GROQ_API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final String GROQ_API_KEY = "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";

    public String getRecommendation(String inputText) {
        RestTemplate restTemplate = new RestTemplate();

        // Header ayarları
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + GROQ_API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // İstek gövdesi
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama-3.3-70b-versatile");
        requestBody.put("messages", new Object[]{
                Map.of("role", "user", "content", inputText)
        });

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(GROQ_API_URL, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred while fetching recommendations: " + e.getMessage();
        }
    }
}
