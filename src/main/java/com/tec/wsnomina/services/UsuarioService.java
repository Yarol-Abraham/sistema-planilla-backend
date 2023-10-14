package com.tec.wsnomina.services;

import com.tec.wsnomina.dto.UsuarioCreateDto;
import com.tec.wsnomina.entity.InformationResponse;
import com.tec.wsnomina.entity.ListUsuarioResponse;
import com.tec.wsnomina.entity.SessionChangePassword;
import com.tec.wsnomina.entity.UsuarioResponse;

public interface UsuarioService {

	public UsuarioResponse createUser(UsuarioCreateDto usuarioEntity, String sessionId);
	
	public UsuarioResponse updateUser(UsuarioCreateDto usuarioEntity, String sessionId);
	
	public UsuarioResponse toUpOrDownUser(String IdUsuario, String sessionId, int OPCION); 
	
	public InformationResponse getUser(String sessionId);
	
	public ListUsuarioResponse getUsers(String sessionId);
	
	public UsuarioResponse updatePassword(SessionChangePassword sessionCredentials, String sessionId);
}
