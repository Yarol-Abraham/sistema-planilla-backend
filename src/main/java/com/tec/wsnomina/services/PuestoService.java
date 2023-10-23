package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.PuestoCreateDto;
import com.tec.wsnomina.dto.PuestoDepartamentoDto;
import com.tec.wsnomina.entity.PuestoListDeparmentResponse;
import com.tec.wsnomina.entity.PuestoResponse;

public interface PuestoService {
		
	public PuestoListDeparmentResponse getPositions(int idDepartamento, String sessionId);
	
	public PuestoResponse createPosition(PuestoCreateDto puestoCreateDto, String sessionId);
	
    public PuestoResponse updatePosition(PuestoDepartamentoDto puestoCreateDto, String sessionId);
	
	
}
