package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.PatientExampleMealPlan;
import com.eleuthera.mcsv_boarding.repositories.PatientExampleMealPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad PatientExampleMealPlan.
 * Contiene la lógica de negocio para la gestión de planes de comidas de ejemplo.
 */
@Service
public class PatientExampleMealPlanService {

    @Autowired
    private PatientExampleMealPlanRepository mealPlanRepository;

    public PatientExampleMealPlan save(PatientExampleMealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    public Optional<PatientExampleMealPlan> findById(UUID id) {
        return mealPlanRepository.findById(id);
    }

    public List<PatientExampleMealPlan> findAll() {
        return mealPlanRepository.findAll();
    }

    public void deleteById(UUID id) {
        mealPlanRepository.deleteById(id);
    }
}
