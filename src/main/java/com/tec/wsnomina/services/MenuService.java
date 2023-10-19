package com.tec.wsnomina.services;

import com.tec.wsnomina.entity.MenuListResponse;
import com.tec.wsnomina.entity.MenuResponse;

public interface MenuService {

	public MenuResponse getMenu(String idRole);
	
	public MenuListResponse getListMenu(int idModulo, String sessionId);
}
