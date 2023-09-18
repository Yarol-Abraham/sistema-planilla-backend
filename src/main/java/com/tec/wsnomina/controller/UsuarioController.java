package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.entity.UsuarioEntity;
import com.tec.wsnomina.entity.UsuarioResponse;
import com.tec.wsnomina.services.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/tec/user")
public class UsuarioController {

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@PostMapping("/create")
	public UsuarioResponse create(@RequestBody UsuarioEntity usuarioEntity, HttpServletRequest request)
	{
		return this.usuarioServiceImpl.createUser(usuarioEntity);
	}
	
	@PostMapping("/update")
	public UsuarioResponse update(@RequestBody UsuarioEntity usuarioEntity)
	{
		return this.usuarioServiceImpl.updateUser(usuarioEntity);
	}
	
	@DeleteMapping("/delete/{IdUsuario}")
	public UsuarioResponse delete(@PathVariable String IdUsuario)
	{
		return this.usuarioServiceImpl.deleteUser(IdUsuario);
	}
	
	@GetMapping("/plantilla")
	public UsuarioEntity plantilla()
	{
		return new UsuarioEntity();
	}
	
}
