package com.tec.wsnomina.dto;

public class RoleDto {
	
	private int idRole;
	private String nombre;
	
	public RoleDto() {}
	
	public RoleDto(int idRole, String nombre)
	{
		this.idRole = idRole;
		this.nombre = nombre;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
