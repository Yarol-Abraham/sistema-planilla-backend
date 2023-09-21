package com.tec.wsnomina.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.MenuDto;
import com.tec.wsnomina.dto.ModuloDto;
import com.tec.wsnomina.dto.OptionDto;
import com.tec.wsnomina.entity.MenuResponse;
import com.tec.wsnomina.repository.IModuloRepository;
import com.tec.wsnomina.repository.IRoleOptionRepository;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private IModuloRepository iModuloRepository;
	
	
	@Autowired
	private IRoleOptionRepository iRoleOptionRepository;
	
	private MenuResponse menuResponse = new MenuResponse();
	
	public MenuResponse getMenu()
	{
		try
		{	
			Set<Integer> opcionesAsociadasAlRol = iRoleOptionRepository.findOpcionesIdsByIdRole(1);
			
			List<ModuloDto> menuModulos = this.iModuloRepository.findAll()
					.stream()
					.map( (modulo) -> 
					  new ModuloDto(modulo.getIdModulo(), modulo.getNombre(), modulo.getOrdenMenu(), 
						modulo.getMenu().stream().map(
								
								menu -> 
								{
									List<OptionDto> optionsFilter = menu.getOpciones().stream()
											.filter(opcion -> opcionesAsociadasAlRol.contains(opcion.getIdOpcion()))
											.map(
												opcion -> new OptionDto(opcion.getIdOpcion(), opcion.getNombre(), opcion.getOrdenMenu(), opcion.getPagina())
											).collect(Collectors.toList());
									return new MenuDto(menu.getIdMenu(), menu.getNombre(), menu.getOrdenMenu(), optionsFilter);
								}
								
								).collect(Collectors.toList())
							)
					).collect(Collectors.toList());
			
			menuResponse.setEntModulo(menuModulos);
		}
		catch(Exception ex)
		{
			System.out.println("ERROR EN: MenuServiceImpl.getMenu() " + ex.getMessage());
		}
		
		return menuResponse;
	}
	
}
