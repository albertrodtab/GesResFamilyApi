package com.alberto.gesresfamily.service;

import com.alberto.gesresfamily.domain.Plan;
import com.alberto.gesresfamily.exception.PlanNotFoundException;

import java.util.List;

public interface PlanService {

    Plan addPlan(Plan plan);

    Plan findPlan(long id) throws PlanNotFoundException;

    List<Plan> findAllPlanes();

    List<Plan> findAllPlanesById(long id);

    Plan removePlan(long id) throws PlanNotFoundException;

    Plan modifyPlan(long id, Plan plan) throws PlanNotFoundException;


}
