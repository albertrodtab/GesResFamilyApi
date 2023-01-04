package com.alberto.gesresfamily.service;

import com.alberto.gesresfamily.domain.Centro;
import com.alberto.gesresfamily.repository.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CentroServiceImpl implements CentroService {

    @Autowired
    private CentroRepository centroRepository;

    @Override
    public Centro addCentro(Centro centro) {
        return centroRepository.save(centro);
    }
}
