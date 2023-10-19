package com.tec.wsnomina.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.RoleDto;
import com.tec.wsnomina.dto.UsuarioRoleDto;
import com.tec.wsnomina.entity.RoleEntity;
import com.tec.wsnomina.entity.RoleResponse;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.entity.UsuarioRoleEntity;
import com.tec.wsnomina.repository.IRoleRepository;
import com.tec.wsnomina.repository.IUsuarioRoleRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class UsuarioRoleServiceImpl implements UsuarioRoleService {

	@Autowired
	private IUsuarioRoleRepository iUsuarioRoleRepository;
	
	@Autowired
	private IRoleRepository iRoleRepository;
	
	@Autowired
	SessionServiceImpl sessionServiceImpl;
	
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	
	@Override
	public RoleResponse grantPermission(UsuarioRoleDto usuarioRoleDto, String sessionId) 
	{
		RoleResponse roleResponse = new RoleResponse();
		try
		{
			// VALIDAMOS PRIMERO LA SESION
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				roleResponse.setStrResponseCode(methods.GETERROR());
				roleResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return roleResponse;
			}
			
			// 1.0 validated fields
			if(usuarioRoleDto.getIdRole().equals("") || usuarioRoleDto.getIdUsuario().equals(""))
			{
				roleResponse.setStrResponseCode(methods.GETERROR());
				roleResponse.setStrResponseMessage("usuario o rol no válidos");
				return roleResponse;
			}
			usuarioRoleDto.setIdRole(this.utils.clean(usuarioRoleDto.getIdRole()));
			int idRole = this.utils.validNumber(usuarioRoleDto.getIdRole());
			
			Optional<RoleEntity> role = this.iRoleRepository.findById(idRole);
			
			if(role.isEmpty())
			{
				roleResponse.setStrResponseCode(methods.GETERROR());
				roleResponse.setStrResponseMessage("rol no válido");
				return roleResponse;
			}
			
			UsuarioRoleEntity usuarioRoleEntity = new UsuarioRoleEntity();
			
			usuarioRoleEntity.setIdUsuario(usuarioRoleDto.getIdUsuario());
			usuarioRoleEntity.setIdRole(usuarioRoleDto.getIdRole());
			usuarioRoleEntity.setFechaCreacion(this.utils.getFechaHoraFormateada());
			usuarioRoleEntity.setUsuarioCreacion(sessionInformationResponse.getStrIdUsuario());
			
			usuarioRoleEntity = this.iUsuarioRoleRepository.save(usuarioRoleEntity);
			
			RoleDto roleDto = new RoleDto(role.get().getIdRole(), role.get().getNombre());
			
			roleResponse.setStrResponseCode(methods.GETSUCCESS());
			roleResponse.setStrResponseMessage("permiso asignado");
			roleResponse.setRole(roleDto);
			
		}
		catch(Exception ex)
		{
			roleResponse.setStrResponseCode(methods.GETERROR());
			roleResponse.setStrResponseMessage("error, no se puede asignar permisos al usuarios");
		}
		return roleResponse;
	}

}
