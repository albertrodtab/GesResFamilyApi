package com.alberto.gesresfamily.controller;

import com.alberto.gesresfamily.domain.Centro;
import com.alberto.gesresfamily.service.CentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CentroController {

    @Autowired
    private CentroService centroService;


    @PostMapping("/centros")
    public Centro addCentro (@RequestBody Centro centro) {

        Centro newCentro = centroService.addCentro(centro);

        return newCentro;

    }





}
