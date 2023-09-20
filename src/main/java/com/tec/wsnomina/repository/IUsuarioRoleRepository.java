package com.tec.wsnomina.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.UsuarioRoleEntity;

@Repository
public interface IUsuarioRoleRepository extends JpaRepository<UsuarioRoleEntity, String> {

	List<Optional<UsuarioRoleEntity>> findAllByIdUsuario(String idUsuario);
	
}
