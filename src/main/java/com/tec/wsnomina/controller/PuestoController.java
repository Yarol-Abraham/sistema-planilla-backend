package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.dto.PuestoCreateDto;
import com.tec.wsnomina.dto.PuestoDto;
import com.tec.wsnomina.entity.PuestoListDeparmentResponse;
import com.tec.wsnomina.entity.PuestoResponse;
import com.tec.wsnomina.services.PuestoServicelmpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/tec/position")
public class PuestoController {

	@Autowired
	private PuestoServicelmpl puestoServicelmpl;
	
	@GetMapping("/list/{IdDepartamento}")
	public PuestoListDeparmentResponse getListByDepartamento(@PathVariable int IdDepartamento, HttpServletRequest request)
	{
		return this.puestoServicelmpl.getPositions(IdDepartamento, request.getHeader("Authorization"));
	}
	
	@PostMapping("/create")
	public  PuestoResponse createPosition(@RequestBody PuestoCreateDto puestoCreateDto,  HttpServletRequest request)
	{
		return this.puestoServicelmpl.createPosition(puestoCreateDto,  request.getHeader("Authorization"));
	}
	
	@PutMapping("/update")
	public  PuestoResponse updatePosition(@RequestBody PuestoDto puestoCreateDto,  HttpServletRequest request)
	{
		return this.puestoServicelmpl.updatePosition(puestoCreateDto, request.getHeader("Authorization"));
	}
	
}
