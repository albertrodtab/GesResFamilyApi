package com.alberto.gesresfamily.controller;

import com.alberto.gesresfamily.domain.Plan;
import com.alberto.gesresfamily.domain.Residente;
import com.alberto.gesresfamily.domain.dto.ParticipaDTO;
import com.alberto.gesresfamily.domain.dto.PlanDto;
import com.alberto.gesresfamily.exception.*;
import com.alberto.gesresfamily.service.PlanService;
import com.alberto.gesresfamily.service.ProfesionalService;
import com.alberto.gesresfamily.service.ResidenteService;
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
public class PlanController {

    private final Logger logger = LoggerFactory.getLogger(PlanController.class);

    @Autowired
    private PlanService planService;
    @Autowired
    private ResidenteService residenteService;
    @Autowired
    private ProfesionalService profesionalService;

    @PostMapping("/planes")
    public ResponseEntity<Plan> addPlan (@RequestBody PlanDto planDto) throws ProfesionalNotFoundException {
        logger.info("Inicio addPlan");
        Plan newPlan = planService.addPlan(planDto);
        logger.info("Fin addPlan");
        return ResponseEntity.status(HttpStatus.CREATED).body(newPlan);
    }

    @PostMapping("/planes/{planId}/residentes/{residenteId}")
    public ResponseEntity<Response> participa (@RequestBody ParticipaDTO participaDTO)
            throws PlanNotFoundException, ResidenteNotFoundException {
        logger.info("Inicio participa");
        Residente residente = residenteService.findResidente(participaDTO.getResidenteId());
        logger.info("Residente encontrado " + residente.getId());
        Plan plan = planService.findPlan(participaDTO.getPlanId());
        logger.info("Plan encontrado " + plan.getId());
        planService.addParticipa(residente, plan);


        Response response = new Response("1", "Residente añadido al Plan " +
                participaDTO.getPlanId());
        logger.info("Fin participa");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/planes/{id}")
    public ResponseEntity<Plan> getPlan (@PathVariable long id) throws PlanNotFoundException {
        Plan plan = planService.findPlan(id);
        return ResponseEntity.ok(plan);
    }

    @GetMapping("/planes")
    public ResponseEntity<List<Plan>> getPlanById (@RequestParam(name = "plan", defaultValue = "0") long id){
        List<Plan> planes;

        if(id == 0){
            planes = planService.findAllPlanes();
        } else {
            planes = planService.findAllPlanesById(id);
        }
        return ResponseEntity.ok(planes);
    }

    @DeleteMapping("/planes/{id}")
    public ResponseEntity<Void> removePlan (@PathVariable long id) throws PlanNotFoundException{
        logger.info("Inicio removePlan");
        Plan plan = planService.removePlan(id);
        logger.info("Fin removePlan");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/planes/{id}")
    public ResponseEntity<Plan> modifyPlan(@RequestBody Plan plan, @PathVariable long id) throws PlanNotFoundException{
        Plan newPlan = planService.modifyPlan(id, plan);
        logger.info("Fin modifyPlan");
        return ResponseEntity.status(HttpStatus.OK).body(newPlan);
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


    @ExceptionHandler(PlanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlanNotFoundException(ProfesionalNotFoundException plnfe) {
        logger.info("404: Plan not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(plnfe.getMessage()));
    }

    @ExceptionHandler(ProfesionalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfesionalNotFoundException(ProfesionalNotFoundException pnfe) {
        logger.info("404: Profesional not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(pnfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.info("500: Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }

}
