package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.EmpleadoCreateDto;

public class EmpleadoResponse {
	
	private String strResponseCode = "";
	private String strResponseMessage = "";
	private EmpleadoCreateDto empleado = new EmpleadoCreateDto();
	
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
	public EmpleadoCreateDto getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadoCreateDto empleado) {
		this.empleado = empleado;
	}
	
}
