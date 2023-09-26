package com.tec.wsnomina.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.RoleOptionEntity;
import com.tec.wsnomina.entity.key.RoleOptionKey;

@Repository
public interface IRoleOptionRepository  extends JpaRepository<RoleOptionEntity, RoleOptionKey>{

	public List<RoleOptionEntity> findByid_IdRole(int idRole);
	
//	@Query("SELECT r.idOpcion, r.nombre, r.ordenMenu, r.pagina, r.alta FROM RoleOptionEntity r WHERE r.idRole = :paramIdRole")
	//public Set<OptionDto> findOpcionesIdsByIdRole(@Param("paramIdRole") int idRole);
	//public Set<Integer> findOpcionesIdsByIdRole(@Param("paramIdRole") int idRole);
	
	//@Query("SELECT r FROM RoleOptionEntity r WHERE r.idRole = :paramIdRole and r.idOpcion = :paramIdOpcion")
//	public RoleOptionEntity findByIdOpcionAndIdRole(@Param("paramIdRole")int idRole,@Param("paramIdOpcion") int idOpcion);
}
