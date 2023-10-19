package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.entity.MenuListResponse;
import com.tec.wsnomina.entity.MenuResponse;
import com.tec.wsnomina.services.MenuServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/tec/menu")
public class MenuController {

	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	@GetMapping("/{IdRole}")
	public MenuResponse getMenu(@PathVariable String IdRole)
	{
		return menuServiceImpl.getMenu(IdRole);
	}
	
	@GetMapping("/modulo/{IdModulo}")
	public MenuListResponse getListMenu(@PathVariable int IdModulo,  HttpServletRequest request)
	{
		return menuServiceImpl.getListMenu(IdModulo, request.getHeader("Authorization"));
	}
	
}
