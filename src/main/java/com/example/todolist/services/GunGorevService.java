package com.example.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todolist.entities.GundelikGorevler;
import com.example.todolist.entities.UserEntity;
import com.example.todolist.repos.GorevGunRepo;
import com.example.todolist.requests.GunGorevCreateRequest;
import com.example.todolist.requests.GunGorevUpdateRequest;

@Service
public class GunGorevService {
	
	private GorevGunRepo GorevRepo;
	private UserService userService;
	
	public GunGorevService(GorevGunRepo GorevRepo,UserService userService) {
		this.GorevRepo=GorevRepo;
		this.userService=userService;
	}

	public List<GundelikGorevler> getAllWithParam(Optional<Long> userId) {
		if(userId.isPresent()) {
			return GorevRepo.findByUserId(userId.get());
		}else {
			return null;
		}
	}

	public GundelikGorevler getOneGorevById(Long ggorev_id) {
		return GorevRepo.findById(ggorev_id).orElse(null);
	}

	public GundelikGorevler createOneGorev(GunGorevCreateRequest request) {
		UserEntity user= userService.getOneUserById(request.getUserId());
		if(user !=null) {
			GundelikGorevler gorevToSave =new GundelikGorevler();
			gorevToSave.setGgorev_id(request.getGgorev_id());
			gorevToSave.setUser(user);
			gorevToSave.setGgorev_adi(request.getGgorev_adi());
			gorevToSave.setAciklama(request.getAciklama());
			return GorevRepo.save(gorevToSave);
		}else {
			return null;
		}
		
	}

	public GundelikGorevler updateOneGorevById(Long ggorev_id, GunGorevUpdateRequest request) {
		Optional<GundelikGorevler> gorev= GorevRepo.findById(ggorev_id);
		if(gorev.isPresent()) {
			GundelikGorevler gorevToUp =new GundelikGorevler();
			gorevToUp.setGgorev_adi(request.getGgorev_adi());
			gorevToUp.setAciklama(request.getAciklama());
			return GorevRepo.save(gorevToUp);
		}else {
			return null;
		}
	}

	public void deleteOneGorevById(Long ggorev_id) {
		GorevRepo.deleteById(ggorev_id);
	}
}
