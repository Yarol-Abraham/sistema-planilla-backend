package com.tec.wsnomina.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncrypt {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public String generateEncrypt(String password)
	{
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
	}
	
	public boolean CompareToPasswords(String hashedPasswordFromDB, String passwordFromUser)
	{
		return passwordEncoder.matches(passwordFromUser, hashedPasswordFromDB);
	}

}
