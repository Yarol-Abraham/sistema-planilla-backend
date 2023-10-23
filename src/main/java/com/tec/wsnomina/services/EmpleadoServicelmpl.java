package com.tec.wsnomina.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.EmpleadoCreateDto;
import com.tec.wsnomina.dto.EmpleadoDto;
import com.tec.wsnomina.dto.PersonaDto;
import com.tec.wsnomina.dto.PuestoDto;
import com.tec.wsnomina.dto.SucursalDto;
import com.tec.wsnomina.entity.EmpleadoEntity;
import com.tec.wsnomina.entity.EmpleadoListResponse;
import com.tec.wsnomina.entity.EmpleadoResponse;
import com.tec.wsnomina.entity.PersonaEntity;
import com.tec.wsnomina.entity.PuestoEntity;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.entity.StatusEmpleadoEntity;
import com.tec.wsnomina.entity.SucursalEntity;
import com.tec.wsnomina.repository.IEmpleadoRepository;
import com.tec.wsnomina.repository.IPersonaRepository;
import com.tec.wsnomina.repository.IPuestoRepository;
import com.tec.wsnomina.repository.IStatusEmpleadoRepository;
import com.tec.wsnomina.repository.ISucursalRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class EmpleadoServicelmpl implements EmpleadoService {

	@Autowired
	private IEmpleadoRepository iEmpleadoRepository;
	
	@Autowired
	private IPersonaRepository iPersonaRepository;
	
	@Autowired
	private IPuestoRepository iPuestoRepository;
	
	@Autowired
	private ISucursalRepository iUISucursalRepository;
	
	@Autowired
	private IStatusEmpleadoRepository iUStatusEmpleadoRepository;
	
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	
	// utilities
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	
	@Override
	public EmpleadoListResponse getEmpleados(String sessionId) {
		
		EmpleadoListResponse empleadoListResponse = new EmpleadoListResponse();
		
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				empleadoListResponse.setStrResponseCode(methods.GETERROR());
				empleadoListResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return empleadoListResponse;
			}
			
			List<EmpleadoDto> empleados = this.iEmpleadoRepository.findAll().stream().map(
					empleado -> new EmpleadoDto(empleado.getIdEmpleado(), 
							new PersonaDto(
									empleado.getPersona().getIdPersona(), 
									empleado.getPersona().getNombre(), 
									empleado.getPersona().getApellido(),
									empleado.getPersona().getFechaNacimiento(),
									empleado.getPersona().getGenero().getIdGenero(),
									empleado.getPersona().getGenero().getNombre(),
									empleado.getPersona().getDireccion(),
									empleado.getPersona().getTelefono(),
									empleado.getPersona().getCorreoElectronico(),
									empleado.getPersona().getEstadoCivil().getNombre(),
									empleado.getPersona().getEstadoCivil().getIdEstadoCivil()
							),
							new SucursalDto(empleado.getSucursal().getIdSucursal(), empleado.getSucursal().getNombre()),
							empleado.getFechaContratacion(), 
							new PuestoDto(empleado.getPuesto().getIdPuesto(), empleado.getPuesto().getNombre(), empleado.getPuesto().getDepartamento().getIdDepartamento()), 
							empleado.getStatusEmpleado().getIdStatusEmpleado(),
							empleado.getIngresoSueldoBase(), 
							empleado.getIngresoBonificacionDecreto(), 
							empleado.getIngresoOtrosIngresos(),
							empleado.getDescuentoIgss(), 
							empleado.getDescuentoIsr(), 
							empleado.getDescuentoInasistencias())
					).collect(Collectors.toList());
			
			empleadoListResponse.setStrResponseCode(methods.GETSUCCESS());
			empleadoListResponse.setStrResponseMessage("datos obtenidos");
			empleadoListResponse.setEmpleados(empleados);
		}
		catch(Exception ex)
		{
			empleadoListResponse.setStrResponseCode(methods.GETERROR());
			empleadoListResponse.setStrResponseMessage("error al obtener la lista de empleados");
		}
		return empleadoListResponse;
	}

	@Override
	public EmpleadoResponse createEmpleado(EmpleadoCreateDto empleadodto, String sessionId) {
		
		EmpleadoResponse empleadoResponse = new EmpleadoResponse();
		
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				empleadoResponse.setStrResponseCode(methods.GETERROR());
				empleadoResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return empleadoResponse;
			}
			
			if(!empleadodto.validarDatos())
			{
				empleadoResponse.setStrResponseCode(methods.GETERROR());
				empleadoResponse.setStrResponseMessage("error, verifique que todos los campos sean correctos.");
				return empleadoResponse;
			}
			
			EmpleadoEntity empleadoEntity = new EmpleadoEntity();
			
			PuestoEntity puesto = new PuestoEntity();
			SucursalEntity sucursal = new SucursalEntity();
			PersonaEntity persona = new PersonaEntity();
			StatusEmpleadoEntity statusEmpleadoEntity = new StatusEmpleadoEntity();
			
			persona.setIdPersona(empleadodto.getIdPersona());
			puesto.setIdPuesto(empleadodto.getIdPuesto());
			sucursal.setIdSucursal(empleadodto.getIdSucursal());
		
			statusEmpleadoEntity.setIdStatusEmpleado(empleadodto.getIdStatusEmpleado());
			
			empleadoEntity.setPersona(persona);
			empleadoEntity.setPuesto(puesto);
			empleadoEntity.setSucursal(sucursal);
			empleadoEntity.setStatusEmpleado(statusEmpleadoEntity);
			
			empleadoEntity.setFechaContratacion(empleadodto.getFechaContratacion());
			empleadoEntity.setIngresoSueldoBase(empleadodto.getIngresoSueldoBase());
			empleadoEntity.setIngresoBonificacionDecreto(empleadodto.getIngresoBonificacionDecreto());
			empleadoEntity.setDescuentoIgss(empleadodto.getDescuentoIgss());
			empleadoEntity.setDescuentoIsr(empleadodto.getDescuentoIsr());
			empleadoEntity.setDescuentoInasistencias(empleadodto.getDescuentoInasistencias());
			empleadoEntity.setIngresoOtrosIngresos(empleadodto.getIngresoOtrosIngresos());
			empleadoEntity.setFechaCreacion(new Date());
			empleadoEntity.setUsuarioCreacion(sessionInformationResponse.getStrIdUsuario());
			
			empleadoEntity = this.iEmpleadoRepository.save(empleadoEntity);
			
			empleadodto.setIdEmpleado(empleadoEntity.getIdEmpleado());
			
			PersonaEntity personacreate = this.iPersonaRepository.findById(empleadodto.getIdPersona()).get();
			puesto = this.iPuestoRepository.findById(empleadoEntity.getPuesto().getIdPuesto()).get();
			sucursal = this.iUISucursalRepository.findById(empleadoEntity.getSucursal().getIdSucursal()).get();
			statusEmpleadoEntity = this.iUStatusEmpleadoRepository.findById(empleadoEntity.getStatusEmpleado().getIdStatusEmpleado()).get();
			
			EmpleadoDto empleadoDto = new EmpleadoDto(empleadodto.getIdEmpleado(), 
					new PersonaDto(
							personacreate.getIdPersona(), 
							personacreate.getNombre(), 
							personacreate.getApellido(),
							personacreate.getFechaNacimiento(),
							personacreate.getGenero().getIdGenero(),
							personacreate.getGenero().getNombre(),
							personacreate.getDireccion(),
							personacreate.getTelefono(),
							personacreate.getCorreoElectronico(),
							personacreate.getEstadoCivil().getNombre(),
							personacreate.getEstadoCivil().getIdEstadoCivil()
					),
					new SucursalDto(sucursal.getIdSucursal(), sucursal.getNombre()),
					empleadoEntity.getFechaContratacion(), 
					new PuestoDto(puesto.getIdPuesto(), puesto.getNombre(), puesto.getDepartamento().getIdDepartamento()), 
					statusEmpleadoEntity.getIdStatusEmpleado(),
					empleadoEntity.getIngresoSueldoBase(), 
					empleadoEntity.getIngresoBonificacionDecreto(), 
					empleadoEntity.getIngresoOtrosIngresos(),
					empleadoEntity.getDescuentoIgss(), 
					empleadoEntity.getDescuentoIsr(), 
					empleadoEntity.getDescuentoInasistencias());
			
			empleadoResponse.setStrResponseCode(methods.GETSUCCESS());
			empleadoResponse.setStrResponseMessage("empleado registrado");
			empleadoResponse.setEmpleado(empleadoDto);
		}
		catch(Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
			empleadoResponse.setStrResponseCode(methods.GETERROR());
			empleadoResponse.setStrResponseMessage("error al intentar crear un empleado");
		}
		
		return empleadoResponse;
	}

	@Override
	public EmpleadoResponse updateEmpleado(EmpleadoCreateDto empleadodto, String sessionId) {
		
		EmpleadoResponse empleadoResponse = new EmpleadoResponse();
		
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				empleadoResponse.setStrResponseCode(methods.GETERROR());
				empleadoResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return empleadoResponse;
			}
			
			if(!empleadodto.validarDatos())
			{
				empleadoResponse.setStrResponseCode(methods.GETERROR());
				empleadoResponse.setStrResponseMessage("error, verifique que todos los campos sean correctos.");
				return empleadoResponse;
			}
			
			Optional<EmpleadoEntity> empleado = this.iEmpleadoRepository.findById(empleadodto.getIdEmpleado());
			
			if(empleado.isEmpty())
			{
				empleadoResponse.setStrResponseCode(methods.GETERROR());
				empleadoResponse.setStrResponseMessage("error, empleado no existe");
				return empleadoResponse;
			}
			EmpleadoEntity empleadoEntity = new EmpleadoEntity();
			//PuestoEntity puestoEntity = new PuestoEntity();
			//puestoEntity.setIdPuesto(empleadodto.getIdPuesto());
			
			empleado.get().getPuesto().setIdPuesto(empleadodto.getIdPuesto());
			empleado.get().setIngresoSueldoBase(empleadodto.getIngresoSueldoBase());
			empleado.get().setDescuentoIgss(empleadodto.getDescuentoIgss());
			empleado.get().setDescuentoInasistencias(empleadodto.getDescuentoInasistencias());
			empleado.get().setIngresoBonificacionDecreto(empleadodto.getIngresoBonificacionDecreto());
			empleado.get().setDescuentoIsr(empleadodto.getDescuentoIsr());
			empleado.get().setIngresoOtrosIngresos(empleadodto.getIngresoOtrosIngresos());
			empleado.get().setUsuarioModificacion(sessionInformationResponse.getStrIdUsuario());
			empleado.get().setFechaModificacion(new Date());
		
			empleadoEntity = this.iEmpleadoRepository.save(empleado.get());
			
			EmpleadoDto empleadoDto = new EmpleadoDto(empleadodto.getIdEmpleado(), 
					new PersonaDto(
							empleadoEntity.getPersona().getIdPersona(), 
							empleadoEntity.getPersona().getNombre(), 
							empleadoEntity.getPersona().getApellido(),
							empleadoEntity.getPersona().getFechaNacimiento(),
							empleadoEntity.getPersona().getGenero().getIdGenero(),
							empleadoEntity.getPersona().getGenero().getNombre(),
							empleadoEntity.getPersona().getDireccion(),
							empleadoEntity.getPersona().getTelefono(),
							empleadoEntity.getPersona().getCorreoElectronico(),
							empleadoEntity.getPersona().getEstadoCivil().getNombre(),
							empleadoEntity.getPersona().getEstadoCivil().getIdEstadoCivil()
					),
					new SucursalDto(empleadoEntity.getSucursal().getIdSucursal(), empleadoEntity.getSucursal().getNombre()),
					empleadoEntity.getFechaContratacion(), 
					new PuestoDto(empleadoEntity.getPuesto().getIdPuesto(), empleadoEntity.getPuesto().getNombre(), empleadoEntity.getPuesto().getDepartamento().getIdDepartamento()), 
					empleadoEntity.getStatusEmpleado().getIdStatusEmpleado(),
					empleadoEntity.getIngresoSueldoBase(), 
					empleadoEntity.getIngresoBonificacionDecreto(), 
					empleadoEntity.getIngresoOtrosIngresos(),
					empleadoEntity.getDescuentoIgss(), 
					empleadoEntity.getDescuentoIsr(), 
					empleadoEntity.getDescuentoInasistencias());
			
			empleadoResponse.setStrResponseCode(methods.GETSUCCESS());
			empleadoResponse.setStrResponseMessage("registro actualizado");
			empleadoResponse.setEmpleado(empleadoDto);
			
			return empleadoResponse;
		}
		catch(Exception ex)
		{
			System.out.println("error al actualizar: " + ex.getMessage());
			empleadoResponse.setStrResponseCode(methods.GETERROR());
			empleadoResponse.setStrResponseMessage("error al intentar actualizar el registro");
		}
		return empleadoResponse;
	}

}













