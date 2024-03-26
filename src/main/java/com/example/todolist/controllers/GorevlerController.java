package com.example.todolist.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.todolist.entities.gorevler;
import com.example.todolist.repos.GorevRepository;

@CrossOrigin(origins="http://localhost:8081")
@RestController
@RequestMapping("/api")
public class GorevlerController {
	
	@Autowired
	GorevRepository gorevRepo;

	public GorevlerController() {
		
	}
	
	@GetMapping("/tasks")
	public ResponseEntity<List<gorevler>> getAllGorevler(@RequestParam(required = false) String gorevAdi){
		try {
			List<gorevler> list =new ArrayList<gorevler>();
			if(gorevAdi == null) {
				gorevRepo.findAll().forEach(list::add);
			}else {
				gorevRepo.findBygorevAdi(gorevAdi).forEach(list::add);
			}
			if(list.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(list,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/tasks/{gorev_id}")
	public ResponseEntity<gorevler> getGorevById(@PathVariable("gorev_id") long gorevId) {
		Optional<gorevler> gorevlerData=gorevRepo.findById(gorevId);
		if(gorevlerData.isPresent()) {
			return new ResponseEntity<>(gorevlerData.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/tasks")
	public ResponseEntity<gorevler> createOneGorev(@RequestBody gorevler  gorevbody) {
		try {
			gorevler gorev =gorevRepo
					.save(new gorevler(gorevbody.getGorev_adi(),gorevbody.getAciklama(),gorevbody.getTarih(),gorevbody.getSaat(),gorevbody.isOncelik(),gorevbody.getUser()));
			return new ResponseEntity<>(gorev,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/tasks/{gorev_id}")
	public ResponseEntity<gorevler> updateOneGorev(@PathVariable("gorev_id") long gorevId,@RequestBody gorevler gorevBody) {
		Optional<gorevler> gorevData=gorevRepo.findById(gorevId);
		if(gorevData.isPresent()) {
			gorevler gorev=gorevData.get();
			gorev.setGorev_adi(gorevBody.getGorev_adi());
			gorev.setAciklama(gorevBody.getAciklama());
			gorev.setSaat(gorevBody.getSaat());
			gorev.setTarih(gorevBody.getTarih());
			gorev.setOncelik(gorevBody.isOncelik());
			gorev.setUser(gorevBody.getUser());
			return new ResponseEntity<>(gorevRepo.save(gorev),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/tasks/{gorev_id}")
	public ResponseEntity<HttpStatus> deleteOneGorev(@PathVariable("gorev_id") long gorevId) {
		try {
			gorevRepo.deleteById(gorevId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
