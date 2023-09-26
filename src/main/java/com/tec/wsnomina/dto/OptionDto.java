package com.tec.wsnomina.dto;

public class OptionDto {
	
	private int idOpcion;
	private String nombre;
	private int ordenMenu;
	private String pagina;
	private int alta;
	private int baja;
	private int cambio;
	private int imprimir;
	private int exportar;

	public OptionDto(int idOpcion, String nombre, int ordenMenu, String pagina, int alta, int baja, int cambio, int imprimir, int exportar)
	{
		this.idOpcion = idOpcion;
		this.nombre = nombre;
		this.ordenMenu = ordenMenu;
		this.pagina = pagina; 
		this.alta = alta;
		this.baja = baja;
		this.cambio = cambio;
		this.imprimir = imprimir;
		this.exportar = exportar;
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
	public int getAlta() {
		return alta;
	}

	public void setAlta(int alta) {
		this.alta = alta;
	}
	
	public int getBaja() {
		return baja;
	}

	public void setBaja(int baja) {
		this.baja = baja;
	}

	public int getCambio() {
		return cambio;
	}

	public void setCambio(int cambio) {
		this.cambio = cambio;
	}

	public int getImprimir() {
		return imprimir;
	}

	public void setImprimir(int imprimir) {
		this.imprimir = imprimir;
	}

	public int getExportar() {
		return exportar;
	}

	public void setExportar(int exportar) {
		this.exportar = exportar;
	}
	
}
