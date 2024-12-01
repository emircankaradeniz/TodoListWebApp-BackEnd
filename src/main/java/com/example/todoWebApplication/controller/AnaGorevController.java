package com.example.todoWebApplication.controller;

import com.example.todoWebApplication.entity.OurUsers;
import com.example.todoWebApplication.model.AnaGorev;
import com.example.todoWebApplication.repository.AnaGorevRepository;
import com.example.todoWebApplication.repository.UsersRepo;

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
    
    @GetMapping("/tamamlanan-gorevler-aylik")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public AnaGorev createAnaGorev(@RequestBody AnaGorev anaGorev, Authentication authentication) {
        String currentUserEmail = authentication.getName();
        OurUsers user = usersRepo.findByEmail(currentUserEmail)
                         .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));
        anaGorev.setUser(user); // Görevi giriş yapan kullanıcıya bağla
        return anaGorevRepository.save(anaGorev);
    }

    // Görev güncelleme
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
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
