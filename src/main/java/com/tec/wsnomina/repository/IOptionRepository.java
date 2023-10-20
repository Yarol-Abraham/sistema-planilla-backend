package com.tec.wsnomina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.OptionEntity;

@Repository
public interface IOptionRepository extends JpaRepository<OptionEntity, Integer> {
	
	@Query("SELECT o FROM OptionEntity o WHERE o.idOpcion NOT IN(SELECT r.opcion.idOpcion FROM o.roleopciones r WHERE r.id.idRole = :idRole )")
	List<OptionEntity> findOptionsNotAssignedToRole(@Param("idRole") int idRole);
	
	@Query("SELECT o FROM OptionEntity o WHERE o.idOpcion IN(SELECT r.opcion.idOpcion FROM o.roleopciones r WHERE r.id.idRole = :idRole )")
	List<OptionEntity> findOptionsAssignedToRole(@Param("idRole") int idRole);
	
}
