package com.alberto.gesresfamily.service;

import com.alberto.gesresfamily.domain.Comunicado;
import com.alberto.gesresfamily.domain.Profesional;
import com.alberto.gesresfamily.domain.dto.ComunicadoDto;
import com.alberto.gesresfamily.exception.ComunicadoNotFoundException;
import com.alberto.gesresfamily.exception.ProfesionalNotFoundException;

import java.util.List;

public interface ComunicadoService {

  Comunicado addComunicado(ComunicadoDto comunicadoDto) throws ProfesionalNotFoundException;

  Comunicado findComunicado(long id) throws ComunicadoNotFoundException;

  List<Comunicado> findAllComunicados();

  List<Comunicado> findAllComunicados(long id);

  Comunicado removeComunicado(long id) throws ComunicadoNotFoundException;

  Comunicado modifyComunicado(long id, ComunicadoDto comunicadoDto) throws ComunicadoNotFoundException, ProfesionalNotFoundException;

  Comunicado patchComunicado(long id, String descripción) throws ComunicadoNotFoundException;

  List<Comunicado> findComunicado(Profesional profesional);

}
