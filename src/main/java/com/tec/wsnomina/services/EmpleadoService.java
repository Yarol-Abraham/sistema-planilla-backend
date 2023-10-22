package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.EmpleadoCreateDto;
import com.tec.wsnomina.entity.EmpleadoListResponse;
import com.tec.wsnomina.entity.EmpleadoResponse;

public interface EmpleadoService {

	public EmpleadoListResponse getEmpleados(String sessionId);
	
	public EmpleadoResponse createEmpleado(EmpleadoCreateDto empleado, String sessionId);
	
	public EmpleadoResponse updateEmpleado(EmpleadoCreateDto empleado, String sessionId);
	
}
