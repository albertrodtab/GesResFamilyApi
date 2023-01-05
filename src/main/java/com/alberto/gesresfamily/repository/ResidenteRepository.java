package com.alberto.gesresfamily.repository;

import com.alberto.gesresfamily.domain.Residente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidenteRepository extends CrudRepository<Residente, Long> {

    Residente findAllById(long id);

    List<Residente> findAll();

    List<Residente> findAllResidentesById(long id);
}
