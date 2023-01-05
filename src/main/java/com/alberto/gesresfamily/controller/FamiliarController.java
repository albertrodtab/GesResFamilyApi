package com.alberto.gesresfamily.controller;

import com.alberto.gesresfamily.domain.Familiar;
import com.alberto.gesresfamily.exception.*;
import com.alberto.gesresfamily.service.FamiliarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class FamiliarController {

    private final Logger logger = LoggerFactory.getLogger(FamiliarController.class);

    @Autowired
    private FamiliarService familiarService;

    @PostMapping("/familiares")
    public ResponseEntity<Familiar> addFamiliar(@RequestBody Familiar familiar) {
        logger.info("Inicio addFamiliar");
        Familiar newFamiliar = familiarService.addFamiliar(familiar);
        logger.info("Fin addFamiliar");
        return ResponseEntity.status(HttpStatus.CREATED).body(newFamiliar);
    }

    @GetMapping("/familiares/{id}")
    public ResponseEntity<Familiar> getFamiliar (@PathVariable long id) throws FamiliarNotFoundException {
        Familiar familiar = familiarService.findFamiliar(id);
        return ResponseEntity.ok(familiar);
    }

    @GetMapping("/familiares")
    public ResponseEntity<List<Familiar>> getFamiliaresById (@RequestParam(name = "familiar", defaultValue = "0") long id){
        List<Familiar> familiares;

        if(id == 0){
            familiares = familiarService.findAllFamiliares();
        } else {
            familiares = familiarService.findAllFamiliares(id);
        }
        return ResponseEntity.ok(familiares);
    }

    @DeleteMapping("/familiares/{id}")
    public ResponseEntity<Void> removeFamiliar (@PathVariable long id) throws FamiliarNotFoundException{
        logger.info("Inicio removeFamiliar");
        Familiar familiar = familiarService.removeFamiliar(id);
        logger.info("Fin removeFamiliar");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/familiares/{id}")
    public ResponseEntity<Familiar> modifyFamiliar(@RequestBody Familiar familiar, @PathVariable long id) throws FamiliarNotFoundException{
        logger.info("Inicio modifyFamiliar");
        Familiar newFamiliar = familiarService.modifyFamiliar(id, familiar);
        logger.info("Fin modifyFamiliar");
        return ResponseEntity.status(HttpStatus.OK).body(newFamiliar);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        logger.info("400: Argument not valid");
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException bre) {
        logger.info("400: Bad request");
        return ResponseEntity.badRequest().body(ErrorResponse.badRequest(bre.getMessage()));
    }


    @ExceptionHandler(CentroNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFamiliarNotFoundException(FamiliarNotFoundException fnfe) {
        logger.info("404: Familiar not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(fnfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.info("500: Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }
}
