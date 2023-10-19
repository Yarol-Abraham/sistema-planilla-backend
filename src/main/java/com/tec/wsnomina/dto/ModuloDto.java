package com.tec.wsnomina.dto;

import java.util.List;

public class ModuloDto {

	private int idModulo;
	private String nombre;
	private int ordenMenu;
	private List<MenuOptionDto> menu;
	
	public ModuloDto(){}
	
	public  ModuloDto(int idModulo, String nombre, int ordenMenu, List<MenuOptionDto> menu)
	{
		this.idModulo = idModulo;
		this.nombre = nombre;
		this.ordenMenu = ordenMenu;
		this.menu = menu;
	}
	
	public int getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
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

	public List<MenuOptionDto> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuOptionDto> menu) {
		this.menu = menu;
	}
	
	
}
