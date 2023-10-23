package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.entity.DepartamentoListResponse;
import com.tec.wsnomina.services.DepartamentoServicelmpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/tec/department")
public class DepartamentoController {

	@Autowired
	private DepartamentoServicelmpl departamentoServicelmpl;
	
	@GetMapping("/list")
	public DepartamentoListResponse getDeparments(HttpServletRequest request)
	{
		return this.departamentoServicelmpl.getDeparments(request.getHeader("Authorization"));
	}
	
}
