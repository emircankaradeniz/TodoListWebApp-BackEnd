package com.example.todolist.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todolist.entities.UserEntity;
import com.example.todolist.entities.gorevler;
import com.example.todolist.repos.GorevRepository;
import com.example.todolist.requests.GorevCreateRequest;
import com.example.todolist.requests.GorevUpdateRequest;

@Service
public class GorevlerService {
	
	private GorevRepository gorevRepository;
	private UserService userService;
	public GorevlerService(GorevRepository gorevRepository,UserService userService) {
		this.gorevRepository = gorevRepository;
		this.userService=userService;
	}

	public List<gorevler> getAllGorevler(Optional<Long> userId) {
		if(userId.isPresent()) 
			return gorevRepository.findByUserId(userId.get());
		return gorevRepository.findAll();
	}

	public gorevler createOneGorev(GorevCreateRequest newGorevRequest) {
		UserEntity user =userService.getOneUserById(newGorevRequest.getUserId());
		if(user==null)
			return null;
		gorevler toSave=new gorevler();
		toSave.setGorev_id(newGorevRequest.getGorev_id());
		toSave.setGorev_adi(newGorevRequest.getGorev_adi());
		toSave.setAciklama(newGorevRequest.getAciklama());
		toSave.setTarih(newGorevRequest.getTarih());
		toSave.setSaat(newGorevRequest.getSaat());
		toSave.setOncelik(newGorevRequest.getOncelik());
		toSave.setUser(user);
		return gorevRepository.save(toSave);
	}

	public gorevler getOneGorevById(Long gorev_id) {
		return gorevRepository.findById(gorev_id).orElse(null);
	}

	public gorevler updateOneGorevById(Long gorev_id,GorevUpdateRequest updategorev) {
		Optional<gorevler> gorev  = gorevRepository.findById(gorev_id);
		if(gorev.isPresent()){
			gorevler toUpdate =gorev.get();
			toUpdate.setGorev_adi(updategorev.getGorev_adi());
			toUpdate.setAciklama(updategorev.getAciklama());
			toUpdate.setTarih(updategorev.getTarih());
			toUpdate.setSaat(updategorev.getSaat());
			toUpdate.setOncelik(updategorev.getOncelik());
			gorevRepository.save(toUpdate);
		}
		return null;
	}

	public void deleteOneGorevById(Long gorev_id) {
		gorevRepository.deleteById(gorev_id);
	}
	
	
}
