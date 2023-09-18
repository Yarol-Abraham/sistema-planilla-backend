package com.tec.wsnomina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.entity.SessionCredentials;
import com.tec.wsnomina.entity.UsuarioEntity;
import com.tec.wsnomina.repository.IUsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	// repository
	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String correoElectronico)
	throws UsernameNotFoundException 
	{	
		SessionCredentials sessionCredentials = new SessionCredentials();
		
		UsuarioEntity usuarioEnOptional = this.iUsuarioRepository
			.findByCorreoElectronico(correoElectronico)
			.orElseThrow( ()-> new UsernameNotFoundException("no existe el usuario"));
		
		sessionCredentials.setPassword(usuarioEnOptional.getPassword());
		sessionCredentials.setCorreoElectronico(usuarioEnOptional.getCorreoElectronico());
		
		return new UserDetailsImpl(sessionCredentials);
	}
	
}
