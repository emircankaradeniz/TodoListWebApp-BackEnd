package com.example.todolist.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="gorevler")
public class gorevler {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="gorevId")
	private long gorevId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JsonIgnore
	UserEntity user;
	
	@Column(name="gorevAdi")
	private String gorevAdi;
	
	@Column(name="aciklama")
	private String aciklama;
	
	@Column(name="tarih")
	private String tarih;
	
	@Column(name="saat")
	private String saat;
	
	@Column(name="oncelik")
	private boolean oncelik;
	
	
	

	public gorevler() {
	
	}

	public gorevler( String gorevAdi, String aciklama, String tarih, String saat, boolean oncelik,UserEntity user) {
		this.gorevAdi = gorevAdi;
		this.aciklama = aciklama;
		this.tarih = tarih;
		this.saat = saat;
		this.oncelik = oncelik;
		this.user=user;
	}

	public long getGorev_id() {
		return gorevId;
	}

	public void setGorev_id(long gorev_id) {
		this.gorevId = gorev_id;
	}

	public String getGorev_adi() {
		return gorevAdi;
	}

	public void setGorev_adi(String gorev_adi) {
		this.gorevAdi = gorev_adi;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getSaat() {
		return saat;
	}

	public void setSaat(String saat) {
		this.saat = saat;
	}

	public boolean isOncelik() {
		return oncelik;
	}
	public void setOncelik(boolean oncelik) {
		this.oncelik = oncelik;
	}

	public void setUser(UserEntity user2) {
		this.user=user2;
		
	}
	public UserEntity getUser()
	{
		return user;
	}

	
}
