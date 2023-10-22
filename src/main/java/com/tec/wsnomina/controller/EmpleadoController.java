package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.dto.EmpleadoCreateDto;
import com.tec.wsnomina.entity.EmpleadoListResponse;
import com.tec.wsnomina.entity.EmpleadoResponse;
import com.tec.wsnomina.services.EmpleadoServicelmpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/tec/employee")
public class EmpleadoController {

	@Autowired
	private EmpleadoServicelmpl empleadoServicelmpl;
	
	@GetMapping("/list")
	public EmpleadoListResponse getList(HttpServletRequest request)
	{
		return this.empleadoServicelmpl.getEmpleados(request.getHeader("Authorization"));
	}
	
	@PostMapping("/create")
	public EmpleadoResponse create(@RequestBody EmpleadoCreateDto empleado, HttpServletRequest request)
	{
		return this.empleadoServicelmpl.createEmpleado(empleado, request.getHeader("Authorization"));
	}
	
	@PutMapping("/update")
	public EmpleadoResponse update(@RequestBody EmpleadoCreateDto empleado, HttpServletRequest request)
	{
		return this.empleadoServicelmpl.updateEmpleado(empleado,  request.getHeader("Authorization"));
	}
	
}
