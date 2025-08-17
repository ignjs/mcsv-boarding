package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import com.eleuthera.mcsv_boarding.models.Plan;

/**
 * Servicio para la entidad Plan.
 * Contiene la lógica de negocio para la gestión de planes.
 */
@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    public Optional<Plan> findById(UUID id) {
        return planRepository.findById(id);
    }

    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    public void deleteById(UUID id) {
        planRepository.deleteById(id);
    }
}