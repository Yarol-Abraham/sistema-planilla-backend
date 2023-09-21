package com.tec.wsnomina.dto;

import java.util.List;

public class MenuDto {

	private int idMenu;
	private String nombre;
	private int ordenMenu;
	private List<OptionDto> opciones;
	
	public MenuDto(int idMenu, String nombre, int ordenMenu, List<OptionDto> opciones)
	{
		this.idMenu = idMenu;
		this.nombre = nombre;
		this.ordenMenu = ordenMenu;
		this.opciones = opciones;
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

	public List<OptionDto> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<OptionDto> opciones) {
		this.opciones = opciones;
	}
	
}
