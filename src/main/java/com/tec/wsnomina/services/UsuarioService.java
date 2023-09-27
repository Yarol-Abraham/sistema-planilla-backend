package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.UsuarioCreateDto;
import com.tec.wsnomina.entity.InformationResponse;
import com.tec.wsnomina.entity.ListUsuarioResponse;
import com.tec.wsnomina.entity.UsuarioResponse;

public interface UsuarioService {

	public UsuarioResponse createUser(UsuarioCreateDto usuarioEntity, String sessionId);
	
	public UsuarioResponse updateUser(UsuarioCreateDto usuarioEntity, String sessionId);
	
	public UsuarioResponse deleteUser(String IdUsuario, String sessionId); 
	
	public InformationResponse getUser(String sessionId);
	
	public ListUsuarioResponse getUsers(String sessionId);
	
}
