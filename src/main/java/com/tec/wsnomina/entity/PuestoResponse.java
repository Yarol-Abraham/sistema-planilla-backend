package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.PuestoDto;

public class PuestoResponse {
	
	private String strResponseCode = "";
	private String strResponseMessage = "";
	private PuestoDto puesto;
	
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
	
	public PuestoDto getPuesto() {
		return puesto;
	}
	
	public void setPuesto(PuestoDto puesto) {
		this.puesto = puesto;
	}

}
