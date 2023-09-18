package com.tec.wsnomina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.UsuarioEntity;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, String> {
	
	Optional<UsuarioEntity> findByCorreoElectronico(String correoElectronico);
	
}
