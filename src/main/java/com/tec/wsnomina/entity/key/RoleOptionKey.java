package com.tec.wsnomina.entity.key;

import java.io.Serializable;

import jakarta.persistence.Embeddable;


@Embeddable
public class RoleOptionKey implements Serializable {
	
	private int idRole;
	private int idOpcion;
	
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public int getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
	}
	
}
