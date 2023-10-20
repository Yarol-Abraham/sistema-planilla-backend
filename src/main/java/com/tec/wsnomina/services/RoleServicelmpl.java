package com.tec.wsnomina.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.RoleDto;
import com.tec.wsnomina.entity.RoleEntity;
import com.tec.wsnomina.entity.RoleListResponse;
import com.tec.wsnomina.entity.RoleResponse;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.repository.IRoleRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class RoleServicelmpl implements RoleService {

	@Autowired
	IRoleRepository iRoleRepository;

	@Autowired
	SessionServiceImpl sessionServiceImpl;
	
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	
	@Override
	public RoleResponse createRole(RoleDto role, String sessionId) 
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
			
			// clean values
			role.setNombre(utils.clean(role.getNombre()));
			
			if(role.getNombre().equals("") || role.getNombre() == null)
			{
				roleResponse.setStrResponseCode(methods.GETERROR());
				roleResponse.setStrResponseMessage("El nombre es requerido");
				return roleResponse;
			}
			
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setNombre(role.getNombre());
			roleEntity.setFechaCreacion(this.utils.getFechaHoraFormateada());
			roleEntity.setUsuarioCreacion(sessionInformationResponse.getStrIdUsuario());
			
			roleEntity = this.iRoleRepository.save(roleEntity);
			role.setIdRole(roleEntity.getIdRole());
			
			roleResponse.setStrResponseCode(this.methods.GETSUCCESS());
			roleResponse.setStrResponseMessage("ROL CREADO CON EXITO");
			roleResponse.setRole(role);
			
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: RoleServicelmpl.createRole() " + ex.getMessage());
			roleResponse.setStrResponseCode(methods.GETERROR());
			roleResponse.setStrResponseMessage("ERROR, ha ocurrido un error inesperado vuelve a intentarlo");
			return roleResponse;
		}
		return roleResponse;
	}

	@Override
	public RoleListResponse getList(String sessionId) {
		
		RoleListResponse roleListResponse = new RoleListResponse();
		
		try
		{
			// VALIDAMOS PRIMERO LA SESION
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				roleListResponse.setStrResponseCode(methods.GETERROR());
				roleListResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return roleListResponse;
			}
			
			List<RoleEntity> listRoleEntity = this.iRoleRepository.findAll();
			List<RoleDto> roleDto = new ArrayList<RoleDto>();
			
			for( RoleEntity role: listRoleEntity )
			{
				roleDto.add(new RoleDto(role.getIdRole(), role.getNombre()));
			}
			
			roleListResponse.setStrResponseCode(this.methods.GETSUCCESS());
			roleListResponse.setStrResponseMessage("SUCCESS");
			roleListResponse.setRoles(roleDto);
			
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: RoleServicelmpl.getList() " + ex.getMessage());
			roleListResponse.setStrResponseCode(methods.GETERROR());
			roleListResponse.setStrResponseMessage("ERROR, ha ocurrido un error inesperado vuelve a intentarlo");
		}
		return roleListResponse;
	}
	
	@Override
	public RoleResponse updateRole(RoleDto role, String sessionId) 
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
			
			role.setNombre(this.utils.clean(role.getNombre()));
			
			if(role.getNombre().isEmpty())
			{
				roleResponse.setStrResponseCode(methods.GETERROR());
				roleResponse.setStrResponseMessage("El nombre es requerido");
				return roleResponse;
			}
			
			if(role.getIdRole() <= 0)
			{
				roleResponse.setStrResponseCode(methods.GETERROR());
				roleResponse.setStrResponseMessage("No se puede identificar el rol seleccionado.");
				return roleResponse;
			}
			
			Optional<RoleEntity> roleEntity = this.iRoleRepository.findById(role.getIdRole());
			
			if(roleEntity.isEmpty())
			{
				roleResponse.setStrResponseCode(methods.GETERROR());
				roleResponse.setStrResponseMessage("Error, No se encontro el rol a editar.");
				return roleResponse;
			}
			
			roleEntity.get().setNombre(role.getNombre());
			roleEntity.get().setFechaModificacion(utils.getFechaHoraFormateada());
			roleEntity.get().setUsuarioModificacion(sessionInformationResponse.getStrNombre());
			this.iRoleRepository.save(roleEntity.get());
			
			roleResponse.setStrResponseCode(methods.GETSUCCESS());
			roleResponse.setStrResponseMessage("Exitoso, Datos guardados correctamente");
			roleResponse.setRole(role);
			return roleResponse;
			
		}
		catch(Exception ex)
		{
			System.out.println("Error en: RoleServicelmpl.updateRole() " + ex.getMessage());
			roleResponse.setStrResponseCode(methods.GETERROR());
			roleResponse.setStrResponseMessage("Error al intentar actualizar el rol, vuelve a intentarlo.");
		}
		
		return roleResponse;
	}
	
	@Override
	public RoleListResponse getUnassignedRoles(String idUsuario, String sessionId)
	{
		RoleListResponse roleResponse = new RoleListResponse();
		
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
			
			List<RoleDto> roles = this.iRoleRepository.obtenerRolesNoAsignadosAlUsuario(idUsuario)
									.stream()
									.map( role -> new RoleDto(role.getIdRole(), role.getNombre()) )
									.collect(Collectors.toList());
			
			roleResponse.setStrResponseCode(methods.GETSUCCESS());
			roleResponse.setStrResponseMessage("roles no asignados obtenidos correctamente");
			roleResponse.setRoles(roles);			
		}
		catch(Exception ex)
		{
			roleResponse.setStrResponseCode(methods.GETERROR());
			roleResponse.setStrResponseMessage("error, no se puede obtener los roles no asignados");
		}
		
		return roleResponse;
	}
	
	@Override
	public RoleListResponse getAssignedRoles(String idUsuario, String sessionId)
	{
		RoleListResponse roleResponse = new RoleListResponse();
		
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
			
			List<RoleDto> roles = this.iRoleRepository.obtenerRolesAsignadosAlUsuario(idUsuario)
									.stream()
									.map( role -> new RoleDto(role.getIdRole(), role.getNombre()) )
									.collect(Collectors.toList());
			
			roleResponse.setStrResponseCode(methods.GETSUCCESS());
			roleResponse.setStrResponseMessage("roles asignados obtenidos correctamente");
			roleResponse.setRoles(roles);			
		}
		catch(Exception ex)
		{
			roleResponse.setStrResponseCode(methods.GETERROR());
			roleResponse.setStrResponseMessage("error, no se puede obtener los roles asignados");
		}
		
		return roleResponse;
	}
	
}
