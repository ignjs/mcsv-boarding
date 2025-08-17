package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.MealPlanOption;
import com.eleuthera.mcsv_boarding.repositories.MealPlanOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad MealPlanOption.
 * Contiene la lógica de negocio para la gestión de opciones de planes de comidas.
 */
@Service
public class MealPlanOptionService {

    @Autowired
    private MealPlanOptionRepository mealPlanOptionRepository;

    public MealPlanOption save(MealPlanOption mealPlanOption) {
        return mealPlanOptionRepository.save(mealPlanOption);
    }

    public Optional<MealPlanOption> findById(UUID id) {
        return mealPlanOptionRepository.findById(id);
    }

    public List<MealPlanOption> findAll() {
        return mealPlanOptionRepository.findAll();
    }

    public void deleteById(UUID id) {
        mealPlanOptionRepository.deleteById(id);
    }
}