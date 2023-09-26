package com.tec.wsnomina.dto;

public class StatusRoleOpcionDto {

	private int alta;
	private int baja;
	private int cambio;
	private int imprimir;
	private int exportar;
	
	public StatusRoleOpcionDto(int alta, int baja, int cambio, int imprimir, int exportar) {
		super();
		this.alta = alta;
		this.baja = baja;
		this.cambio = cambio;
		this.imprimir = imprimir;
		this.exportar = exportar;
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
