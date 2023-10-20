package com.tec.wsnomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.EmpleadoEntity;

@Repository
public interface IEmpleadoRepository  extends JpaRepository<EmpleadoEntity, Integer> {

}
