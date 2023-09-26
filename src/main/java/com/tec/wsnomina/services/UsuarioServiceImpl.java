package com.tec.wsnomina.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.UsuarioCreateDto;
import com.tec.wsnomina.dto.UsuarioDto;
import com.tec.wsnomina.entity.EmpresaEntity;
import com.tec.wsnomina.entity.ListUsuarioResponse;
import com.tec.wsnomina.entity.SessionChangePassword;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.entity.SucursalEntity;
import com.tec.wsnomina.entity.UsuarioEntity;
import com.tec.wsnomina.entity.UsuarioResponse;
import com.tec.wsnomina.repository.IEmpresaRepository;
import com.tec.wsnomina.repository.ISucursalRepository;
import com.tec.wsnomina.repository.IUsuarioRepository;
import com.tec.wsnomina.security.PasswordEncrypt;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;


@Service
public class UsuarioServiceImpl implements UsuarioService {

	// repository
	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	// empresa
	@Autowired
	private IEmpresaRepository iempresaRepository;
	
	// sucursal
	@Autowired
	private ISucursalRepository iSucursalRepository;
	
	// services
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
			
	// utilities
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	private PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
	
	// conditions
	private int INACTIVO = 3;
	private int ACTIVO = 1;
	
	@Override
	public UsuarioResponse createUser(UsuarioCreateDto usuarioCreateDto, String sessionId) 
	{
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		
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
						
			usuarioCreateDto = cleanValues(usuarioCreateDto, usuarioResponse, "CREATE");
			
			if(usuarioResponse.getStrResponseCode().equals(methods.GETERROR()))
				return usuarioResponse;
			
			Optional<UsuarioEntity> usuarioSearch = this.iUsuarioRepository.findByCorreoElectronico(usuarioCreateDto.getCorreoElectronico());
			
			if(!usuarioSearch.isEmpty())
			{
				usuarioResponse.setStrResponseCode(methods.GETERROR());
				usuarioResponse.setStrResponseMessage("NO SE PUEDE CREAR UN USUARIO CON EL MISMO CORREO");
				return usuarioResponse;
			}
			
			UsuarioEntity usuarioEntity = new UsuarioEntity();
			usuarioEntity.setIdUsuario(usuarioCreateDto.getIdUsuario());
			usuarioEntity.setNombre(usuarioCreateDto.getNombre());
			usuarioEntity.setApellido(usuarioCreateDto.getApellido());
			usuarioEntity.setFechaNacimiento(usuarioCreateDto.getFechaNacimiento());
			usuarioEntity.setIdGenero(usuarioCreateDto.getIdGenero());
			usuarioEntity.setIdStatusUsuario(ACTIVO);
			usuarioEntity.setIdSucursal(usuarioCreateDto.getIdSucursal());
			usuarioEntity.setCorreoElectronico(usuarioCreateDto.getCorreoElectronico());
			usuarioEntity.setTelefonoMovil(usuarioCreateDto.getTelefonoMovil());
			
			String capPassword = generatePassword(usuarioCreateDto.getIdSucursal());
			usuarioEntity.setPassword(passwordEncrypt.generateEncrypt(capPassword));
			
			usuarioEntity.setFechaCreacion(utils.getFechaHoraFormateada());
			usuarioEntity.setUsuarioCreacion(sessionInformationResponse.getStrNombre());
			usuarioEntity.setRequiereCambiarPassword(usuarioCreateDto.getRequiereCambiarPassword());
			
			usuarioEntity = this.iUsuarioRepository.save(usuarioEntity);
			
			UsuarioDto usuarioDto = new UsuarioDto(
					usuarioEntity.getNombre(), 
					usuarioEntity.getApellido(), 
					usuarioEntity.getCorreoElectronico(), 
					usuarioEntity.getTelefonoMovil(), 
					usuarioEntity.getFechaNacimiento(), 
					usuarioEntity.getIdGenero());
			
			usuarioResponse.setEntUsuario(usuarioDto);
			usuarioResponse.setStrResponseCode(methods.GETSUCCESS());
			usuarioResponse.setStrResponseMessage("USUARIO CREADO, PASSWORD: " + capPassword );
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
	public UsuarioResponse updateUser(UsuarioCreateDto usuarioEntity, String sessionId) 
	{
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		
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
			
			usuarioEntity = cleanValues(usuarioEntity, usuarioResponse, "UPDATE");
			
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
			usuario.get().setIdGenero(usuarioEntity.getIdGenero());
			
			if(usuarioEntity.getFotografia() != null)
				usuario.get().setFotografia(usuarioEntity.getFotografia());
			
			usuario.get().setFechaModificacion(utils.getFechaHoraFormateada());
			usuario.get().setUsuarioModificacion(sessionInformationResponse.getStrNombre());
			
			this.iUsuarioRepository.save(usuario.get());
			
			usuarioResponse.setStrResponseCode(methods.GETSUCCESS());
			usuarioResponse.setStrResponseMessage("USUARIO ACTUALIZADO");
			UsuarioDto usuarioDto = new UsuarioDto(
					usuarioEntity.getNombre(), 
					usuarioEntity.getApellido(), 
					usuarioEntity.getCorreoElectronico(), 
					usuarioEntity.getTelefonoMovil(),
					usuarioEntity.getFechaNacimiento(),
					usuarioEntity.getIdGenero()
					);
			usuarioResponse.setEntUsuario(usuarioDto);
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

		UsuarioResponse usuarioResponse = new UsuarioResponse();
		
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

	public UsuarioResponse getUser(String sessionId)
	{

		UsuarioResponse usuarioResponse = new UsuarioResponse();
		
		UsuarioDto usuario = new UsuarioDto();
		try
		{
			SessionInformationResponse sessionInformation = this.sessionServiceImpl.getByInformationUserSesion(sessionId);
			Optional<UsuarioEntity> usuarioOptional = this.iUsuarioRepository.findById(sessionInformation.getStrIdUsuario());
			
			if(usuarioOptional.isEmpty())
			{
				usuarioResponse.setStrResponseCode(this.methods.GETERROR());
				usuarioResponse.setStrResponseMessage("Usuario no existe");
				return usuarioResponse;
			}
			
			usuario.setNombre(usuarioOptional.get().getNombre());
			usuario.setApellido(usuarioOptional.get().getApellido());
			usuario.setCorreoElectronico(usuarioOptional.get().getCorreoElectronico());
			usuario.setTelefonoMovil(usuarioOptional.get().getTelefonoMovil());
			usuario.setIdGenero(usuarioOptional.get().getIdGenero());
			usuario.setFechaNacimiento(usuarioOptional.get().getFechaNacimiento());
			
			usuarioResponse.setStrResponseCode(methods.GETSUCCESS());
			usuarioResponse.setStrResponseMessage("SUCCESS");
			usuarioResponse.setEntUsuario(usuario);
			return usuarioResponse;
		}
		catch(Exception ex)
		{
			System.out.println("Error: " + ex.getMessage());
			usuarioResponse.setStrResponseCode(methods.GETERROR());
			usuarioResponse.setStrResponseMessage("ERROR AL INTENTAR CON EL USUARIO");
			return usuarioResponse;
		
		}
	}
	
	public ListUsuarioResponse getUsers(String sessionId)
	{
		ListUsuarioResponse listUsuarioResponse = new ListUsuarioResponse();
		try
		{
			// VALIDAMOS PRIMERO LA SESION
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				listUsuarioResponse.setStrResponseCode(methods.GETERROR());
				listUsuarioResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return listUsuarioResponse;
			}
						
			List<UsuarioEntity> users = this.iUsuarioRepository.findAll();
			List<UsuarioCreateDto> userdto = new ArrayList<UsuarioCreateDto>();
			
			for(UsuarioEntity user : users)
			{
				userdto.add( new UsuarioCreateDto(user.getNombre(), 
						user.getApellido(), 
						user.getCorreoElectronico(), 
						user.getTelefonoMovil(), 
						user.getFechaNacimiento(), 
						user.getIdGenero(),
					    user.getIdUsuario(),
					    user.getIdSucursal(),
					    user.getRequiereCambiarPassword(),
					    user.getFotografia()
				));
			}
			listUsuarioResponse.setStrResponseCode(methods.GETSUCCESS());
			listUsuarioResponse.setStrResponseMessage("SUCCESS");
			listUsuarioResponse.setUsuarios(userdto);
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: UsuarioServiceLmpl.ListUsuarioResponse() " + ex.getMessage());
			listUsuarioResponse.setStrResponseCode(methods.GETERROR());
			listUsuarioResponse.setStrResponseMessage("ERROR LA OBTENER LA LISTA DE USUARIOS");
		}
		return listUsuarioResponse;
	}
	
	public UsuarioResponse updatePassword(String sessionId, SessionChangePassword sessionCredentials)
	{
		UsuarioResponse usuarioResponse = new UsuarioResponse();
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
			Optional<UsuarioEntity> userOptional = this.iUsuarioRepository.findById(sessionCredentials.getCorreoElectronico());		
						
			Optional<EmpresaEntity> empresaOptional = this.iempresaRepository.findById(userOptional.get().getIdSucursal());
			

			if(this.passwordEncrypt.CompareToPasswords(userOptional.get().getPassword(), sessionCredentials.getPassword()))
			{
				// TODO: AGREGAR LAS VALIDACIONES DE LA EMPRESA
				int cantM = empresaOptional.get().getPasswordCantidadMayusculas();
				int countM = 0;
				
				for(int i = 0; i < sessionCredentials.getNewPassword().length(); i++ )
				{
					char caracter =  sessionCredentials.getNewPassword().charAt(i);
					if(Character.isUpperCase(caracter))
					{
						countM++;
					}
				}
				
				
				
				
			}
			
		}
		catch(Exception ex)
		{
			
		}
		return usuarioResponse;
	}
	
	public String generatePassword(int idSucursal)
	{
		Optional<SucursalEntity> sucursal = this.iSucursalRepository.findById(idSucursal);
		EmpresaEntity empresa = sucursal.get().getEmpresa();
		
		int length = empresa.getPasswordLargo();
		int minNumber = empresa.getPasswordCantidadNumeros();
		int minLowercase = empresa.getPasswordCantidadMinusculas();
		int minUppercase = empresa.getPasswordCantidadMayusculas();
		int minCharacterEspecial = empresa.getPasswordCantidadCaracteresEspeciales();
		
		return this.utils.generatePassword(length, minNumber, minLowercase, minUppercase, minCharacterEspecial);
		
	}
	
	// CLEAN VALUES
	private UsuarioCreateDto cleanValues(UsuarioCreateDto usuarioEntity, UsuarioResponse usuarioResponse, String OPERATION)
	{
		UsuarioCreateDto usuarioEntityClean = new UsuarioCreateDto();
		
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
		
		usuarioEntityClean.setCorreoElectronico(utils.clean(usuarioEntity.getCorreoElectronico()));
		
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













