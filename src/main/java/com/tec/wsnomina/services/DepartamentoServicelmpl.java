package com.tec.wsnomina.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.DepartamentoDto;
import com.tec.wsnomina.entity.DepartamentoListResponse;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.repository.IDepartamentoRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class DepartamentoServicelmpl implements DepartamentoService {

	@Autowired
	private IDepartamentoRepository iDepartamentoRepository;
	
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
	
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	
	@Override
	public DepartamentoListResponse getDeparments(String sessionId) 
	{
		DepartamentoListResponse departamentoListResponse = new DepartamentoListResponse();
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				departamentoListResponse.setStrResponseCode(methods.GETERROR());
				departamentoListResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return departamentoListResponse;
			}
			
			List<DepartamentoDto> departamentos = this.iDepartamentoRepository.findAll()
								  .stream()
								  .map(departamento -> new DepartamentoDto(departamento.getIdDepartamento(), departamento.getNombre()))
								  .collect(Collectors.toList());
			
			
			departamentoListResponse.setStrResponseCode(methods.GETSUCCESS());
			departamentoListResponse.setStrResponseMessage("datos obtenidos correctamente");
			departamentoListResponse.setDepartamentos(departamentos);
			
			return departamentoListResponse;
		}
		catch(Exception ex)
		{
			departamentoListResponse.setStrResponseCode(methods.GETERROR());
			departamentoListResponse.setStrResponseMessage("error al obtener los departamentos");
		}
		return departamentoListResponse;
	}

}
