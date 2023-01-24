package com.alberto.gesresfamily.repository;

import com.alberto.gesresfamily.domain.Comunicado;
import com.alberto.gesresfamily.domain.Profesional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunicadoRepository extends CrudRepository<Comunicado, Long> {


    List<Comunicado> findAllComunicadosById(long id);

    List<Comunicado> findAll();


    List<Comunicado> findByProfesional(Profesional profesional);


}

