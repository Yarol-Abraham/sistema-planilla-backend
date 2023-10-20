package com.tec.wsnomina.services;

import java.util.List;

import com.tec.wsnomina.dto.UsuarioRoleDto;
import com.tec.wsnomina.entity.RoleResponse;

public interface UsuarioRoleService {

	public RoleResponse grantPermission(List<UsuarioRoleDto> usuarioRoleDtos, String type, String sessionId);
	
}
