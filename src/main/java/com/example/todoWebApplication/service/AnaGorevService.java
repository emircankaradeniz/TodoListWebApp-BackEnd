package com.example.todoWebApplication.service;

import com.example.todoWebApplication.model.AnaGorev;
import com.example.todoWebApplication.repository.AnaGorevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaGorevService {

    @Autowired
    private AnaGorevRepository anaGorevRepository;

    public List<AnaGorev> getAllAnaGorevler() {
        return anaGorevRepository.findAll();
    }

    public AnaGorev saveAnaGorev(AnaGorev gorev) {
        return anaGorevRepository.save(gorev);
    }

    public AnaGorev updateAnaGorev(Long id, AnaGorev gorevDetails) {
        AnaGorev gorev = anaGorevRepository.findById(id).orElseThrow();
        gorev.setGorevAdi(gorevDetails.getGorevAdi());
        gorev.setSonTarih(gorevDetails.getSonTarih());
        gorev.setTamamlandi(gorevDetails.getTamamlandi());
        return anaGorevRepository.save(gorev);
    }

    public void deleteAnaGorev(Long id) {
        anaGorevRepository.deleteById(id);
    }
}
