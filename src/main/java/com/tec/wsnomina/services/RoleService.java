package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.RoleDto;
import com.tec.wsnomina.entity.RoleListResponse;
import com.tec.wsnomina.entity.RoleResponse;

public interface RoleService {

	public RoleResponse createRole(RoleDto role, String sessionId);
	
	public RoleListResponse getList(String sessionId);
	
	public RoleResponse updateRole(RoleDto role, String sessionId);
	
	public RoleListResponse getUnassignedRoles(String idUsuario, String sessionId);
	
}
