package com.tec.wsnomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.OptionEntity;

@Repository
public interface IOptionRepository extends JpaRepository<OptionEntity, Integer> {
	
	
}
