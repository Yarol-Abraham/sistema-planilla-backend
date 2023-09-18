package com.tec.wsnomina.entity;

public class UsuarioResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private UsuarioEntity entUsuario = new UsuarioEntity();
	
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
	public UsuarioEntity getEntUsuario() {
		return entUsuario;
	}
	public void setEntUsuario(UsuarioEntity entUsuario) {
		this.entUsuario = entUsuario;
	}
	
}
