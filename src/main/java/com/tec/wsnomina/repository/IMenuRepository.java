package com.tec.wsnomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.MenuEntity;
@Repository
public interface IMenuRepository extends JpaRepository<MenuEntity, Integer>{
	
	
}
