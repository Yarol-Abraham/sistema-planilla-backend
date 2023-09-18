package com.tec.wsnomina.services;

import com.tec.wsnomina.entity.UsuarioEntity;
import com.tec.wsnomina.entity.UsuarioResponse;

public interface UsuarioService {

	public UsuarioResponse createUser(UsuarioEntity usuarioEntity); // return '00' if is success, '-1' if is error
	
	public UsuarioResponse updateUser(UsuarioEntity usuarioEntity);  // return '00' if is success, '-1' if is error
	
	public UsuarioResponse deleteUser(String IdUsuario); 
	
}
