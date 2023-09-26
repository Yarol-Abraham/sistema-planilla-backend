package com.tec.wsnomina.entity;

import java.util.ArrayList;
import java.util.List;

import com.tec.wsnomina.dto.UsuarioCreateDto;

public class ListUsuarioResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<UsuarioCreateDto> usuarios = new ArrayList<UsuarioCreateDto>();
	
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
	public List<UsuarioCreateDto> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioCreateDto> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
