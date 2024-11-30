package com.example.todoWebApplication.startup;

import com.example.todoWebApplication.model.Gorev;
import com.example.todoWebApplication.repository.GorevRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ApplicationStartup implements CommandLineRunner {

    private final GorevRepository gorevRepository;

    public ApplicationStartup(GorevRepository gorevRepository) {
        this.gorevRepository = gorevRepository;
    }

    @Override
    public void run(String... args) {
        List<Gorev> gorevler = gorevRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Gorev gorev : gorevler) {
            if (gorev.getLastResetDate() == null || !gorev.getLastResetDate().equals(today)) {
                // Tamamlanma durumunu sıfırla
                gorev.setTamamlandi(false);
                gorev.setLastResetDate(today);
            }
        }

        gorevRepository.saveAll(gorevler);

        System.out.println("Uygulama başlatıldığında tamamlanma durumu kontrol edildi ve sıfırlandı.");
    }
}
