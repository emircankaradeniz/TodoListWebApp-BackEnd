package com.example.todolist.repos;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.entities.gorevler;

public interface GorevRepository extends JpaRepository<gorevler, Long> {

	List<gorevler> findBygorevAdi(String gorevAdi);

	List<gorevler> findByUserId(Long long1);

}
