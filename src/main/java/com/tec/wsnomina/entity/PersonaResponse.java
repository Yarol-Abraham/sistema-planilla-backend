package com.tec.wsnomina.entity;
 
import com.tec.wsnomina.dto.PersonaDto;

public class PersonaResponse {
	
	private String strResponseCode = "";
	private String strResponseMessage = "";
	private PersonaDto persona = new PersonaDto();
	
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
	public PersonaDto getPersona() {
		return persona;
	}
	public void setPersona(PersonaDto persona) {
		this.persona = persona;
	}
	
}
