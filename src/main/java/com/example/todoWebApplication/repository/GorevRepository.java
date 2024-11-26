package com.example.todoWebApplication.repository;

import com.example.todoWebApplication.model.Gorev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GorevRepository extends JpaRepository<Gorev, Long> {
}
