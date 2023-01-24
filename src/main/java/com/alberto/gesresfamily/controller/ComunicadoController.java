package com.alberto.gesresfamily.controller;

import com.alberto.gesresfamily.domain.Comunicado;
import com.alberto.gesresfamily.domain.dto.ComunicadoDto;
import com.alberto.gesresfamily.exception.*;
import com.alberto.gesresfamily.service.ComunicadoService;
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
public class ComunicadoController {

    private final Logger logger = LoggerFactory.getLogger(ComunicadoController.class);

    @Autowired
    private ComunicadoService comunicadoService;

    @PostMapping("/comunicados")
    public ResponseEntity<Comunicado> addComunicado (@Valid @RequestBody ComunicadoDto comunicadoDto) throws ProfesionalNotFoundException{
        logger.info("Inicio addComunicado");
        Comunicado newComunicado = comunicadoService.addComunicado(comunicadoDto);
        logger.info("Fin addComunicado");
        return ResponseEntity.status(HttpStatus.CREATED).body(newComunicado);
    }

/*    @GetMapping("/comunicados/{id}")
    public Comunicado getComunicado (@PathVariable long id) throws ComunicadoNotFoundException {
        logger.info("Inicio getComunicado");
        Comunicado comunicado = comunicadoService.findComunicado(id);
        logger.info("Fin getComunicado");
        return comunicado;
    }*/

    @GetMapping("/comunicados")
    public ResponseEntity<List<Comunicado>> getComunicadosById (@RequestParam(name = "comunicado", defaultValue = "0") long id){
        logger.info("Inicio getComunicadosById");
        List<Comunicado> comunicados;

        if(id == 0){
            comunicados = comunicadoService.findAllComunicados();
        } else {
            comunicados = comunicadoService.findAllComunicados(id);
        }
        logger.info("Fin getComunicadosById");
        return ResponseEntity.ok(comunicados);
    }

    @DeleteMapping("/comunicados/{id}")
    public ResponseEntity<Comunicado> removeComunicado (@PathVariable long id) throws ComunicadoNotFoundException {
        logger.info("Inicio removeComunicado");
        Comunicado comunicado = comunicadoService.removeComunicado(id);
        logger.info("Fin removeComunicado");
        return ResponseEntity.ok(comunicado);
    }

    @PutMapping("/comunicados/{id}")
    public ResponseEntity<Comunicado> modifyComunicado(@RequestBody ComunicadoDto comunicadoDto, @PathVariable long id)
            throws ComunicadoNotFoundException, ProfesionalNotFoundException{
        logger.info("Inicio modifyComunicado");
        Comunicado newComunicado = comunicadoService.modifyComunicado(id, comunicadoDto);
        logger.info("Fin modifyComunicado");
        return ResponseEntity.status(HttpStatus.OK).body(newComunicado);
    }

    // Cambiar la descripción de un comunicado
    @PatchMapping("/comunicado/{id}")
    public ResponseEntity<Comunicado> patchComunicado (@PathVariable long id, @RequestBody String descripción)
            throws ComunicadoNotFoundException {
        logger.info("Inicio PatchComunicado " + id);
        Comunicado comunicado = comunicadoService.patchComunicado(id, descripción);
        logger.info("Fin patchComunicado " + id);
        return ResponseEntity.status(HttpStatus.OK).body(comunicado);
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

    @ExceptionHandler(ComunicadoNotFoundException.class)

    public ResponseEntity<ErrorResponse> handleComunicadoNotFoundException(ComunicadoNotFoundException confe) {
        logger.error(confe.getMessage(), confe);
        logger.error(Arrays.toString(confe.getStackTrace()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(confe.getMessage()));
    }

    @ExceptionHandler(ProfesionalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfesionalNotFoundException(ProfesionalNotFoundException pnfe) {
        logger.info("404: Profesional not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(pnfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.error(isee.getMessage(), isee);
        logger.error(Arrays.toString(isee.getStackTrace()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }



}
