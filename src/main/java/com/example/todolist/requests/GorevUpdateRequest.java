package com.example.todolist.requests;

import lombok.Data;

@Data
public class GorevUpdateRequest {
	String gorev_adi;
	String aciklama;
	String tarih;
	String saat;
	boolean oncelik;
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
	
}
