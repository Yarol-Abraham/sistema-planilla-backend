package com.tec.wsnomina.entity;

import java.util.ArrayList;
import java.util.List;

import com.tec.wsnomina.dto.PersonaDto;

public class PersonaResponse {

	private String strResponseCode = "";
	private String strResponseMessage = "";
	private List<PersonaDto> personas = new ArrayList<PersonaDto>();
	
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
	public List<PersonaDto> getPersonas() {
		return personas;
	}
	public void setPersonas(List<PersonaDto> personas) {
		this.personas = personas;
	}
	
			
	
}
