package com.example.todolist.requests;

import lombok.Data;

@Data
public class GunGorevCreateRequest {

	Long Ggorev_id;
	String Ggorev_adi;
	String aciklama;
	Long userId;
	public Long getUserId() {
		return Ggorev_id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getGgorev_id() {
		return Ggorev_id;
	}
	public void setGgorev_id(Long ggorev_id) {
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
	
}
