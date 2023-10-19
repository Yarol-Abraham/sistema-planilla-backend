package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.UsuarioRoleDto;
import com.tec.wsnomina.entity.RoleResponse;

public interface UsuarioRoleService {

	public RoleResponse grantPermission(UsuarioRoleDto usuarioRoleDto, String sessionId);
	
}
