package com.tec.wsnomina.entity;

import java.util.ArrayList;
import java.util.List;

import com.tec.wsnomina.dto.UsuarioSucursalDto;

public class ListUsuarioResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<UsuarioSucursalDto> usuarios = new ArrayList<UsuarioSucursalDto>();
	
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
	public List<UsuarioSucursalDto> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioSucursalDto> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
