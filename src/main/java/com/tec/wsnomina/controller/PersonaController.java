package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.dto.PersonaDto;
import com.tec.wsnomina.entity.PersonaListResponse;
import com.tec.wsnomina.entity.PersonaResponse;
import com.tec.wsnomina.services.PersonaServicelmpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/tec/people")
public class PersonaController {

	@Autowired
	private PersonaServicelmpl personaServicelmpl;
	
	@GetMapping("/list")
	public PersonaListResponse getList(HttpServletRequest request)
	{
		return this.personaServicelmpl.getPersons(request.getHeader("Authorization"));
	}
	
	@PostMapping("/create")
	public PersonaResponse create(@RequestBody PersonaDto persona, HttpServletRequest request)
	{
		return this.personaServicelmpl.createPeople(persona, request.getHeader("Authorization"));
	}
	
	@PutMapping("/update")
	public PersonaResponse update(@RequestBody PersonaDto persona, HttpServletRequest request)
	{
		return this.personaServicelmpl.updatePeople(persona, request.getHeader("Authorization"));
	}
}
