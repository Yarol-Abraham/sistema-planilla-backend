package com.tec.wsnomina.entity;

import com.tec.wsnomina.dto.EmpleadoDto;

public class EmpleadoResponse {
	
	private String strResponseCode = "";
	private String strResponseMessage = "";
	private EmpleadoDto empleado = new EmpleadoDto();
	
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
	public EmpleadoDto getEmpleado() {
		return empleado;
	}
	public void setEmpleado(EmpleadoDto empleado) {
		this.empleado = empleado;
	}
	
}
