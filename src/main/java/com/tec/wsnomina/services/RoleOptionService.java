package com.tec.wsnomina.services;

import java.util.List;

import com.tec.wsnomina.dto.RoleOpcionCreateDto;
import com.tec.wsnomina.entity.RoleListOpcionResponse;
import com.tec.wsnomina.entity.RoleOpcionResponse;

public interface RoleOptionService {
	
	public RoleListOpcionResponse getUnassignedOptions(String idRole, String sessionId);
	
	public RoleListOpcionResponse getAssignedOptions(String idRole, String sessionId);
	
	public RoleOpcionResponse grantPermission(List<RoleOpcionCreateDto> roleOpcionCreates, String type, String sessionId);
	
}
