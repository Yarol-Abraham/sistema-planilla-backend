package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.GeneroDto;

public class GeneroResponse {


	private String strResponseCode = "";
	private String strResponseMessage = "";
	private GeneroDto genero = new GeneroDto();
	
	public String getStrResponseCode() {
		return strResponseCode;
	}
	public void setStrResponseCode(String strResponseCode) {
		this.strResponseCode = strResponseCode;
	}
	public String getStrResponseMessage() {
		return strResponseMessage;
	}
	public void setStrResponseMessage(String strResponseMessage) {
		this.strResponseMessage = strResponseMessage;
	}
	public GeneroDto getGenero() {
		return genero;
	}
	public void setGenero(GeneroDto genero) {
		this.genero = genero;
	}
		
}
