package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tec.wsnomina.entity.MenuResponse;
import com.tec.wsnomina.services.MenuServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/tec/menu")
public class MenuController {

	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	@GetMapping("/all")
	public MenuResponse getMenu(HttpServletRequest request)
	{
		return menuServiceImpl.getMenu();
	}
	
}
