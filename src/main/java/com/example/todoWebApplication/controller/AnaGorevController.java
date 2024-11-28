package com.example.todoWebApplication.controller;

import com.example.todoWebApplication.model.AnaGorev;
import com.example.todoWebApplication.repository.AnaGorevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @GetMapping("/tamamlanan-gorevler-aylik")
    public Map<Object, Long> getAylikTamamlananGorevler() {
        return anaGorevRepository.findAll().stream()
            .filter(AnaGorev::getTamamlandi) // Sadece tamamlanmış görevleri al
            .collect(Collectors.groupingBy(
                gorev -> {
                    // Tarihi yıl-ay formatına çevir
                    LocalDate tarih = gorev.getSonTarih().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                    return tarih.getYear() + "-" + tarih.getMonthValue();
                },
                Collectors.counting() // Görevleri say
            ));
    }

    // Yeni görev oluşturma
    @PostMapping
    public AnaGorev createAnaGorev(@RequestBody AnaGorev gorev) {
        return anaGorevRepository.save(gorev);
    }

    // Görev güncelleme
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnaGorev(@PathVariable("id") Long id, @RequestBody AnaGorev gorevDetails) {
        try {
            if (anaGorevRepository.existsById(id)) {
                AnaGorev gorev = anaGorevRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));
                gorev.setGorevAdi(gorevDetails.getGorevAdi());
                gorev.setSonTarih(gorevDetails.getSonTarih());
                gorev.setTamamlandi(gorevDetails.getTamamlandi());
                anaGorevRepository.save(gorev);
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

    @PutMapping("/tamamla/{id}")
    public ResponseEntity<?> tamamlaGorev(@PathVariable("id") Long id) {
        try {
            if (anaGorevRepository.existsById(id)) {
                AnaGorev gorev = anaGorevRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));
                gorev.setTamamlandi(true);
                anaGorevRepository.save(gorev);
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


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnaGorev(@PathVariable("id") Long id) {
        try {
            if (anaGorevRepository.existsById(id)) {
                anaGorevRepository.deleteById(id);
                return ResponseEntity.ok("Görev başarıyla silindi: ID=" + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Görev bulunamadı: ID=" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Silme işlemi sırasında hata oluştu.");
        }
    }


}
