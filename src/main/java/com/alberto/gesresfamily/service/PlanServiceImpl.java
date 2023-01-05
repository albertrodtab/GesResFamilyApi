package com.alberto.gesresfamily.service;

import com.alberto.gesresfamily.domain.Plan;
import com.alberto.gesresfamily.exception.PlanNotFoundException;
import com.alberto.gesresfamily.repository.PlanRepository;
import com.alberto.gesresfamily.repository.ProfesionalRepository;
import com.alberto.gesresfamily.repository.ResidenteRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService{

    private final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private ResidenteRepository residenteRepository;
    @Autowired
    private ProfesionalRepository profesionalRepository;

    @Override
    public Plan addPlan(Plan plan){
        return planRepository.save(plan);
    }

    @Override
    public Plan findPlan(long id) throws PlanNotFoundException {
        return planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
    }

    @Override
    public List<Plan> findAllPlanes() {
        return planRepository.findAll();
    }

    @Override
    public List<Plan> findAllPlanesById(long id) {
        return planRepository.findAllPlanesById(id);
    }

    @Override
    public Plan removePlan(long id) throws PlanNotFoundException{
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
        planRepository.delete(plan);
        return plan;
    }

    @Override
    public Plan modifyPlan(long id, Plan newPlan) throws PlanNotFoundException{
        Plan ExistingPlan = planRepository.findById(id).
                orElseThrow(PlanNotFoundException::new);
        /*
         * Con ModelMapper evito escribir todos los getters y setters pero debo incluir el id tambien en Json
         * para que no me cree un nuevo familiar y si realice la modificación sobre el familiar indicado.
         */
        ModelMapper mapper = new ModelMapper();
        ExistingPlan = mapper.map(newPlan, Plan.class);
//        ExistingPlan.setNombrePlan(newPlan.getNombrePlan());
//        ExistingPlan.setTerapia(newPlan.getTerapia());
//        ExistingPlan.setDescripcion(newPlan.getDescripcion());
//        ExistingPlan.setFechaInicio(newPlan.getFechaInicio());
//        ExistingPlan.setFechaFin(newPlan.getFechaFin());
//        ExistingPlan.setImportante(newPlan.getImportante());
        return planRepository.save(ExistingPlan);
    }
}
