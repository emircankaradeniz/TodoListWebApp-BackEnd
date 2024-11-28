package com.example.todoWebApplication.service;

import com.example.todoWebApplication.model.Gorev;
import com.example.todoWebApplication.repository.GorevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GorevService {

    @Autowired
    private GorevRepository gorevRepository;
    
    public GorevService(GorevRepository gorevRepository) {
        this.gorevRepository = gorevRepository;
    }

    public Optional<Gorev> findById(Long id) {
        return gorevRepository.findById(id);
    }

    public Gorev save(Gorev gorev) {
        return gorevRepository.save(gorev);
    }
    
    public List<Gorev> getAllGorevler() {
        return gorevRepository.findAll();
    }

    public Gorev getGorevById(Long id) {
        return gorevRepository.findById(id).orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));
    }

    public Gorev saveGorev(Gorev gorev) {
        return gorevRepository.save(gorev);
    }

    public Gorev updateGorev(Long id, Gorev gorevDetails) {
        Gorev gorev = getGorevById(id);
        gorev.setGorevAdi(gorevDetails.getGorevAdi());
        gorev.setSaat(gorevDetails.getSaat());
        gorev.setAciklama(gorevDetails.getAciklama());
        return gorevRepository.save(gorev);
    }

    public Gorev completeGorev(Long id) {
        Gorev gorev = getGorevById(id);
        gorev.setTamamlandi(true);
        return gorevRepository.save(gorev);
    }

    public Gorev updateDescription(Long id, String newDescription) {
        Gorev gorev = getGorevById(id);
        gorev.setAciklama(newDescription);
        return gorevRepository.save(gorev);
    }

    public void deleteGorev(Long id) {
        gorevRepository.deleteById(id);
    }
}

