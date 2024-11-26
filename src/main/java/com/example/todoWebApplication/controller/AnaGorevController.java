package com.example.todoWebApplication.controller;

import com.example.todoWebApplication.model.AnaGorev;
import com.example.todoWebApplication.repository.AnaGorevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/ana-gorevler")
public class AnaGorevController {

    @Autowired
    private AnaGorevRepository anaGorevRepository;

    // Tüm görevleri listeleme
    @GetMapping
    public List<AnaGorev> getAllAnaGorevler() {
        return anaGorevRepository.findAll();
    }

    // ID'ye göre görev getirme
    @GetMapping("/{id}")
    public AnaGorev getAnaGorevById(@PathVariable Long id) {
        return anaGorevRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));
    }

    // Yeni görev oluşturma
    @PostMapping
    public AnaGorev createAnaGorev(@RequestBody AnaGorev gorev) {
        return anaGorevRepository.save(gorev);
    }

    // Görev güncelleme
    @PutMapping("/{id}")
    public AnaGorev updateAnaGorev(@PathVariable Long id, @RequestBody AnaGorev gorevDetails) {
        Optional<AnaGorev> existingGorev = anaGorevRepository.findById(id);

        if (existingGorev.isPresent()) {
            AnaGorev gorev = existingGorev.get();
            gorev.setGorevAdi(gorevDetails.getGorevAdi());
            gorev.setSonTarih(gorevDetails.getSonTarih());
            gorev.setTamamlandi(gorevDetails.getTamamlandi());
            return anaGorevRepository.save(gorev);
        } else {
            throw new RuntimeException("Görev bulunamadı: ID=" + id);
        }
    }
    @PutMapping("/tamamla/{id}")
    public AnaGorev tamamlaGorev(@PathVariable Long id) {
        Optional<AnaGorev> existingGorev = anaGorevRepository.findById(id);

        if (existingGorev.isPresent()) {
            AnaGorev gorev = existingGorev.get();
            gorev.setTamamlandi(true); // Tamamlandı olarak işaretle
            return anaGorevRepository.save(gorev);
        } else {
            throw new RuntimeException("Görev bulunamadı: ID=" + id);
        }
    }


    // Görev silme
    @DeleteMapping("/{id}")
    public String deleteAnaGorev(@PathVariable Long id) {
        Optional<AnaGorev> existingGorev = anaGorevRepository.findById(id);

        if (existingGorev.isPresent()) {
            anaGorevRepository.deleteById(id);
            return "Görev başarıyla silindi: ID=" + id;
        } else {
            throw new RuntimeException("Görev bulunamadı: ID=" + id);
        }
    }
}
