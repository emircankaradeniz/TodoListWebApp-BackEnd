package com.example.todolist.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entities.UserEntity;
import com.example.todolist.services.UserService;

@RestController
@RequestMapping("/profile")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService){
		this.userService=userService;
	}
	
	@GetMapping
	public List<UserEntity> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping
	public UserEntity createUser(@RequestBody UserEntity newUser) {
		return userService.saveOneUser(newUser);
	}
	
	@GetMapping("/{userId}")
	public UserEntity getOneUser(@PathVariable Long userId) {
		return userService.getOneUserById(userId);
	}
	
	@PutMapping("/{userId}")
	public UserEntity updateOneUser(@PathVariable Long userId,@RequestBody UserEntity newUser) {
		return userService.updateOneUser(userId,newUser);
	}
	@DeleteMapping("/{userId}")
	public void deleteOneUser(@PathVariable Long userId) {
		userService.deleteById(userId);
	}
}
