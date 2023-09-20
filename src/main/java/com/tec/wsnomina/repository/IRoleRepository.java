package com.tec.wsnomina.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.RoleEntity;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Integer> {
	
}
