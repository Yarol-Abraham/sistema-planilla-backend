package com.tec.wsnomina.services;

import com.tec.wsnomina.entity.MenuResponse;

public interface ModuloService {
	
	public MenuResponse getMenu(String sessionId);
	
}
