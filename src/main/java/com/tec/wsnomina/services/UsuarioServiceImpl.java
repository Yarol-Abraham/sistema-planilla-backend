package com.tec.wsnomina.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.entity.UsuarioEntity;
import com.tec.wsnomina.entity.UsuarioResponse;
import com.tec.wsnomina.repository.IUsuarioRepository;
import com.tec.wsnomina.security.PasswordEncrypt;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;


@Service
public class UsuarioServiceImpl implements UsuarioService {

	// repository
	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	
	// services
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	
	// entity
	private UsuarioResponse usuarioResponse = new UsuarioResponse();
	
	// utilities
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	private PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
	
	// conditions
	private int INACTIVO = 3;
	private int ACTIVO = 1;
	
	@Override
	public UsuarioResponse createUser(UsuarioEntity usuarioEntity, String sessionId) 
	{
		try
		{
			// VALIDAMOS PRIMERO LA SESION
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				usuarioResponse.setStrResponseCode(methods.GETERROR());
				usuarioResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return usuarioResponse;
			}
						
			usuarioEntity =  cleanValues(usuarioEntity, "CREATE");
			
			if(usuarioResponse.getStrResponseCode().equals(methods.GETERROR()))
				return usuarioResponse;
			
			Optional<UsuarioEntity> usuarioSearch = this.iUsuarioRepository.findByCorreoElectronico(usuarioEntity.getCorreoElectronico());
			
			if(!usuarioSearch.isEmpty())
			{
				usuarioResponse.setStrResponseCode(methods.GETERROR());
				usuarioResponse.setStrResponseMessage("NO SE PUEDE CREAR UN USUARIO CON EL MISMO CORREO");
				return usuarioResponse;
			}
			
			usuarioEntity.setIdUsuario(usuarioEntity.getNombre());
			usuarioEntity.setFechaCreacion(utils.getFechaHoraFormateada());
			usuarioEntity.setPassword(passwordEncrypt.generateEncrypt(usuarioEntity.getPassword()));
			usuarioEntity.setUsuarioCreacion(sessionInformationResponse.getStrNombre());
			usuarioEntity = this.iUsuarioRepository.save(usuarioEntity);
			usuarioEntity.setPassword("");
			
			usuarioResponse.setEntUsuario(usuarioEntity);
			usuarioResponse.setStrResponseCode(methods.GETSUCCESS());
			usuarioResponse.setStrResponseMessage("USUARIO CREADO");
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: Create() " + ex.getMessage());
			usuarioResponse.setStrResponseCode(methods.GETERROR());
			usuarioResponse.setStrResponseMessage("ERROR AL CREAR USUARIO");
		}
		return usuarioResponse;		
	}

	@Override
	public UsuarioResponse updateUser(UsuarioEntity usuarioEntity, String sessionId) 
	{
		try
		{
			// VALIDAMOS PRIMERO LA SESION
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				usuarioResponse.setStrResponseCode(methods.GETERROR());
				usuarioResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return usuarioResponse;
			}
			
			System.out.println("USUARIO: " + usuarioEntity);
			
			
			usuarioEntity = cleanValues(usuarioEntity, "UPDATE");
			

			System.out.println("USUARIO: " + usuarioEntity);
			
			
			if(usuarioResponse.getStrResponseCode().equals(methods.GETERROR()))
				return usuarioResponse;
			
			Optional<UsuarioEntity> usuario = this.iUsuarioRepository.findById(usuarioEntity.getIdUsuario());
			
			if(usuario.isEmpty())
			{
				usuarioResponse.setStrResponseCode(methods.GETRESOURCE_NOT_FOUND());
				usuarioResponse.setStrResponseMessage("USUARIO NO ENCONTRADO");
				return usuarioResponse;
			}
			
			// list data update
			usuario.get().setNombre(usuarioEntity.getNombre());
			usuario.get().setApellido(usuarioEntity.getApellido());
			usuario.get().setFechaNacimiento(usuarioEntity.getFechaNacimiento());
			usuario.get().setTelefonoMovil(usuarioEntity.getTelefonoMovil());
			usuario.get().setCorreoElectronico(usuarioEntity.getCorreoElectronico());
		
			if(usuarioEntity.getFotografia() != null)
				usuario.get().setFotografia(usuarioEntity.getFotografia());
			
			usuario.get().setFechaModificacion(utils.getFechaHoraFormateada());
			usuario.get().setUsuarioModificacion(sessionInformationResponse.getStrNombre());
			
			this.iUsuarioRepository.save(usuario.get());
			
			usuarioResponse.setStrResponseCode(methods.GETSUCCESS());
			usuarioResponse.setStrResponseMessage("USUARIO ACTUALIZADO");
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: UPDATE() " + ex.getMessage());
			ex.getStackTrace();
			usuarioResponse.setStrResponseCode(methods.GETERROR());
			usuarioResponse.setStrResponseMessage("ERROR AL INTENTAR ACTUALIZAR EL USUARIO");
		}
		return usuarioResponse;	
	}
	
	public UsuarioResponse deleteUser(String IdUsuario, String sessionId)
	{
		try
		{
			// VALIDAMOS PRIMERO LA SESION
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				usuarioResponse.setStrResponseCode(methods.GETERROR());
				usuarioResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return usuarioResponse;
			}
			
			Optional<UsuarioEntity> usuario = this.iUsuarioRepository.findById(IdUsuario);
			if(usuario.isEmpty())
			{
				usuarioResponse.setStrResponseCode(methods.GETRESOURCE_NOT_FOUND());
				usuarioResponse.setStrResponseMessage("USUARIO NO ENCONTRADO");
				return usuarioResponse;
			}
			
			usuario.get().setIdStatusUsuario(INACTIVO);
			usuario.get().setFechaModificacion(utils.getFechaHoraFormateada());
			usuario.get().setUsuarioModificacion(sessionInformationResponse.getStrNombre());
			
			this.iUsuarioRepository.save(usuario.get());
			
			usuarioResponse.setStrResponseCode(methods.GETSUCCESS());
			usuarioResponse.setStrResponseMessage("USUARIO ELIMINADO CORRECTAMENTE");
			
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: deleteUser() " + ex.getMessage());
			usuarioResponse.setStrResponseCode(methods.GETERROR());
			usuarioResponse.setStrResponseMessage("ERROR AL ELIMINAR EL USUARIO");
		}
		return usuarioResponse;
	}
	
	// CLEAN VALUES
	private UsuarioEntity cleanValues(UsuarioEntity usuarioEntity, String OPERATION)
	{
		UsuarioEntity usuarioEntityClean = new UsuarioEntity();
		usuarioEntityClean.setPassword("");
		
		usuarioEntityClean.setIdUsuario(utils.clean(usuarioEntity.getIdUsuario()));
		usuarioEntityClean.setNombre(utils.clean(usuarioEntity.getNombre()));
		usuarioEntityClean.setApellido(utils.clean(usuarioEntity.getApellido()));
		usuarioEntityClean.setFechaNacimiento(utils.clean(usuarioEntity.getFechaNacimiento()));
		usuarioEntityClean.setTelefonoMovil(utils.clean(usuarioEntity.getTelefonoMovil()));
		
		usuarioEntityClean.setIdGenero(validNumber(this.utils.clean(String.valueOf(usuarioEntity.getIdGenero()))));
		usuarioEntityClean.setIdSucursal(validNumber(this.utils.clean(String.valueOf(usuarioEntity.getIdSucursal()))));
		
		if(String.valueOf(usuarioEntityClean.getIdGenero()) == this.methods.GETERROR())
		{
			usuarioResponse.setStrResponseCode(methods.GETERROR());
			usuarioResponse.setStrResponseMessage("ERROR, EL PARAMETRO: 'GENERO' NO ES VÁLIDO");
			return usuarioEntityClean;
		}
		
		if(String.valueOf(usuarioEntityClean.getIdSucursal()) == this.methods.GETERROR())
		{
			usuarioResponse.setStrResponseCode(methods.GETERROR());
			usuarioResponse.setStrResponseMessage("ERROR, EL PARAMETRO: 'SUCURSAL' NO ES VÁLIDO");
			return usuarioEntityClean;
		}
		
		
		usuarioEntityClean.setPassword(utils.clean(usuarioEntity.getPassword()));
		
		
		usuarioEntityClean.setCorreoElectronico(utils.clean(usuarioEntity.getCorreoElectronico()));
		
		if(OPERATION.equals("CREATE"))
		{
			usuarioEntityClean.setIdUsuario("");
			usuarioEntityClean.setIdStatusUsuario(ACTIVO);
			usuarioEntityClean.setFechaCreacion("");
			usuarioEntityClean.setUltimaFechaCambioPassword(null);
			usuarioEntityClean.setUsuarioModificacion(null);
			usuarioEntityClean.setFechaModificacion(null);
			usuarioEntityClean.setUltimaFechaIngreso(null);
			usuarioEntityClean.setIntentosDeAcceso(0);
		}
		// validate required password
		if(usuarioEntity.getRequiereCambiarPassword() == 0 || usuarioEntity.getRequiereCambiarPassword() == 1 )
		{
			usuarioEntityClean.setRequiereCambiarPassword(usuarioEntity.getRequiereCambiarPassword());
		}
		else
		{
			usuarioResponse.setStrResponseCode(methods.GETERROR());
			usuarioResponse.setStrResponseMessage("ERROR, EL PARAMETRO: 'REQUIERE CAMBIAR PASSWORD' NO ES VÁLIDO");
		}
		
		if(usuarioEntity.getFotografia() != null)
			usuarioEntityClean.setFotografia(utils.clean(usuarioEntity.getFotografia()));
		
		if(usuarioEntity.getSesionActual() != null)
			usuarioEntityClean.setSesionActual(utils.clean(usuarioEntity.getSesionActual()));
		
		return usuarioEntityClean;
	}
	
	private int validNumber(String strnumber)
	{
		int format = -1;
		try
		{
			format = Integer.parseInt(strnumber);
		}
		catch(Exception ex)
		{
			System.out.println("ERROR AL CONVERTIR NUMERO: validNumber(): " + ex.getMessage());
			format = -1;
		}
		return format;
	}
	
}













