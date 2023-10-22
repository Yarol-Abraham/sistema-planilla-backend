package com.tec.wsnomina.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.PersonaDto;
import com.tec.wsnomina.entity.PersonaEntity;
import com.tec.wsnomina.entity.PersonaListResponse;
import com.tec.wsnomina.entity.PersonaResponse;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.repository.IPersonaRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class PersonaServicelmpl implements PersonaService {

	@Autowired
	private IPersonaRepository iPersonaRepository;
	
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	
	@Override
	public PersonaListResponse getPersons(String sessionId) {
		
		PersonaListResponse personaListResponse = new PersonaListResponse();
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				personaListResponse.setStrResponseCode(methods.GETERROR());
				personaListResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return personaListResponse;
			}
			
			List<PersonaDto> personas = this.iPersonaRepository.findAll()
					.stream()
					.map( persona -> new PersonaDto(persona.getIdPersona(), persona.getNombre(), 
							persona.getApellido(), persona.getFechaNacimiento(), persona.getGenero().getIdGenero(), persona.getGenero().getNombre(), 
							persona.getDireccion(), persona.getTelefono(), persona.getCorreoElectronico(), persona.getEstadoCivil().getNombre(), persona.getEstadoCivil().getIdEstadoCivil()) )
					.collect(Collectors.toList());
			
			personaListResponse.setStrResponseCode(methods.GETSUCCESS());
			personaListResponse.setStrResponseMessage("");
			personaListResponse.setPersonas(personas);
			
		}
		catch(Exception ex)
		{
			personaListResponse.setStrResponseCode(methods.GETERROR());
			personaListResponse.setStrResponseMessage("error al obtener el listado de personas");
		}
		
		return personaListResponse;
	}

	@Override
	public PersonaResponse createPeople(PersonaDto personaDto, String sessionId) 
	{
		PersonaResponse personaResponse = new PersonaResponse();
		
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				personaResponse.setStrResponseCode(methods.GETERROR());
				personaResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return personaResponse;
			}
			
			// validated fields
			if(!personaDto.validarCampos())
			{
				personaResponse.setStrResponseCode(methods.GETERROR());
				personaResponse.setStrResponseMessage("error, verifique que todos los campos sean correctos.");
				return personaResponse;
			}
			
			PersonaEntity persona = new PersonaEntity();
			persona.setNombre(personaDto.getNombre());
			persona.setApellido(personaDto.getApellido());
			persona.setCorreoElectronico(personaDto.getCorreoElectronico());
			persona.setDireccion(personaDto.getDireccion());
			persona.setFechaCreacion(new Date());
			persona.setFechaNacimiento(personaDto.getFechaNacimiento());
			persona.setIdGenero(personaDto.getIdGenero());
			persona.setIdEstadoCivil(personaDto.getIdEstadoCivil());
			persona.setUsuarioCreacion(sessionInformationResponse.getStrIdUsuario());
			persona.setTelefono(personaDto.getTelefono());
			
			persona = this.iPersonaRepository.save(persona);
			
			personaDto.setIdPersona(persona.getIdPersona());
			personaResponse.setStrResponseCode(methods.GETSUCCESS());
			personaResponse.setStrResponseMessage("persona creada exitosamente");
			personaResponse.setPersona(personaDto);
		}
		catch(Exception ex)
		{
			personaResponse.setStrResponseCode(methods.GETERROR());
			personaResponse.setStrResponseMessage("error al intentar crear una persona, vuelve a intentarlo");
		}
		
		return personaResponse;
	}

	@Override
	public PersonaResponse updatePeople(PersonaDto personaDto, String sessionId) {
	
		PersonaResponse personaResponse = new PersonaResponse();
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				personaResponse.setStrResponseCode(methods.GETERROR());
				personaResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return personaResponse;
			}
			
			// validated fields
			if(!personaDto.validarCampos())
			{
				personaResponse.setStrResponseCode(methods.GETERROR());
				personaResponse.setStrResponseMessage("error, verifique que todos los campos sean correctos.");
				return personaResponse;
			}
				
			Optional<PersonaEntity> persona = this.iPersonaRepository.findById(personaDto.getIdPersona());
			
			if(persona.isEmpty())
			{
				personaResponse.setStrResponseCode(methods.GETERROR());
				personaResponse.setStrResponseMessage("error, la persona no existe para actualizar sus datos");
				return personaResponse;
			}
			
			persona.get().setNombre(personaDto.getNombre());
			persona.get().setApellido(personaDto.getApellido());
			persona.get().setCorreoElectronico(personaDto.getCorreoElectronico());
			persona.get().setDireccion(personaDto.getDireccion());
			persona.get().setTelefono(personaDto.getTelefono());
			persona.get().setFechaNacimiento(personaDto.getFechaNacimiento());
			persona.get().setIdGenero(personaDto.getIdGenero());
			persona.get().setIdEstadoCivil(personaDto.getIdEstadoCivil());
			persona.get().setUsuarioModificacion(sessionInformationResponse.getStrIdUsuario());
			persona.get().setFechaModificacion(new Date());
			
			
			this.iPersonaRepository.save(persona.get());
			
			personaResponse.setStrResponseCode(methods.GETSUCCESS());
			personaResponse.setStrResponseMessage("datos actualizados correctamente");
			personaResponse.setPersona(personaDto);
			
		}
		catch(Exception ex)
		{

			System.out.println("error al actualizar la persona: " + ex.getMessage());
			personaResponse.setStrResponseCode(methods.GETERROR());
			personaResponse.setStrResponseMessage("error al intentar actualizar la persona, vuelve a intentarlo");
		}
		return personaResponse;
	}

	
	
}


















