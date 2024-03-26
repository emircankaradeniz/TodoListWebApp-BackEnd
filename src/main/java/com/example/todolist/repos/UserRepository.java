package com.example.todolist.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todolist.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
}
