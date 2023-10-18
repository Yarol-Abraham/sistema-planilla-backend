package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.dto.ModuloDto;
import com.tec.wsnomina.entity.ModuloResponse;
import com.tec.wsnomina.services.ModuloServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("tec/modulo")
public class ModulosController {

	@Autowired
	private ModuloServiceImpl moduloServiceImpl;
	
	@PostMapping("/create")
	public ModuloResponse create(@RequestBody ModuloDto moduloDto, HttpServletRequest request)
	{
		return this.moduloServiceImpl.createModulo(moduloDto, request.getHeader("Authorization"));
	}
	
	@PostMapping("/update")
	public ModuloResponse update(@RequestBody ModuloDto moduloDto, HttpServletRequest request)
	{
		return this.moduloServiceImpl.updateModulo(moduloDto, request.getHeader("Authorization"));
	}
	
}
