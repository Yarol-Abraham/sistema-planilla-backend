package com.tec.wsnomina.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.RoleOptionEntity;

@Repository
public interface IRoleOptionRepository  extends JpaRepository<RoleOptionEntity, Integer>{

	@Query("SELECT r.idOpcion FROM RoleOptionEntity r WHERE r.idRole = :paramIdRole")
	public Set<Integer> findOpcionesIdsByIdRole(@Param("paramIdRole") int idRole);
}
