package com.example.todolist.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.entities.GundelikGorevler;

public interface GorevGunRepo extends JpaRepository<GundelikGorevler, Long> {

	List<GundelikGorevler> findByUserId(Long userId);


}
