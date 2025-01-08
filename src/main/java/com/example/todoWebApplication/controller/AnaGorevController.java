package com.example.todoWebApplication.controller;

import com.example.todoWebApplication.entity.OurUsers;
import com.example.todoWebApplication.model.AnaGorev;
import com.example.todoWebApplication.repository.AnaGorevRepository;
import com.example.todoWebApplication.repository.UsersRepo;
import com.example.todoWebApplication.service.GroqService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/ana-gorevler")
public class AnaGorevController {

    @Autowired
    private AnaGorevRepository anaGorevRepository;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private GroqService groqService; // Tavsiye almak için servis

    // Tüm görevleri listeleme
    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<AnaGorev> getAllAnaGorevler(Authentication authentication) {
        String currentUserEmail = authentication.getName();
        return anaGorevRepository.findByUser_Email(currentUserEmail);
    }

    // ID'ye göre görev getirme
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public AnaGorev getAnaGorevById(@PathVariable Long id) {
        return anaGorevRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));
    }

    // Aylık tamamlanan görevler
    @GetMapping("/tamamlanan-gorevler-aylik")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public Map<Object, Long> getAylikTamamlananGorevler(Authentication authentication) {
        String currentUserEmail = authentication.getName();
        OurUsers user = usersRepo.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));
        return anaGorevRepository.findByUser_Email(user.getEmail()).stream()
                .filter(AnaGorev::getTamamlandi)
                .collect(Collectors.groupingBy(
                        gorev -> {
                            LocalDate tarih = gorev.getSonTarih().toInstant()
                                    .atZone(ZoneId.systemDefault()).toLocalDate();
                            return tarih.getYear() + "-" + tarih.getMonthValue();
                        },
                        Collectors.counting()
                ));
    }

    // Yeni görev oluşturma
    @PostMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public AnaGorev createAnaGorev(@RequestBody AnaGorev anaGorev, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        OurUsers user = usersRepo.findByEmail(currentUserEmail)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));
        anaGorev.setUser(user);
        return anaGorevRepository.save(anaGorev);
    }

    // Görev güncelleme
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<?> updateAnaGorev(@PathVariable("id") Long id, @RequestBody AnaGorev gorevDetails) {
        try {
            AnaGorev gorev = anaGorevRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));
            gorev.setGorevAdi(gorevDetails.getGorevAdi());
            gorev.setSonTarih(gorevDetails.getSonTarih());
            gorev.setSaat(gorevDetails.getSaat());
            gorev.setTamamlandi(gorevDetails.getTamamlandi());
            anaGorevRepository.save(gorev);
            return ResponseEntity.ok("Görev başarıyla güncellendi: ID=" + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Görev güncellenirken hata oluştu.");
        }
    }

    // Görev tamamlama
    @PutMapping("/tamamla/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<?> tamamlaGorev(@PathVariable("id") Long id) {
        try {
            AnaGorev gorev = anaGorevRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Görev bulunamadı: ID=" + id));
            gorev.setTamamlandi(true);
            anaGorevRepository.save(gorev);
            return ResponseEntity.ok("Görev başarıyla tamamlandı: ID=" + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Görev tamamlanırken hata oluştu.");
        }
    }

    // Görev silme
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteAnaGorev(@PathVariable("id") Long id) {
        try {
            anaGorevRepository.deleteById(id);
            return ResponseEntity.ok("Görev başarıyla silindi: ID=" + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Silme işlemi sırasında hata oluştu.");
        }
    }

    // Görev adına göre tavsiyeler alma
    @PostMapping("/tavsiyeler")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String getTavsiyeler(@RequestParam String gorevAdi) {
        return groqService.getRecommendation(gorevAdi);
    }
}
