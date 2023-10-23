package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.PuestoDto;

import java.util.ArrayList;
import java.util.List;

public class PuestoListDeparmentResponse {
	
	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<PuestoDto> puestos = new ArrayList<PuestoDto>();
	
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
	public List<PuestoDto> getPuestos() {
		return puestos;
	}
	public void setPuestos(List<PuestoDto> puestos) {
		this.puestos = puestos;
	}
	
}
