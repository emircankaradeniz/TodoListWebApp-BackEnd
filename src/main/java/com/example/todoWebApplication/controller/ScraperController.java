package com.example.todoWebApplication.controller;

import com.example.todoWebApplication.dto.Product;
import com.example.todoWebApplication.service.ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ScraperController {

    @Autowired
    private ScraperService scraperService;

    @GetMapping("/api/scrape/amazon")
    public List<Product> scrapeAmazon(@RequestParam String query) {
        try {
            return scraperService.scrapeAmazon(query);
        } catch (IOException e) {
            throw new RuntimeException("Amazon scraping hatası: " + e.getMessage());
        }
    }
}
