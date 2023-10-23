package com.tec.wsnomina.dto;


public class PuestoDto {

    private int idPuesto;
    private String nombre;
    private int idDepartamento;
	public PuestoDto(int idPuesto, String nombre,int idDepartamento) 
	{
		this.idPuesto = idPuesto;
		this.nombre = nombre;
		this.idDepartamento = idDepartamento;
	}
	
	public int getIdPuesto() {
		return idPuesto;
	}
	public void setIdPuesto(int idPuesto) {
		this.idPuesto = idPuesto;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	
}
