package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.dto.RoleDto;
import com.tec.wsnomina.entity.RoleListResponse;
import com.tec.wsnomina.entity.RoleResponse;
import com.tec.wsnomina.services.RoleServicelmpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/tec/role")
public class RoleController {

	@Autowired
	RoleServicelmpl roleServicelmpl;
	
	@PostMapping("/create")
	public RoleResponse createRole(@RequestBody RoleDto role, HttpServletRequest request)
	{
		return this.roleServicelmpl.createRole(role, request.getHeader("Authorization"));
	}
	
	@GetMapping("/list")
	public RoleListResponse getList( HttpServletRequest request)
	{
		return this.roleServicelmpl.getList(request.getHeader("Authorization"));
	}
	
	@GetMapping("/test")
	public RoleListResponse getPlantilla( HttpServletRequest request)
	{
		return new RoleListResponse();
	}
	
}
