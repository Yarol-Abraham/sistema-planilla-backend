package com.tec.wsnomina.dto;

public class SucursalDto {

	private int idSucursal;
	private String nombre;
	
	
	
	public SucursalDto(int idSucursal, String nombre) {
	
		this.idSucursal = idSucursal;
		this.nombre = nombre;
	}
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
