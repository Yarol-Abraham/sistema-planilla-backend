package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.RoleOpcionCreateDto;
import com.tec.wsnomina.entity.RoleListOpcionResponse;
import com.tec.wsnomina.entity.RoleOpcionResponse;

public interface RoleOptionService {
	
	public RoleListOpcionResponse getUnassignedOptions(String idRole, String sessionId);
	
	public RoleOpcionResponse grantPermission(RoleOpcionCreateDto roleOpcionCreate, String sessionId);
	
}
