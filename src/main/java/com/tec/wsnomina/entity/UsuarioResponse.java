package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.UsuarioDto;
import com.tec.wsnomina.dto.UsuarioSucursalDto;

public class UsuarioResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private UsuarioSucursalDto entUsuario = new UsuarioSucursalDto();
	
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
	public void setEntUsuario(UsuarioSucursalDto entUsuario) {
		this.entUsuario = entUsuario;
	}
	
}
