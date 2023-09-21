package com.tec.wsnomina.dto;

public class OptionDto {
	
	private int idOpcion;
	private String nombre;
	private int ordenMenu;
	private String pagina;
	
	public OptionDto(int idOpcion, String nombre, int ordenMenu, String pagina)
	{
		this.idOpcion = idOpcion;
		this.nombre = nombre;
		this.ordenMenu = ordenMenu;
		this.pagina = pagina; 
	}

	public int getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(int idOpcion) {
		this.idOpcion = idOpcion;
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

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	
}
