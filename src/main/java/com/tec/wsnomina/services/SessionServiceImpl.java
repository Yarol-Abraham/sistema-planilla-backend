package com.tec.wsnomina.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.entity.SessionCredentials;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.entity.UsuarioEntity;
import com.tec.wsnomina.repository.IUsuarioRepository;
import com.tec.wsnomina.security.GenerateToken;
import com.tec.wsnomina.security.PasswordEncrypt;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	private UsuarioEntity usuarioEntity = new UsuarioEntity();
	
	private SessionInformationResponse sessionInformationResponse = new SessionInformationResponse();
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	private PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
	private GenerateToken generateToken = new GenerateToken();
	
	private final int RESET_ACCESS = 0;
	private final int MAX_FAILED_ATTEMPTS = 5;
	private final int STATUS_LOCKED = 2;
	
	@Override
	public SessionInformationResponse generateSessionByUser(SessionCredentials sessionCredentials)
	{
		try
		{
			sessionCredentials = cleanValuesCredential(sessionCredentials);
			
			// validate fields
			if(sessionCredentials.getCorreoElectronico().isEmpty())
			{
				sessionInformationResponse.setStrResponseCode(this.methods.GETERROR());
				sessionInformationResponse.setStrResponseMessage("CREDENCIALES INVÁLIDAS, verifica que tu correo sea correcto");
				return sessionInformationResponse;
			}
			
			if(sessionCredentials.getPassword().isEmpty())
			{
				sessionInformationResponse.setStrResponseCode(this.methods.GETERROR());
				sessionInformationResponse.setStrResponseMessage("CREDENCIALES INVÁLIDAS, verifica que tu contraseña sea correcta");
				return sessionInformationResponse;
			}
			
			Optional<UsuarioEntity> searchUser = this.iUsuarioRepository
					.findByCorreoElectronico(sessionCredentials.getCorreoElectronico());
			
			if(searchUser.isEmpty())
			{
				sessionInformationResponse.setStrResponseCode(this.methods.GETERROR());
				sessionInformationResponse.setStrResponseMessage("CREDENCIALES INVÁLIDAS, verifica que tu correo sea correcto");
				return sessionInformationResponse;
			}
			
			if(this.passwordEncrypt.CompareToPasswords(searchUser.get().getPassword(), sessionCredentials.getPassword()))
			{
				String generateToken = GenerateToken.createToken(sessionCredentials.getCorreoElectronico());
				
				sessionInformationResponse.setStrSessionId(generateToken);
				sessionInformationResponse.setStrNombre(searchUser.get().getNombre());
				sessionInformationResponse.setStrIdUsuario(searchUser.get().getIdUsuario());
				sessionInformationResponse.setStrFotografia(searchUser.get().getFotografia());
				
				sessionInformationResponse.setStrResponseCode(this.methods.GETSUCCESS());
				sessionInformationResponse.setStrResponseMessage("SUCCESS");
				
				usuarioEntity = searchUser.get();
				usuarioEntity.setSesionActual(generateToken);
				usuarioEntity.setUltimaFechaIngreso(utils.getFechaHoraFormateada());
				usuarioEntity.setIntentosDeAcceso(RESET_ACCESS);
				this.iUsuarioRepository.save(usuarioEntity);
				
				return sessionInformationResponse;
			}

			systemFailCount(searchUser.get());
			sessionInformationResponse.setStrResponseMessage("CREDENCIALES INVÁLIDAS, verifica que tu contraseña o correo sean correctos");
			return sessionInformationResponse;
		
			
		}
		catch(Exception ex) 
		{
			System.out.println("ERROR EN: SessionServiceImpl.generateSessionByUser() " + ex.getMessage());
			sessionInformationResponse.setStrResponseCode(this.methods.GETERROR());
			sessionInformationResponse.setStrResponseMessage("ERROR AL GENERAR SESIÓN (FAIL)");
		}
		
		return sessionInformationResponse;
	}
	
	@Override
	public SessionInformationResponse getByInformationUserSesion(String sessionId) 
	{
		try
		{
			String correoEelectronicoToken = generateToken.getCorreoElectronicoToken(sessionId);
			
			if(correoEelectronicoToken.equals(methods.GETERROR())) 
			{
				sessionInformationResponse.setStrResponseCode(this.methods.GETERROR());
				sessionInformationResponse.setStrResponseMessage("TIEMPO AGOTADO, LA SESIÓN HA EXPIRADO");
				resetValuesSessionInformationResponse();
				return sessionInformationResponse;
			}
			
			if(correoEelectronicoToken.isEmpty())
			{
				sessionInformationResponse.setStrResponseCode(this.methods.GETERROR());
				sessionInformationResponse.setStrResponseMessage("ERROR AL OBTENER DATOS DE LA SESIÓN (FAIL)");
				resetValuesSessionInformationResponse();
				return sessionInformationResponse;
			}
			
			Optional<UsuarioEntity> searchUser = this.iUsuarioRepository.findByCorreoElectronico(correoEelectronicoToken);
			if(searchUser.isEmpty())
			{
				sessionInformationResponse.setStrResponseCode(this.methods.GETERROR());
				sessionInformationResponse.setStrResponseMessage("CREDENCIALES INVÁLIDAS, verifica que tu correo sea correcto");
				return sessionInformationResponse;
			}
			
				
			sessionInformationResponse.setStrSessionId(sessionId);
			sessionInformationResponse.setStrNombre(searchUser.get().getNombre());
			sessionInformationResponse.setStrIdUsuario(searchUser.get().getIdUsuario());
			sessionInformationResponse.setStrFotografia(searchUser.get().getFotografia());
			
			sessionInformationResponse.setStrResponseCode(this.methods.GETSUCCESS());
			sessionInformationResponse.setStrResponseMessage("SUCCESS");
			
			return sessionInformationResponse;
			
			
		}	
		catch(Exception ex)
		{
			System.out.println("ERROR EN: SessionServiceImpl.getByInformationUserSesion()" + ex.getMessage());
			sessionInformationResponse.setStrResponseCode(this.methods.GETERROR());
			sessionInformationResponse.setStrResponseMessage("ERROR AL OBTENER DATOS DE LA SESIÓN (FAIL)");
			resetValuesSessionInformationResponse();
		}
		return sessionInformationResponse;
	}
		
	private SessionCredentials cleanValuesCredential(SessionCredentials sessionCredentials)
	{
		SessionCredentials sessionCredentialsClean = new SessionCredentials();
		sessionCredentialsClean.setCorreoElectronico(this.utils.clean(sessionCredentials.getCorreoElectronico()));
		sessionCredentialsClean.setPassword(this.utils.clean(sessionCredentials.getPassword()));
		
		return sessionCredentialsClean;
	}
	
	private void systemFailCount(UsuarioEntity usuarioEntity)
	{
		try
		{
			if(MAX_FAILED_ATTEMPTS == usuarioEntity.getIntentosDeAcceso())
			{
				 usuarioEntity.setIdStatusUsuario(STATUS_LOCKED);
				 this.iUsuarioRepository.save(usuarioEntity);
			}
			else
			{
				usuarioEntity.setIntentosDeAcceso(usuarioEntity.getIntentosDeAcceso() + 1);
				this.iUsuarioRepository.save(usuarioEntity);
			}
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: SessionServiceImpl.systemFailCount() " + ex.getMessage());
		}
		
	}
	
	private void resetValuesSessionInformationResponse()
	{
		sessionInformationResponse.setStrFotografia("");
		sessionInformationResponse.setStrIdUsuario("");
		sessionInformationResponse.setStrNombre("");
	}
	
}
