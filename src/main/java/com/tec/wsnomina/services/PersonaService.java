package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.PersonaDto;
import com.tec.wsnomina.entity.PersonaListResponse;
import com.tec.wsnomina.entity.PersonaResponse;

public interface PersonaService {
	
	public PersonaListResponse getPersons(String sessionId);
	
	public PersonaResponse createPeople(PersonaDto personaDto, String sessionId);
	
	public PersonaResponse updatePeople(PersonaDto personaDto, String sessionId);
}
