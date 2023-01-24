package com.alberto.gesresfamily.repository;

import com.alberto.gesresfamily.domain.Comunicado;
import com.alberto.gesresfamily.domain.Residente;
import com.alberto.gesresfamily.domain.Seguimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoRepository extends CrudRepository<Seguimiento, Long> {


    List<Seguimiento> findAllSeguimientosById(long id);

    List<Seguimiento> findAll();


    List<Seguimiento> findByResidente(Residente residente);


}

