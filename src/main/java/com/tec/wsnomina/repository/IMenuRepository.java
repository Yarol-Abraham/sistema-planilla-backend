package com.tec.wsnomina.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tec.wsnomina.entity.MenuEntity;

@Repository
public interface IMenuRepository extends JpaRepository<MenuEntity, Integer>{
	
	@Query("SELECT m FROM MenuEntity m WHERE m.modulo.idModulo = :idModulo")
	public ArrayList<MenuEntity> getFindAllByIdModulo(@Param("idModulo") int idModulo);
}
