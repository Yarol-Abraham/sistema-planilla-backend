package com.tec.wsnomina.services;

import com.tec.wsnomina.entity.EmailResponse;

public interface EmailPort {

	public EmailResponse sendEmail(String email);
	
	public EmailResponse verifyPassword(String token);
	
	public EmailResponse confirmNewPassword(String token);
}
