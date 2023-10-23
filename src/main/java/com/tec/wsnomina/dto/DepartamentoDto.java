package com.tec.wsnomina.dto;

public class DepartamentoDto {

	private int idDepartamento;
	private String nombre;
	
	public DepartamentoDto(int idDepartamento, String nombre) 
	{
		this.idDepartamento = idDepartamento;
		this.nombre = nombre;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
