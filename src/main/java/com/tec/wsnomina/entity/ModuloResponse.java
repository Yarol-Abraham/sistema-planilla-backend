package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.ModuloDto;

public class ModuloResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private ModuloDto modulo = new ModuloDto();
	
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
	public ModuloDto getModulo() {
		return modulo;
	}
	public void setModulo(ModuloDto modulo) {
		this.modulo = modulo;
	}
	
			
	
}
