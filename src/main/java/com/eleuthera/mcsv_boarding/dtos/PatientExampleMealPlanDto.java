package com.eleuthera.mcsv_boarding.dtos;

import com.eleuthera.mcsv_boarding.models.PatientExampleMealPlan;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para la entidad PatientExampleMealPlan.
 */
public class PatientExampleMealPlanDto {

    private UUID id;
    private String mealType;
    private String mealDescription;

    /**
     * Constructor vacío.
     */
    public PatientExampleMealPlanDto() {
    }

    /**
     * Constructor que mapea una entidad PatientExampleMealPlan a un DTO.
     * @param mealPlan La entidad PatientExampleMealPlan de la que se obtendrán los datos.
     */
    public PatientExampleMealPlanDto(PatientExampleMealPlan mealPlan) {
        this.id = mealPlan.getId();
        this.mealType = mealPlan.getMealType();
        this.mealDescription = mealPlan.getMealDescription();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getMealDescription() {
        return mealDescription;
    }

    public void setMealDescription(String mealDescription) {
        this.mealDescription = mealDescription;
    }
}