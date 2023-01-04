package com.alberto.gesresfamily.repository;

import com.alberto.gesresfamily.domain.Centro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroRepository extends CrudRepository<Centro, Long> {


}

