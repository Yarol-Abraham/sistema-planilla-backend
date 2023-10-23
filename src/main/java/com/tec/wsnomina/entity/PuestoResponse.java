package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.PuestoDepartamentoDto;

public class PuestoResponse {
	
	private String strResponseCode = "";
	private String strResponseMessage = "";
	private PuestoDepartamentoDto puesto;
	
	public String getStrResponseCode() {
		return strResponseCode;
	}
	
	public void setStrResponseCode(String strResponseCode) {
		this.strResponseCode = strResponseCode;
	}
	
	public String getStrResponseMessage() {
		return strResponseMessage;
	}
	
	public void setStrResponseMessage(String strResponseMessage) {
		this.strResponseMessage = strResponseMessage;
	}
	
	public PuestoDepartamentoDto getPuesto() {
		return puesto;
	}
	
	public void setPuesto(PuestoDepartamentoDto puesto) {
		this.puesto = puesto;
	}

}
