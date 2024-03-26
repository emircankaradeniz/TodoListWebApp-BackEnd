package com.example.todolist.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.entities.GundelikGorevler;
import com.example.todolist.requests.GunGorevCreateRequest;
import com.example.todolist.requests.GunGorevUpdateRequest;
import com.example.todolist.services.GunGorevService;

@RestController
@RequestMapping("/Gündelik-Görevler")
public class GunlukGorevlerController {

	private GunGorevService Gservice;
	
	public GunlukGorevlerController(GunGorevService Gservice) {
		this.Gservice=Gservice;
	}
	
	@GetMapping
	public List<GundelikGorevler> getAllGGorev(@RequestParam Optional<Long> userId){
		return Gservice.getAllWithParam(userId);
	}
	@GetMapping("/{Ggorev_id}")
	public GundelikGorevler getOneGorev(@PathVariable Long Ggorev_id) {
		return Gservice.getOneGorevById(Ggorev_id);
	}
	@PostMapping
	public GundelikGorevler createOneGorev(@RequestBody GunGorevCreateRequest request) {
		return Gservice.createOneGorev(request);
	}
	@PutMapping("/{Ggorev_id}")
	public GundelikGorevler updateOneGorev(@PathVariable Long Ggorev_id,@RequestBody GunGorevUpdateRequest request) {
		return Gservice.updateOneGorevById(Ggorev_id,request);
	}
	@DeleteMapping("/{Ggorev_id}")
	public void deleteOneGorev(@PathVariable Long Ggorev_id) {
		Gservice.deleteOneGorevById(Ggorev_id);
	}
}
