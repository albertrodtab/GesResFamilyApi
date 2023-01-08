package com.alberto.gesresfamily.repository;

import com.alberto.gesresfamily.domain.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {

    Plan findAllById(long id);

    List<Plan> findAll();

    List<Plan> findAllPlanesById(long id);

    List<Plan> findByIdOrNombrePlanOrImportante(long id, String nombrePlan, boolean importante);
}
