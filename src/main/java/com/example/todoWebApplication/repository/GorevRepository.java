package com.example.todoWebApplication.repository;

import com.example.todoWebApplication.model.Gorev;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GorevRepository extends JpaRepository<Gorev, Long> {
	List<Gorev> findByUser_Email(String email);

}
