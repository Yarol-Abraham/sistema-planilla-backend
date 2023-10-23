package com.tec.wsnomina.entity;

import java.util.ArrayList;
import java.util.List;

import com.tec.wsnomina.dto.DepartamentoDto;

public class DepartamentoListResponse {
	
	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<DepartamentoDto> departamentos = new ArrayList<DepartamentoDto>();
	
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
	public List<DepartamentoDto> getDepartamentos() {
		return departamentos;
	}
	public void setDepartamentos(List<DepartamentoDto> departamentos) {
		this.departamentos = departamentos;
	}
	
	
	
}
