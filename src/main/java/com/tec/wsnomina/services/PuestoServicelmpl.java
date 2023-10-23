package com.tec.wsnomina.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.DepartamentoDto;
import com.tec.wsnomina.dto.PuestoCreateDto;
import com.tec.wsnomina.dto.PuestoDepartamentoDto;
import com.tec.wsnomina.dto.PuestoDto;
import com.tec.wsnomina.entity.DepartamentoEntity;
import com.tec.wsnomina.entity.PuestoEntity;
import com.tec.wsnomina.entity.PuestoListDeparmentResponse;
import com.tec.wsnomina.entity.PuestoResponse;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.repository.IDepartamentoRepository;
import com.tec.wsnomina.repository.IPuestoRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class PuestoServicelmpl implements PuestoService {

	@Autowired
	private IPuestoRepository iPuestoRepository;
	
	@Autowired
	private IDepartamentoRepository iDepartamentoRepository;
	
	@Autowired 
	private SessionServiceImpl sessionServiceImpl;
	
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	
	@Override
	public PuestoListDeparmentResponse getPositions(int idDepartamento, String sessionId)
	{
		PuestoListDeparmentResponse puestoListResponse = new PuestoListDeparmentResponse();
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean( sessionId ));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				puestoListResponse.setStrResponseCode(methods.GETERROR());
				puestoListResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return puestoListResponse;
			}
			
			List<PuestoDto> puestos = this.iPuestoRepository.findByDepartamentoIdDepartamento(idDepartamento)
									.stream()
									.map( puesto -> new PuestoDto( puesto.getIdPuesto(), puesto.getNombre(), puesto.getDepartamento().getIdDepartamento()) )
									.collect(Collectors.toList());
			
			puestoListResponse.setStrResponseCode(methods.GETSUCCESS());
			puestoListResponse.setStrResponseMessage("datos obtenidos");
			puestoListResponse.setPuestos(puestos);
			return puestoListResponse;
		}
		catch(Exception ex)
		{
			puestoListResponse.setStrResponseCode(methods.GETERROR());
			puestoListResponse.setStrResponseMessage("error, no se puede obtener los puestos");
		}
		return puestoListResponse;
	}

	@Override
	public PuestoResponse createPosition(PuestoCreateDto puestoCreateDto, String sessionId) 
	{
		
		PuestoResponse puestoResponse = new PuestoResponse();
		
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean( sessionId ));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				puestoResponse.setStrResponseCode(methods.GETERROR());
				puestoResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return puestoResponse;
			}
			
			if(!puestoCreateDto.validate())
			{
				puestoResponse.setStrResponseCode(methods.GETERROR());
				puestoResponse.setStrResponseMessage("error, verifique que todos los campos sean correctos.");
				return puestoResponse;
			}
			
			Optional<DepartamentoEntity> departamento = this.iDepartamentoRepository.findById(puestoCreateDto.getIdDepartamento());
			
			PuestoEntity puesto = new PuestoEntity();
			puesto.setDepartamento(departamento.get());
			puesto.setNombre(puestoCreateDto.getNombre());
			puesto.setFechaCreacion(new Date());
			puesto.setUsuarioCreacion(sessionInformationResponse.getStrIdUsuario());
			
			puesto = this.iPuestoRepository.save(puesto);
			PuestoDepartamentoDto puestoDto = new PuestoDepartamentoDto(puesto.getIdPuesto(),puesto.getNombre(),
												new DepartamentoDto(puesto.getDepartamento().getIdDepartamento(), puesto.getDepartamento().getNombre())
											);
			
			puestoResponse.setStrResponseCode(methods.GETERROR());
			puestoResponse.setStrResponseMessage("puesto creado exitosamente");
			puestoResponse.setPuesto(puestoDto);
			
		}
		catch(Exception ex)
		{
			puestoResponse.setStrResponseCode(methods.GETERROR());
			puestoResponse.setStrResponseMessage("error al crear el puesto");
		}
		
		return puestoResponse;
	}

	@Override
	public PuestoResponse updatePosition(PuestoDepartamentoDto puestoCreateDto, String sessionId) 
	{
		PuestoResponse puestoResponse = new PuestoResponse();
		
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean( sessionId ));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				puestoResponse.setStrResponseCode(methods.GETERROR());
				puestoResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return puestoResponse;
			}
			
			if(!puestoCreateDto.validate())
			{
				puestoResponse.setStrResponseCode(methods.GETERROR());
				puestoResponse.setStrResponseMessage("error, verifique que todos los campos sean correctos.");
				return puestoResponse;
			}
			
			Optional<PuestoEntity> puestoEntity = this.iPuestoRepository.findById(puestoCreateDto.getIdPuesto());
			
			if(puestoEntity.isEmpty())
			{
				puestoResponse.setStrResponseCode(methods.GETERROR());
				puestoResponse.setStrResponseMessage("error, el puesto no existe");
				return puestoResponse;
			}
			
			puestoEntity.get().getDepartamento().setIdDepartamento(puestoCreateDto.getDepartamento().getIdDepartamento());
			puestoEntity.get().setNombre(puestoCreateDto.getNombre());
			
			puestoResponse.setStrResponseCode(methods.GETSUCCESS());
			puestoResponse.setStrResponseMessage("puesto actualizado correctamente");
			puestoResponse.setPuesto(puestoCreateDto);
		}
		catch(Exception ex)
		{
			puestoResponse.setStrResponseCode(methods.GETERROR());
			puestoResponse.setStrResponseMessage("error al actualizar el puesto actual");
		}
		return puestoResponse;
	}

	
	
}
