package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.UsuarioDto;

public class InformationResponse {
	

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private UsuarioDto entUsuario = new UsuarioDto();
	
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
	public UsuarioDto getEntUsuario() {
		return entUsuario;
	}
	public void setEntUsuario(UsuarioDto entUsuario) {
		this.entUsuario = entUsuario;
	}
	
	
}
