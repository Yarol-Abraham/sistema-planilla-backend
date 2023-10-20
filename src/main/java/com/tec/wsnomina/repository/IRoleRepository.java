package com.tec.wsnomina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tec.wsnomina.entity.RoleEntity;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Integer> {

	 @Query("SELECT r FROM RoleEntity r WHERE r NOT IN (SELECT ur FROM UsuarioEntity u JOIN u.roles ur WHERE u.idUsuario = :idUsuario)")
	 List<RoleEntity> obtenerRolesNoAsignadosAlUsuario(String idUsuario);
	
	 @Query("SELECT r FROM RoleEntity r WHERE r IN (SELECT ur FROM UsuarioEntity u JOIN u.roles ur WHERE u.idUsuario = :idUsuario)")
	 List<RoleEntity> obtenerRolesAsignadosAlUsuario(String idUsuario);
	
}
