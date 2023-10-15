package com.tec.wsnomina.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.ModuloDto;
import com.tec.wsnomina.entity.ModuloEntity;
import com.tec.wsnomina.entity.ModuloResponse;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.repository.IModuloRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class ModuloServiceImpl implements ModuloService {

	
	// services
	@Autowired
	private IModuloRepository iModuloRepository;
	
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	
	@Override
	public ModuloResponse createModulo(ModuloDto modulo, String sessionId) {
		
		ModuloResponse moduloResponse = new ModuloResponse();
		try
		{
			System.out.println("que mierdaaaaaaaaaaaaaa: " + modulo.getOrdenMenu());
			
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				moduloResponse.setStrResponseCode(methods.GETERROR());
				moduloResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return moduloResponse;
			}
			
			// 1.0 clean values
			modulo.setNombre(this.utils.clean(modulo.getNombre()));
			modulo.setOrdenMenu(this.utils.validNumber(this.utils.clean(String.valueOf(modulo.getOrdenMenu()))));
			
			// 2.0 validate fields
			if(modulo.getNombre().trim().equals(""))
			{
				moduloResponse.setStrResponseCode(methods.GETERROR());
				moduloResponse.setStrResponseMessage("El nombre no puede ir vacio");
				return moduloResponse;
			}
			
			if(modulo.getOrdenMenu() <= 0)
			{
				moduloResponse.setStrResponseCode(methods.GETERROR());
				moduloResponse.setStrResponseMessage("Debes de indicar el orden de posición");
				return moduloResponse;
			}
			
			
			ModuloEntity moduloEntity = new ModuloEntity();
			
			moduloEntity.setNombre(modulo.getNombre());
			moduloEntity.setOrdenMenu(modulo.getOrdenMenu());
			moduloEntity.setFechaCreacion(this.utils.getFechaHoraFormateada());
			moduloEntity.setUsuarioCreacion(sessionInformationResponse.getStrIdUsuario());
			
			moduloEntity = this.iModuloRepository.save(moduloEntity);
			
			modulo.setIdModulo(moduloEntity.getIdModulo());
			
			moduloResponse.setStrResponseCode(methods.GETSUCCESS());
			moduloResponse.setStrResponseMessage("Modulo creado exitosamente!");
			moduloResponse.setModulo(modulo);
			return moduloResponse;
			
		}
		catch (Exception e) {
			moduloResponse.setStrResponseCode(methods.GETERROR());
			moduloResponse.setStrResponseMessage("Error al intentar crear un nuevo modulo, vuelve a intentarlo");
			return moduloResponse;
		}
	}

	@Override
	public ModuloResponse updateModulo(ModuloDto modulo, String sessionId) {
		ModuloResponse moduloResponse = new ModuloResponse();
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				moduloResponse.setStrResponseCode(methods.GETERROR());
				moduloResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return moduloResponse;
			}
			
			// 1.0 clean values
			modulo.setIdModulo(this.utils.validNumber(this.utils.clean(String.valueOf(modulo.getIdModulo()))));
			modulo.setNombre(this.utils.clean(modulo.getNombre()));
			modulo.setOrdenMenu(this.utils.validNumber(this.utils.clean(String.valueOf(modulo.getOrdenMenu()))));
			
			// 2.0 validate fields
			if(modulo.getNombre().trim().equals(""))
			{
				moduloResponse.setStrResponseCode(methods.GETERROR());
				moduloResponse.setStrResponseMessage("El nombre no puede ir vacio");
				return moduloResponse;
			}
			
			if(modulo.getOrdenMenu() <= 0)
			{
				moduloResponse.setStrResponseCode(methods.GETERROR());
				moduloResponse.setStrResponseMessage("Debes de indicar el orden de posición");
				return moduloResponse;
			}
			
			// 3.0 exist modulo
			Optional<ModuloEntity> moduloEntity = this.iModuloRepository.findById(modulo.getIdModulo());
			
			// 4.0 if not exist modulo
			if(moduloEntity.isEmpty())
			{
				moduloResponse.setStrResponseCode(methods.GETERROR());
				moduloResponse.setStrResponseMessage("No se encontro el modulo, vuelve a intentarlo");
				return moduloResponse;
			}
			
			moduloEntity.get().setNombre(modulo.getNombre());
			moduloEntity.get().setOrdenMenu(modulo.getOrdenMenu());
			moduloEntity.get().setFechaModificacion(this.utils.getFechaHoraFormateada());
			moduloEntity.get().setUsuarioModificacion(sessionInformationResponse.getStrIdUsuario());
			
			this.iModuloRepository.save(moduloEntity.get());
			
			moduloResponse.setStrResponseCode(methods.GETERROR());
			moduloResponse.setStrResponseMessage("Modulo actualizado correctamente");
			moduloResponse.setModulo(modulo);
			return moduloResponse;
			
		}
		catch (Exception e) {
			moduloResponse.setStrResponseCode(methods.GETERROR());
			moduloResponse.setStrResponseMessage("Error al intentar actualizar un nuevo modulo, vuelve a intentarlo");
			return moduloResponse;
		}
	}

		
	
}
