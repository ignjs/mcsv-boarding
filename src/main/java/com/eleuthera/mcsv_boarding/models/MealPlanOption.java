package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "meal_plan_options")
public class MealPlanOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String optionDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_example_meal_plan_id")
    private PatientExampleMealPlan patientExampleMealPlan;

    public MealPlanOption() {
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public PatientExampleMealPlan getPatientExampleMealPlan() {
        return patientExampleMealPlan;
    }

    public void setPatientExampleMealPlan(PatientExampleMealPlan patientExampleMealPlan) {
        this.patientExampleMealPlan = patientExampleMealPlan;
    }
}