package com.alberto.gesresfamily.repository;

import com.alberto.gesresfamily.domain.Centro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentroRepository extends CrudRepository<Centro, Long> {

    List<Centro> findAll();

    List<Centro> findAllCentrosById(long id);

    List<Centro> findByNombreOrNumRegistroOrEmail(String nombre, String numRegistro, String email);
}


