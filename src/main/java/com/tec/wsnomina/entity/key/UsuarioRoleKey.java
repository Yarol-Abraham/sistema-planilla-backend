package com.tec.wsnomina.entity.key;

import java.io.Serializable;

public class UsuarioRoleKey  implements Serializable {
 
	private String idUsuario = "";
	private String idRole = "";
	
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
