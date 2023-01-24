package com.alberto.gesresfamily.controller;

import com.alberto.gesresfamily.domain.Comunicado;
import com.alberto.gesresfamily.domain.Seguimiento;
import com.alberto.gesresfamily.domain.dto.ComunicadoDto;
import com.alberto.gesresfamily.domain.dto.SeguimientoDto;
import com.alberto.gesresfamily.exception.*;
import com.alberto.gesresfamily.service.ComunicadoService;
import com.alberto.gesresfamily.service.SeguimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SeguimientoController {

    private final Logger logger = LoggerFactory.getLogger(SeguimientoController.class);

    @Autowired
    private SeguimientoService seguimientoService;



    @PostMapping("/seguimientos")
    public ResponseEntity<Seguimiento> addSeguimiento (@Valid @RequestBody SeguimientoDto seguimientoDto) throws ResidenteNotFoundException{
        logger.info("Inicio addSeguimiento");
        Seguimiento newSeguimiento = seguimientoService.addSeguimiento(seguimientoDto);
        logger.info("Fin addSeguimiento");
        return ResponseEntity.status(HttpStatus.CREATED).body(newSeguimiento);
    }


    @GetMapping("/seguimientos")
    public ResponseEntity<List<Seguimiento>> getSeguimientosById (@RequestParam(name = "seguimiento", defaultValue = "0") long id){
        logger.info("Inicio getSeguimientosById");
        List<Seguimiento> seguimientos;

        if(id == 0){
            seguimientos = seguimientoService.findAllSeguimientos();
        } else {
            seguimientos = seguimientoService.findAllSeguimientos(id);
        }
        logger.info("Fin getSeguimientosById");
        return ResponseEntity.ok(seguimientos);
    }

    @DeleteMapping("/seguimientos/{id}")
    public ResponseEntity<Seguimiento> removeSeguimiento (@PathVariable long id) throws SeguimientoNotFoundException {
        logger.info("Inicio removeseguimiento");
        Seguimiento seguimiento = seguimientoService.removeSeguimiento(id);
        logger.info("Fin removeSeguimiento");
        return ResponseEntity.ok(seguimiento);
    }

    @PutMapping("/seguimientos/{id}")
    public ResponseEntity<Seguimiento> modifySeguimiento (@RequestBody SeguimientoDto seguimientoDto, @PathVariable long id)
            throws SeguimientoNotFoundException, ResidenteNotFoundException{
        logger.info("Inicio modifySeguimiento");
        Seguimiento newSeguimiento = seguimientoService.modifySeguimiento(id, seguimientoDto);
        logger.info("Fin modifySeguimiento");
        return ResponseEntity.status(HttpStatus.OK).body(newSeguimiento);
    }

    // Cambiar la descripción de un seguimiento
    @PatchMapping("/seguimiento/{id}")
    public ResponseEntity<Seguimiento> patchSeguimiento (@PathVariable long id, @RequestBody String descripción)
            throws SeguimientoNotFoundException {
        logger.info("Inicio PatchSeguimiento " + id);
        Seguimiento seguimiento = seguimientoService.patchSeguimiento(id, descripción);
        logger.info("Fin patchSeguimiento " + id);
        return ResponseEntity.status(HttpStatus.OK).body(seguimiento);
    }

    //creo también un método que capture la excepción y la devuelve un poco más elegante


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException manve) {
        logger.info("400: Argument not valid");
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        logger.error(manve.getMessage(), manve);
        logger.error(Arrays.toString(manve.getStackTrace()));
        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException bre) {
        logger.error(bre.getMessage(), bre);
        logger.error(Arrays.toString(bre.getStackTrace()));
        return ResponseEntity.badRequest().body(ErrorResponse.badRequest(bre.getMessage()));
    }

    @ExceptionHandler(SeguimientoNotFoundException.class)

    public ResponseEntity<ErrorResponse> handleSeguimientoNotFoundException(SeguimientoNotFoundException snfe) {
        logger.error(snfe.getMessage(), snfe);
        logger.error(Arrays.toString(snfe.getStackTrace()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(snfe.getMessage()));
    }

    @ExceptionHandler(ResidenteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResidenteNotFoundException(ResidenteNotFoundException rnfe) {
        logger.info("404: Residente not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(rnfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.error(isee.getMessage(), isee);
        logger.error(Arrays.toString(isee.getStackTrace()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }



}
