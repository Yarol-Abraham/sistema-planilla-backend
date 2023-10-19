package com.tec.wsnomina.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.MenuDto;
import com.tec.wsnomina.dto.MenuOptionDto;
import com.tec.wsnomina.dto.ModuloDto;
import com.tec.wsnomina.dto.OptionDto;
import com.tec.wsnomina.dto.StatusRoleOpcionDto;
import com.tec.wsnomina.entity.MenuListResponse;
import com.tec.wsnomina.entity.MenuResponse;
import com.tec.wsnomina.entity.RoleOptionEntity;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.repository.IMenuRepository;
import com.tec.wsnomina.repository.IModuloRepository;
import com.tec.wsnomina.repository.IRoleOptionRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private IModuloRepository iModuloRepository;
	
	@Autowired
	private IMenuRepository iMenuRepository;
	
	@Autowired
	private IRoleOptionRepository iRoleOptionRepository;
	
	
	
	// services
	@Autowired
	private SessionServiceImpl sessionServiceImpl;
		
	private Utils utils = new Utils();
	private Methods methods = new Methods();
	private MenuResponse menuResponse = new MenuResponse();
	
	@Override
	public MenuResponse getMenu(String idRole)
	{
		try
		{	
			int idRoleFormat = this.utils.validNumber(this.utils.clean(idRole));

			if(idRoleFormat == -1)
			{
				menuResponse.setStrResponseCode(this.methods.GETERROR());
				menuResponse.setStrResponseMessage("ERROR, rol de usuario no identificado");
				return menuResponse;
			}
			
			List<RoleOptionEntity> opcionesAsociadasAlRol = iRoleOptionRepository.findByid_IdRole(idRoleFormat);
			
			List<ModuloDto> menuModulos = this.iModuloRepository.findAll()
					.stream()
					.map( (modulo) -> 
					  new ModuloDto(modulo.getIdModulo(), modulo.getNombre(), modulo.getOrdenMenu(), 
						modulo.getMenu().stream().map(
								menu -> 
								{
									List<OptionDto> optionsFilter = menu.getOpciones().stream()
											.filter(opcion -> opcionesAsociadasAlRol.stream().anyMatch(
													rolOpcion -> rolOpcion.getId().getIdOpcion() == opcion.getIdOpcion()
													))
											.map( opcion -> {
												
												Optional<StatusRoleOpcionDto> statusOpcion = opcion.getRoleopciones().stream()
														.filter( roleOpcion -> roleOpcion.getId().getIdOpcion() == opcion.getIdOpcion() && roleOpcion.getId().getIdRole() == idRoleFormat )
														.findFirst()
														.map(
															roleopcion -> new StatusRoleOpcionDto(
																	roleopcion.getAlta(), 
																	roleopcion.getBaja(), 
																	roleopcion.getCambio(), 
																	roleopcion.getImprimir(), 
																	roleopcion.getExportar())
														);
												
													return new OptionDto(
															opcion.getIdOpcion(), 
															opcion.getNombre(), 
															opcion.getOrdenMenu(), 
															opcion.getPagina(),
															statusOpcion.get().getAlta(),
															statusOpcion.get().getBaja(),
															statusOpcion.get().getCambio(),
															statusOpcion.get().getImprimir(),
															statusOpcion.get().getExportar()
															);
												}
											).collect(Collectors.toList());
									return new MenuOptionDto(menu.getIdMenu(), menu.getNombre(), menu.getOrdenMenu(), optionsFilter);
								}
								).collect(Collectors.toList())
							)
					).collect(Collectors.toList()); 
			
			
			menuResponse.setStrResponseCode(this.methods.GETSUCCESS());
			menuResponse.setStrResponseMessage("SUCCESS");
			menuResponse.setEntModulo(menuModulos);
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: MenuServiceImpl.getMenu() " + ex.getMessage());
			menuResponse.setStrResponseCode(this.methods.GETERROR());
			menuResponse.setStrResponseMessage("ERROR");
		}
		
		return menuResponse;
	}
	
	@Override
	public MenuListResponse getListMenu(int idModulo, String sessionId)
	{
		MenuListResponse menuListResponse = new MenuListResponse();
		
		try 
		{
			// validate session
			SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
			if(!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS()))
			{
				menuListResponse.setStrResponseCode(methods.GETERROR());
				menuListResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
				return menuListResponse;
			}	
			
			List<MenuDto> list = this.iMenuRepository.getFindAllByIdModulo(idModulo).stream()
					.map( menu -> new MenuDto(menu.getModulo().getIdModulo(), menu.getIdMenu(), menu.getNombre(), menu.getOrdenMenu())
					).collect(Collectors.toList());
			
			menuListResponse.setStrResponseCode(methods.GETSUCCESS());
			menuListResponse.setStrResponseMessage("listado obtenido correctamente");
			menuListResponse.setMenus(list);
			return menuListResponse;
			
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: MenuServiceImpl.getListMenu(): " + ex.getMessage());
			menuListResponse.setStrResponseCode(methods.GETERROR());
			menuListResponse.setStrResponseMessage("Error al obtener el listado del menu");
		}
		return menuListResponse;
	}
	
	
}
