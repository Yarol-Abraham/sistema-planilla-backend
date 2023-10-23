package com.tec.wsnomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tec.wsnomina.entity.DepartamentoEntity;

@Repository
public interface IDepartamentoRepository extends JpaRepository<DepartamentoEntity, Integer> {

}
