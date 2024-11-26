package com.example.todoWebApplication.repository;

import com.example.todoWebApplication.model.AnaGorev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaGorevRepository extends JpaRepository<AnaGorev, Long> {
}
