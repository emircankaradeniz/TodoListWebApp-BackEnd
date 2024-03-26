package com.example.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todolist.entities.UserEntity;
import com.example.todolist.repos.UserRepository;

@Service
public class UserService {
	
	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	public UserEntity saveOneUser(UserEntity newUser) {
		return userRepository.save(newUser);
	}

	public UserEntity getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public UserEntity updateOneUser(Long userId, UserEntity newUser) {
		Optional<UserEntity> user=userRepository.findById(userId);
		if(user.isPresent()) {
			UserEntity foundUser = user.get();
			foundUser.setUsername(newUser.getUsername());
			foundUser.setUserSurname(newUser.getUserSurname());
			foundUser.setPassword(newUser.getPassword());
			foundUser.setBio(newUser.getBio());
			foundUser.setEmail(newUser.getEmail());
			foundUser.setPhone(newUser.getPhone());
			userRepository.save(foundUser);
			return foundUser;
		}else {
			return null;
		}
	}

	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
		
	}
	
	
}
