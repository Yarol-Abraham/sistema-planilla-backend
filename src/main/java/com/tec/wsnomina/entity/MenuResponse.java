package com.tec.wsnomina.entity;

import java.util.List;

import com.tec.wsnomina.dto.ModuloDto;

public class MenuResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<ModuloDto> entModulo;
	
	
	public List<ModuloDto> getEntModulo() {
		return entModulo;
	}
	public void setEntModulo(List<ModuloDto> entModulo) {
		this.entModulo = entModulo;
	}
	
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
	
}
