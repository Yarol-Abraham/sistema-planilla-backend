package com.tec.wsnomina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tec.wsnomina.dto.EmailBodyDto;
import com.tec.wsnomina.entity.EmailResponse;
import com.tec.wsnomina.services.EmailService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/tec/email")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@GetMapping("/recovery/password")
	public EmailResponse send(@RequestParam String email, HttpServletRequest request)
	{
		System.out.println("email: " + email);
		return emailService.sendEmail(email);
	}
	
	@PostMapping("/alert/newpassword")
	public EmailResponse alert(@RequestParam String token, HttpServletRequest request)
	{
		return emailService.verifyPassword(token);
	}
	
	@PostMapping("/confirm/newpassword")
	public EmailResponse confirm(@RequestParam String token, HttpServletRequest request)
	{
		return emailService.confirmNewPassword(token);
	}
	
}
