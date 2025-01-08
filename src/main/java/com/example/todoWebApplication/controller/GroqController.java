package com.example.todoWebApplication.controller;

import com.example.todoWebApplication.service.GroqService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groq")
public class GroqController {

    @Autowired
    private GroqService groqService;

    @PostMapping("/recommendations")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<?> getRecommendations(@RequestBody Map<String, String> requestBody) {
        String inputText = requestBody.get("inputText");
        String recommendations = groqService.getRecommendation(inputText);
        return ResponseEntity.ok(recommendations);
    }
}
