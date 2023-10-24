package com.tec.wsnomina.services;

import java.util.List;

import com.tec.wsnomina.dto.GeneroDto;
import com.tec.wsnomina.entity.GeneroResponse;

public interface GeneroService {
	
	public List<GeneroDto> getGender(String sessionId);
	public GeneroResponse createGender(GeneroDto generoDto, String sessionId);
	public GeneroResponse updateGender(GeneroDto generoDto, String sessionId);

}
