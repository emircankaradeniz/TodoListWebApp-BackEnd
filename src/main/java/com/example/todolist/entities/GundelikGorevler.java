package com.example.todolist.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Ggorevler")
@Data
public class GundelikGorevler {

	@Id
	@Column(name="Ggorev_id")
	private long Ggorev_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	UserEntity user;
	
	@Column(name="Ggorev_adi")
	private String Ggorev_adi;
	
	@Column(name="aciklama")
	private String aciklama;
	
	

	public GundelikGorevler() {

		
	}

	public GundelikGorevler(long ggorev_id, String ggorev_adi, String aciklama) {
		super();
		Ggorev_id = ggorev_id;
		Ggorev_adi = ggorev_adi;
		this.aciklama = aciklama;
	}

	public long getGgorev_id() {
		return Ggorev_id;
	}

	public void setGgorev_id(long ggorev_id) {
		Ggorev_id = ggorev_id;
	}

	public String getGgorev_adi() {
		return Ggorev_adi;
	}

	public void setGgorev_adi(String ggorev_adi) {
		Ggorev_adi = ggorev_adi;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public void setUser(UserEntity user2) {
		this.user=user2;
		
	}
	
	
}
