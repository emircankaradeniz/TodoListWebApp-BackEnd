package com.example.todoWebApplication.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class AnaGorev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gorevAdi;

    @Column(nullable = false)
    private Date sonTarih;

    @Column(nullable = false)
    private Boolean tamamlandi = false;

    // Getter ve Setter'lar
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGorevAdi() {
        return gorevAdi;
    }

    public void setGorevAdi(String gorevAdi) {
        this.gorevAdi = gorevAdi;
    }

    public Date getSonTarih() {
        return sonTarih;
    }

    public void setSonTarih(Date sonTarih) {
        this.sonTarih = sonTarih;
    }

    public Boolean getTamamlandi() {
        return tamamlandi;
    }

    public void setTamamlandi(Boolean tamamlandi) {
        this.tamamlandi = tamamlandi;
    }
}
