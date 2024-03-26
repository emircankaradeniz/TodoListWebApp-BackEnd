package com.example.todolist.requests;

import lombok.Data;

@Data
public class GorevCreateRequest {
	
	Long gorev_id;
	String gorev_adi;
	String aciklama;
	String tarih;
	String saat;
	boolean oncelik;
	Long userId;
	public Long getUserId() {
		return gorev_id;
	}
	public Long getGorev_id() {
		return gorev_id;
	}
	public void setGorev_id(Long gorev_id) {
		this.gorev_id = gorev_id;
	}
	public String getGorev_adi() {
		return gorev_adi;
	}
	public void setGorev_adi(String gorev_adi) {
		this.gorev_adi = gorev_adi;
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
	public boolean getOncelik() {
		return oncelik;
	}
	public void setOncelik(boolean oncelik) {
		this.oncelik = oncelik;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
