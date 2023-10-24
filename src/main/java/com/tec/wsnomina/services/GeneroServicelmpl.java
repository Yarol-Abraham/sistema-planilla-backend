package com.tec.wsnomina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tec.wsnomina.dto.GeneroDto;
import com.tec.wsnomina.entity.GeneroEntity;
import com.tec.wsnomina.entity.GeneroResponse;
import com.tec.wsnomina.entity.SessionInformationResponse;
import com.tec.wsnomina.repository.IGeneroRepository;
import com.tec.wsnomina.utils.Methods;
import com.tec.wsnomina.utils.Utils;

@Service
public class GeneroServicelmpl implements GeneroService {
	
 	@Autowired
    private IGeneroRepository generoRepository;

 	@Autowired
 	private SessionServiceImpl sessionServiceImpl;
 	
    private Utils utils = new Utils();
    private Methods methods = new Methods();

    @Override
    public GeneroResponse createGender(GeneroDto generoDto, String sessionId) 
    {
        GeneroResponse generoResponse = new GeneroResponse();

        try {
            // Validar sesión
            SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
            
            if (!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS())) 
            {
                generoResponse.setStrResponseCode(methods.GETERROR());
                generoResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
                return generoResponse;
            }

            GeneroEntity generoEntity = new GeneroEntity();
            generoEntity.setIdGenero(generoDto.getIdGenero());
            generoEntity.setNombre(generoDto.getNombre());
            generoEntity = generoRepository.save(generoEntity);

            generoDto.setIdGenero(generoEntity.getIdGenero());
                       
            generoResponse.setStrResponseCode(methods.GETSUCCESS());
            generoResponse.setStrResponseMessage("Género Creado");
            
        } catch (Exception ex) {
            System.out.println("ERROR EN: createGenero() " + ex.getMessage());
            generoResponse.setStrResponseCode(methods.GETERROR());
            generoResponse.setStrResponseMessage("Error al crear el Género");
        }
        return generoResponse;
    }

    @Override
    public GeneroResponse updateGender(GeneroDto generoDto, String sessionId) 
    {
        GeneroResponse generoResponse = new GeneroResponse();

        try {
            SessionInformationResponse sessionInformationResponse = this.sessionServiceImpl.getByInformationUserSesion(this.utils.clean(sessionId));
            if (!sessionInformationResponse.getStrResponseCode().equals(this.methods.GETSUCCESS())) 
            {
                generoResponse.setStrResponseCode(methods.GETERROR());
                generoResponse.setStrResponseMessage(sessionInformationResponse.getStrResponseMessage());
                return generoResponse;
            }
            
            GeneroEntity generoEntity = new GeneroEntity();
            generoEntity.setIdGenero(generoDto.getIdGenero());
            generoEntity.setNombre(generoDto.getNombre());

            generoEntity = generoRepository.save(generoEntity);

            generoResponse.setStrResponseCode(methods.GETSUCCESS());
            generoResponse.setStrResponseMessage("Género Actualizado");
            
        } catch (Exception ex) {
            System.out.println("ERROR EN: updateGenero() " + ex.getMessage());
            generoResponse.setStrResponseCode(methods.GETERROR());
            generoResponse.setStrResponseMessage("Error al actualizar el Género");
        }
        return generoResponse;
    }

	@Override
	public List<GeneroDto> getGender(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}
}
