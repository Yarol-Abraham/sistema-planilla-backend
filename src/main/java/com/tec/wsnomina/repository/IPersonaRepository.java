package com.tec.wsnomina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tec.wsnomina.entity.PersonaEntity;

@Repository
public interface IPersonaRepository extends JpaRepository<PersonaEntity, Integer> {

}
