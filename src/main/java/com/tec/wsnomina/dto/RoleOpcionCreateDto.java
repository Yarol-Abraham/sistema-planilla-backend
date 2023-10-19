package com.tec.wsnomina.dto;

public class RoleOpcionCreateDto {

	private String idRole;
	private String idOpcion;
	private String alta;
	private String baja;
	private String cambio;
	private String imprimir;
	private String exportar;
	
	public RoleOpcionCreateDto(String idRole, String idOpcion, String alta, String baja, String cambio, String imprimir,String exportar) 
	{
		this.idRole = idRole;
		this.idOpcion = idOpcion;
		this.alta = alta;
		this.baja = baja;
		this.cambio = cambio;
		this.imprimir = imprimir;
		this.exportar = exportar;
	}

	public String getIdRole() {
		return idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public String getIdOpcion() {
		return idOpcion;
	}

	public void setIdOpcion(String idOpcion) {
		this.idOpcion = idOpcion;
	}

	public String getAlta() {
		return alta;
	}

	public void setAlta(String alta) {
		this.alta = alta;
	}

	public String getBaja() {
		return baja;
	}

	public void setBaja(String baja) {
		this.baja = baja;
	}

	public String getCambio() {
		return cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}

	public String getImprimir() {
		return imprimir;
	}

	public void setImprimir(String imprimir) {
		this.imprimir = imprimir;
	}

	public String getExportar() {
		return exportar;
	}

	public void setExportar(String exportar) {
		this.exportar = exportar;
	}
	
	
}
