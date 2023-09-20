package com.tec.wsnomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.ModuloEntity;

@Repository
public interface IModuloRepository extends JpaRepository<ModuloEntity, Integer> {}
