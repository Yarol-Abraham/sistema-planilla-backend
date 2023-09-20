package com.tec.wsnomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.SucursalEntity;

@Repository
public interface ISucursalRepository  extends JpaRepository<SucursalEntity, Integer>{}
