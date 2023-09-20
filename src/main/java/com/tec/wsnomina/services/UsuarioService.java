package com.tec.wsnomina.services;

import com.tec.wsnomina.entity.UsuarioEntity;
import com.tec.wsnomina.entity.UsuarioResponse;

public interface UsuarioService {

	public UsuarioResponse createUser(UsuarioEntity usuarioEntity, String sessionId);
	
	public UsuarioResponse updateUser(UsuarioEntity usuarioEntity, String sessionId);
	
	public UsuarioResponse deleteUser(String IdUsuario, String sessionId); 
	
}
