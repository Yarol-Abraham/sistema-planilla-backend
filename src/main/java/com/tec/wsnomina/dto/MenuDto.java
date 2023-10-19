package com.tec.wsnomina.dto;

public class MenuDto {

	private int idModulo;
	private int idMenu;
	private String nombre;
	private int ordenMenu;
	
	public MenuDto(int idModulo, int idMenu, String nombre, int ordenMenu) {
		super();
		this.idModulo = idModulo;
		this.idMenu = idMenu;
		this.nombre = nombre;
		this.ordenMenu = ordenMenu;
	}

	public int getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getOrdenMenu() {
		return ordenMenu;
	}

	public void setOrdenMenu(int ordenMenu) {
		this.ordenMenu = ordenMenu;
	}
	
	
	
	
}
