package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.dto.UsuarioCreateDto;
import com.tec.wsnomina.dto.UsuarioSucursalDto;
import com.tec.wsnomina.entity.InformationResponse;
import com.tec.wsnomina.entity.ListUsuarioResponse;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.entity.UsuarioResponse;
import com.tec.wsnomina.services.SessionServiceImpl;
import com.tec.wsnomina.services.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/tec/user")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired 
	private SessionServiceImpl sessionServiceImpl;
	
	@PostMapping("/create")
	public UsuarioResponse create(@RequestBody UsuarioCreateDto usuarioEntity, HttpServletRequest request)
	{
		System.out.println("body: " + usuarioEntity);
		return this.usuarioServiceImpl.createUser(usuarioEntity, request.getHeader("Authorization"));
	}
	
	@PostMapping("/update")
	public UsuarioResponse update(@RequestBody UsuarioCreateDto usuarioEntity, HttpServletRequest request)
	{
		return this.usuarioServiceImpl.updateUser(usuarioEntity, request.getHeader("Authorization"));
	}
	
	@DeleteMapping("/delete/{IdUsuario}")
	public UsuarioResponse delete(@PathVariable String IdUsuario, HttpServletRequest request)
	{
		return this.usuarioServiceImpl.deleteUser(IdUsuario, request.getHeader("Authorization"));
	}
	
	@GetMapping("/information")
	public SessionInformationResponse information(HttpServletRequest request)
	{
		return this.sessionServiceImpl.getByInformationUserSesion(request.getHeader("Authorization"));
	}
	
	@GetMapping("/perfil")
	public InformationResponse getuser(HttpServletRequest request)
	{
		return this.usuarioServiceImpl.getUser(request.getHeader("Authorization"));
	}
	
	@GetMapping("/list")
	public ListUsuarioResponse getusers(HttpServletRequest request)
	{
		return this.usuarioServiceImpl.getUsers(request.getHeader("Authorization"));
	}
	
	@GetMapping("/plantilla")
	public UsuarioSucursalDto getplantilla(HttpServletRequest request)
	{
		return new UsuarioSucursalDto();
	}
	
	
	
}
