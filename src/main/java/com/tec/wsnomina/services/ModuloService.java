package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.ModuloDto;
import com.tec.wsnomina.entity.ModuloResponse;

public interface ModuloService {
	
	public ModuloResponse createModulo(ModuloDto modulo, String sessionId);
	public ModuloResponse updateModulo(ModuloDto modulo, String sessionId);
	
}
