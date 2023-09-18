package com.tec.wsnomina.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tec.wsnomina.entity.SessionCredentials;

public class UserDetailsImpl implements UserDetails {

	private final SessionCredentials sessionCredentials;
	
	public UserDetailsImpl(SessionCredentials sessionCredentials)
	{
		this.sessionCredentials = sessionCredentials;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.sessionCredentials.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.sessionCredentials.getCorreoElectronico();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
