package com.example.todoWebApplication.controller;

import com.example.todoWebApplication.model.Gorev;
import com.example.todoWebApplication.service.GorevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/gorevler")
public class GorevController {

    @Autowired
    private GorevService gorevService;

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
    public Gorev updateGorev(@PathVariable Long id, @RequestBody Gorev gorevDetails) {
        Gorev gorev = gorevService.getGorevById(id);
        if (gorev != null) {
            gorev.setGorevAdi(gorevDetails.getGorevAdi());
            gorev.setAciklama(gorevDetails.getAciklama());
            gorev.setTarih(gorevDetails.getTarih());
            gorev.setSaat(gorevDetails.getSaat());
            return gorevService.saveGorev(gorev);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteGorev(@PathVariable Long id) {
        gorevService.deleteGorev(id);
    }
}
