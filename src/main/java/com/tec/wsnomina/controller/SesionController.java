package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.entity.SessionCredentials;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.services.SessionServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class SesionController {

	
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	
	@PostMapping("/login")
	public SessionInformationResponse generateSessionByUser(@RequestBody SessionCredentials sessionCredentials)
	{
		return sessionServiceImpl.generateSessionByUser(sessionCredentials);
	}
	
}
