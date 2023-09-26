package com.tec.wsnomina.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
		HttpServletRequest request, 
		HttpServletResponse response, 
		FilterChain filterChain
	) throws ServletException, IOException 
	{
		System.out.println("request " + request.getHeader("Authorization"));
		
		String bearerToken = request.getHeader("Authorization");
		
		if(bearerToken != null && bearerToken.startsWith("Bearer "))
		{
			String token = bearerToken.replace("Bearer ", "");
			UsernamePasswordAuthenticationToken userToken = GenerateToken.usernamePasswordAuthenticationToken(token);
			SecurityContextHolder.getContext().setAuthentication(userToken);
		} 
		

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

		
		filterChain.doFilter(request, response);
	}

}
