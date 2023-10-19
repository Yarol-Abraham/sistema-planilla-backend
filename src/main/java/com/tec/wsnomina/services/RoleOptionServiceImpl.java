package com.tec.wsnomina.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.OptionDto;
import com.tec.wsnomina.dto.RoleOpcionCreateDto;
import com.tec.wsnomina.entity.RoleListOpcionResponse;
import com.tec.wsnomina.entity.RoleOpcionResponse;
import com.tec.wsnomina.entity.RoleOptionEntity;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.entity.key.RoleOptionKey;
import com.tec.wsnomina.repository.IOptionRepository;
import com.tec.wsnomina.repository.IRoleOptionRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class RoleOptionServiceImpl implements RoleOptionService {

	@Autowired
	private IRoleOptionRepository iRoleOptionRepository;
	
	@Autowired
	private IOptionRepository iOptionRepository;
	
	// services
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
			
	// utilities
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	
	@Override
	public RoleListOpcionResponse getUnassignedOptions(String idRole, String sessionId) {
		
		RoleListOpcionResponse roleListOpcionResponse = new RoleListOpcionResponse();
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				roleListOpcionResponse.setStrResponseCode(methods.GETERROR());
				roleListOpcionResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				roleListOpcionResponse.setOption(null);
				return roleListOpcionResponse;
			}

			int formatIdRole = this.utils.validNumber(idRole);
			
			if(idRole == null || idRole.trim().equals("") || (formatIdRole == -1 || formatIdRole == 0) )
			{
				roleListOpcionResponse.setStrResponseCode(methods.GETERROR());
				roleListOpcionResponse.setStrResponseMessage("error, rol no seleccionado");
				roleListOpcionResponse.setOption(null);
				return roleListOpcionResponse;
			}
			
			List<OptionDto> options = this.iOptionRepository.findOptionsNotAssignedToRole(formatIdRole)
					.stream()
					.map(
						option -> new OptionDto(option.getIdOpcion(), option.getNombre(), option.getOrdenMenu(), option.getPagina())
					).collect(Collectors.toList());
			
			roleListOpcionResponse.setStrResponseCode(methods.GETSUCCESS());
			roleListOpcionResponse.setStrResponseMessage("");
			roleListOpcionResponse.setOption(options);
			
		}
		catch(Exception ex)
		{
			roleListOpcionResponse.setStrResponseCode(methods.GETERROR());
			roleListOpcionResponse.setStrResponseMessage("error al obtener las opciones");
			roleListOpcionResponse.setOption(null);
		}
		return roleListOpcionResponse;
	}

	@Override
	public RoleOpcionResponse grantPermission(RoleOpcionCreateDto roleOpcionCreate, String sessionId) {
		
		RoleOpcionResponse roleOpcionResponse = new RoleOpcionResponse();
		
		try
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				roleOpcionResponse.setStrResponseCode(methods.GETERROR());
				roleOpcionResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				roleOpcionResponse.setOption(null);
				return roleOpcionResponse;
			}
			
			RoleOptionEntity role = new RoleOptionEntity();
			RoleOptionKey roleOptionKey = new RoleOptionKey();
			
			int formatIdRole = this.utils.validNumber(roleOpcionCreate.getIdRole());
			int formatIdOpcion = this.utils.validNumber(roleOpcionCreate.getIdOpcion());
			int formatAlta = this.utils.validNumber(roleOpcionCreate.getAlta());
			int formatBaja = this.utils.validNumber(roleOpcionCreate.getBaja());
			int formatCambio = this.utils.validNumber(roleOpcionCreate.getCambio());
			int formatImprimir = this.utils.validNumber(roleOpcionCreate.getImprimir());
			int formatExportar = this.utils.validNumber(roleOpcionCreate.getExportar());
			
			if( (formatIdRole == 0 || formatIdRole == -1 ) || (formatIdRole == 0 || formatIdRole == -1 )  )
			{
				roleOpcionResponse.setStrResponseCode(this.methods.GETERROR());
				roleOpcionResponse.setStrResponseMessage("error, opcion o rol no válidos");
				return roleOpcionResponse;
			}
			roleOptionKey.setIdOpcion(formatIdOpcion);
			roleOptionKey.setIdRole(formatIdRole);
			role.setId(roleOptionKey);
			role.setFechaCreacion(this.utils.getFechaHoraFormateada());
			role.setUsuarioCreacion(sessionInformationResponse.getStrIdUsuario());
			role.setAlta(formatAlta);
			role.setBaja(formatBaja);
			role.setCambio(formatCambio);
			role.setExportar(formatExportar);
			role.setImprimir(formatImprimir);
			
			role = this.iRoleOptionRepository.save(role);
			
			roleOpcionResponse.setStrResponseCode(this.methods.GETSUCCESS());
			roleOpcionResponse.setStrResponseMessage("opciones asigandos exitosamente");
		}
		catch(Exception ex)
		{
			System.out.println("Error: " + ex);
			roleOpcionResponse.setStrResponseCode(this.methods.GETERROR());
			roleOpcionResponse.setStrResponseMessage("error, no se puede asignar la opcion al rol");
		}
		
		return roleOpcionResponse;
	}

}
















