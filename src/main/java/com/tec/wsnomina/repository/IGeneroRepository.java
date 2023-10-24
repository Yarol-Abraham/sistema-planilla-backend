package com.tec.wsnomina.repository;

import com.tec.wsnomina.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IGeneroRepository extends JpaRepository<GeneroEntity, Integer> {}