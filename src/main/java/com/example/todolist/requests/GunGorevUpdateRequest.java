package com.example.todolist.requests;

import lombok.Data;

@Data
public class GunGorevUpdateRequest {

	String Ggorev_adi;
	String aciklama;
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
