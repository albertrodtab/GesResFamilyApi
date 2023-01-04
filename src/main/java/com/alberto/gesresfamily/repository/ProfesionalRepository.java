package com.alberto.gesresfamily.repository;

import com.alberto.gesresfamily.domain.Profesional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalRepository extends CrudRepository<Profesional, Long> {
}
