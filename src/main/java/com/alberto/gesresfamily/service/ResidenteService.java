package com.alberto.gesresfamily.service;

import com.alberto.gesresfamily.domain.Residente;
import com.alberto.gesresfamily.exception.ResidenteNotFoundException;

import java.util.List;

public interface ResidenteService {

    Residente addResidente(Residente residente);

    Residente findResidente(long id) throws ResidenteNotFoundException;

    List<Residente> findAllResidentes();

    List<Residente> findAllResidentes(long id);

    Residente removeResidente(long id) throws ResidenteNotFoundException;

    Residente modifyResidente(long id, Residente residente) throws ResidenteNotFoundException;

}
