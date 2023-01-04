package com.alberto.gesresfamily.repository;

import com.alberto.gesresfamily.domain.Familiar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliarRepository extends CrudRepository<Familiar, Long> {
}
