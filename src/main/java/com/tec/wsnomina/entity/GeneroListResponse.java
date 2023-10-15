package com.tec.wsnomina.entity;

import java.util.ArrayList;
import java.util.List;

import com.tec.wsnomina.dto.GeneroDto;

public class GeneroListResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<GeneroDto> generos = new ArrayList<GeneroDto>();
	
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
	public List<GeneroDto> getGeneros() {
		return generos;
	}
	public void setGeneros(List<GeneroDto> generos) {
		this.generos = generos;
	}
	
	
	
	
}
