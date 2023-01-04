package com.alberto.gesresfamily.repository;

import com.alberto.gesresfamily.domain.Residente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenteRepository extends CrudRepository<Residente, Long> {
}
