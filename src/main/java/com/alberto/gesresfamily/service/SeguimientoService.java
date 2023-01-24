package com.alberto.gesresfamily.service;

import com.alberto.gesresfamily.domain.Residente;
import com.alberto.gesresfamily.domain.Seguimiento;
import com.alberto.gesresfamily.domain.dto.SeguimientoDto;
import com.alberto.gesresfamily.exception.ComunicadoNotFoundException;
import com.alberto.gesresfamily.exception.ResidenteNotFoundException;
import com.alberto.gesresfamily.exception.SeguimientoNotFoundException;

import java.util.List;

public interface SeguimientoService {

  Seguimiento addSeguimiento (SeguimientoDto seguimientoDto) throws ResidenteNotFoundException;

  Seguimiento findSeguimiento (long id) throws SeguimientoNotFoundException;

  List<Seguimiento> findAllSeguimientos();

  List<Seguimiento> findAllSeguimientos(long id);

  Seguimiento removeSeguimiento(long id) throws SeguimientoNotFoundException;

  Seguimiento modifySeguimiento(long id, SeguimientoDto seguimientoDto) throws SeguimientoNotFoundException, ResidenteNotFoundException;

  Seguimiento patchSeguimiento(long id, String descripción) throws SeguimientoNotFoundException;

  List<Seguimiento> findSeguimiento(Residente residente);

}
