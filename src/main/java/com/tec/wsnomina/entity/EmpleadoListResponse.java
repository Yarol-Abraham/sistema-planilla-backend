package com.tec.wsnomina.entity;

import java.util.ArrayList;
import java.util.List;
import com.tec.wsnomina.dto.EmpleadoDto;

public class EmpleadoListResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<EmpleadoDto> empleados = new ArrayList<EmpleadoDto>();
	
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
	public List<EmpleadoDto> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<EmpleadoDto> empleados) {
		this.empleados = empleados;
	}
	
}
