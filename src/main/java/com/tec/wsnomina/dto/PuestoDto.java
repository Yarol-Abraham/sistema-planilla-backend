package com.tec.wsnomina.dto;


public class PuestoDto {

    private int idPuesto;
    private String nombre;
    
	public PuestoDto(int idPuesto, String nombre) {
		this.idPuesto = idPuesto;
		this.nombre = nombre;
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
    
}
