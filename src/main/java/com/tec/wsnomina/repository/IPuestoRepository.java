package com.tec.wsnomina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tec.wsnomina.entity.PuestoEntity;

public interface IPuestoRepository extends JpaRepository<PuestoEntity, Integer> {

	 List<PuestoEntity> findByDepartamentoIdDepartamento(int idDepartamento);
	
}
