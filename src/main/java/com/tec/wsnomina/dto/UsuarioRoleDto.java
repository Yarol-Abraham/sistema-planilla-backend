package com.tec.wsnomina.dto;

public class UsuarioRoleDto {

	private String idUsuario;
	private String idRole;
	
	public UsuarioRoleDto(){}
	
	public UsuarioRoleDto(String idUsuario, String idRole) 
	{
		this.idUsuario = idUsuario;
		this.idRole = idRole;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	
	
}
