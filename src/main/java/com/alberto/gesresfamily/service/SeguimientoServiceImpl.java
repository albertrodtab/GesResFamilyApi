package com.alberto.gesresfamily.service;

import com.alberto.gesresfamily.domain.Comunicado;
import com.alberto.gesresfamily.domain.Profesional;
import com.alberto.gesresfamily.domain.Residente;
import com.alberto.gesresfamily.domain.Seguimiento;
import com.alberto.gesresfamily.domain.dto.SeguimientoDto;
import com.alberto.gesresfamily.exception.ComunicadoNotFoundException;
import com.alberto.gesresfamily.exception.ProfesionalNotFoundException;
import com.alberto.gesresfamily.exception.ResidenteNotFoundException;
import com.alberto.gesresfamily.exception.SeguimientoNotFoundException;
import com.alberto.gesresfamily.repository.ResidenteRepository;
import com.alberto.gesresfamily.repository.SeguimientoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SeguimientoServiceImpl implements SeguimientoService{

    private final Logger logger = LoggerFactory.getLogger(SeguimientoServiceImpl.class);

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Autowired
    private ResidenteRepository residenteRepository;

    @Override
    public Seguimiento addSeguimiento (SeguimientoDto seguimientoDto) throws ResidenteNotFoundException {
        Residente residente = residenteRepository.findById(seguimientoDto.getResidente()).
                orElseThrow(ResidenteNotFoundException::new);
        ModelMapper mapper = new ModelMapper();
        Seguimiento seguimiento = mapper.map(seguimientoDto, Seguimiento.class);
        seguimiento.setResidente(residente);
        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public Seguimiento findSeguimiento (long id) throws SeguimientoNotFoundException {
        return seguimientoRepository.findById(id).
                orElseThrow(SeguimientoNotFoundException::new);
    }

    @Override
    public List<Seguimiento> findAllSeguimientos (){
        return seguimientoRepository.findAll();
    }

    @Override
    public List<Seguimiento> findAllSeguimientos (long id){
        return seguimientoRepository.findAllSeguimientosById(id);
    }

    @Override
    public Seguimiento removeSeguimiento (long id) throws SeguimientoNotFoundException {
        Seguimiento seguimiento = seguimientoRepository.findById(id)
                .orElseThrow(SeguimientoNotFoundException::new);
        seguimientoRepository.delete(seguimiento);
        return seguimiento;
    }

    @Override
    public Seguimiento modifySeguimiento (long id, SeguimientoDto seguimientoDto) throws SeguimientoNotFoundException, ResidenteNotFoundException{
        Seguimiento seguimiento = seguimientoRepository.findById(id)
                .orElseThrow(SeguimientoNotFoundException::new);
        Residente residente = residenteRepository.findById(seguimientoDto.getResidente()).
                orElseThrow(ResidenteNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        seguimiento = mapper.map(seguimientoDto, Seguimiento.class);
        seguimiento.setId(id);
        seguimiento.setResidente(residente);
        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public Seguimiento patchSeguimiento (long id, String descripcion) throws SeguimientoNotFoundException {
        Seguimiento seguimiento = seguimientoRepository.findById(id)
                .orElseThrow(SeguimientoNotFoundException::new);
        seguimiento.setDescripcion(descripcion);
        return seguimientoRepository.save(seguimiento);
    }

    @Override
    public List<Seguimiento> findSeguimiento(Residente residente) {
        return seguimientoRepository.findByResidente(residente);
    }


}
