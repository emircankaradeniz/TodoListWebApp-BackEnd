package com.example.todoWebApplication.service;

import com.example.todoWebApplication.model.Gorev;
import com.example.todoWebApplication.repository.GorevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GorevService {

    @Autowired
    private GorevRepository gorevRepository;

    public List<Gorev> getAllGorevler() {
        return gorevRepository.findAll();
    }

    public Gorev getGorevById(Long id) {
        return gorevRepository.findById(id).orElse(null);
    }

    public Gorev saveGorev(Gorev gorev) {
        return gorevRepository.save(gorev);
    }

    public void deleteGorev(Long id) {
        gorevRepository.deleteById(id);
    }
}
