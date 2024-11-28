package com.example.todoWebApplication.controller;

import com.example.todoWebApplication.model.Gorev;
import com.example.todoWebApplication.repository.AnaGorevRepository;
import com.example.todoWebApplication.repository.GorevRepository;
import com.example.todoWebApplication.service.GorevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/gorevler")
public class GorevController {

    @Autowired
    private GorevService gorevService;
    
    @Autowired
    private GorevRepository gorevRepository;

    @GetMapping
    public List<Gorev> getAllGorevler() {
        return gorevService.getAllGorevler();
    }

    @GetMapping("/{id}")
    public Gorev getGorevById(@PathVariable Long id) {
        return gorevService.getGorevById(id);
    }

    @PostMapping
    public Gorev createGorev(@RequestBody Gorev gorev) {
        return gorevService.saveGorev(gorev);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGorev(@PathVariable("id") Long id, @RequestBody Gorev gorevDetails) {
        try {
            // Görev mevcut mu kontrol et
            if (gorevRepository.existsById(id)) {
                // Görevi getir
                Gorev gorev = gorevRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));
                
                // Görev bilgilerini güncelle
                gorev.setGorevAdi(gorevDetails.getGorevAdi());
                gorev.setAciklama(gorevDetails.getAciklama());
                gorev.setSaat(gorevDetails.getSaat());
                gorev.setTamamlandi(gorevDetails.isTamamlandi());

                // Güncellenmiş görevi kaydet
                gorevRepository.save(gorev);

                return ResponseEntity.ok("Görev başarıyla güncellendi: ID=" + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Görev bulunamadı: ID=" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Görev güncellenirken hata oluştu.");
        }
    }

    
    @PutMapping("/{id}/tamamla")
    public ResponseEntity<?> completeGorev(@PathVariable("id") Long id) {
        try {
            if (gorevRepository.existsById(id)) {
                Gorev gorev = gorevRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));

                gorev.setTamamlandi(true);
                gorevRepository.save(gorev);

                return ResponseEntity.ok("Görev başarıyla tamamlandı: ID=" + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Görev bulunamadı: ID=" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Görev tamamlanırken hata oluştu.");
        }
    }


    @PutMapping("/{id}/aciklama")
    public ResponseEntity<?> updateGorevDescription(@PathVariable("id") Long id, @RequestBody String newDescription) {
        try {
            if (gorevRepository.existsById(id)) {
                Gorev gorev = gorevRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));

                gorev.setAciklama(newDescription);
                gorevRepository.save(gorev);

                return ResponseEntity.ok("Görev açıklaması başarıyla güncellendi: ID=" + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Görev bulunamadı: ID=" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Görev açıklaması güncellenirken hata oluştu.");
        }
    }


    // Görev sil
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGorev(@PathVariable("id") Long id) {
        try {
            if (gorevRepository.existsById(id)) {
                gorevRepository.deleteById(id);
                return ResponseEntity.ok("Görev başarıyla silindi: ID=" + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Görev bulunamadı: ID=" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Görev silinirken hata oluştu.");
        }
    }

}
