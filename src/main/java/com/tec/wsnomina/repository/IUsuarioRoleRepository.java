package com.tec.wsnomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.UsuarioRoleEntity;
import com.tec.wsnomina.entity.key.UsuarioRoleKey;

@Repository
public interface IUsuarioRoleRepository extends JpaRepository<UsuarioRoleEntity, UsuarioRoleKey> {

//	List<Optional<UsuarioRoleEntity>> findAllByIdUsuario(String idUsuario);
	
	
}
