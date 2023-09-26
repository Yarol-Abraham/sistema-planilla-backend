package com.tec.wsnomina.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class WebSecurityConfig {

	private final UserDetailsService userDetailsService;
	private final JWTAuthorizationFilter jwtAuthorizationFilter; 
	
	public WebSecurityConfig(UserDetailsService userDetailsService, JWTAuthorizationFilter jwtAuthorizationFilter)
	{
		this.userDetailsService = userDetailsService;  
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception
	{
		return http
				.csrf().disable().cors().and()
		        .authorizeHttpRequests( 
		        		(requests) ->
		        			requests.requestMatchers(new AntPathRequestMatcher("/auth/login")).permitAll()
		        			.anyRequest().authenticated()
		        		)
		        .sessionManagement()
		        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        .and()
		        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
		        .build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception
	{
		return http.getSharedObject(AuthenticationManagerBuilder.class)
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder())
			.and()
			.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
