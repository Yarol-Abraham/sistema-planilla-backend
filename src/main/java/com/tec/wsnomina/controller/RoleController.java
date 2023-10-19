package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.dto.RoleDto;
import com.tec.wsnomina.dto.UsuarioRoleDto;
import com.tec.wsnomina.entity.RoleListResponse;
import com.tec.wsnomina.entity.RoleResponse;
import com.tec.wsnomina.services.RoleServicelmpl;
import com.tec.wsnomina.services.UsuarioRoleServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/tec/role")
public class RoleController {

	@Autowired
	RoleServicelmpl roleServicelmpl;
	
	@Autowired
	UsuarioRoleServiceImpl usuarioRoleServiceImpl;
	
	@PostMapping("/create")
	public RoleResponse createRole(@RequestBody RoleDto role, HttpServletRequest request)
	{
		return this.roleServicelmpl.createRole(role, request.getHeader("Authorization"));
	}
	
	@PostMapping("/update")
	public RoleResponse updateRole(@RequestBody RoleDto role, HttpServletRequest request)
	{
		return this.roleServicelmpl.updateRole(role, request.getHeader("Authorization"));
	}
	
	@GetMapping("/list")
	public RoleListResponse getList( HttpServletRequest request)
	{
		return this.roleServicelmpl.getList(request.getHeader("Authorization"));
	}
	
	@GetMapping("/list/unassigned/{idUsuario}")
	public RoleListResponse getListNotAssing(@PathVariable String idUsuario, HttpServletRequest request)
	{
		return this.roleServicelmpl.getUnassignedRoles(idUsuario, request.getHeader("Authorization"));
	}
	
	@PostMapping("/assign")
	public RoleResponse assignRole(@RequestBody UsuarioRoleDto usuarioRoleDto, HttpServletRequest request)
	{
		return this.usuarioRoleServiceImpl.grantPermission(usuarioRoleDto,  request.getHeader("Authorization"));
	}
	
	@GetMapping("/test")
	public RoleListResponse getPlantilla( HttpServletRequest request)
	{
		return new RoleListResponse();
	}
	
}
