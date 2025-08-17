package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.util.UUID;
import java.util.Set;

@Entity
@Table(name = "patient_example_meal_plans")
public class PatientExampleMealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String mealType;
    private String mealDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_detail_id")
    private PlanDetail planDetail;

    @OneToMany(mappedBy = "patientExampleMealPlan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MealPlanOption> mealOptions;

    public PatientExampleMealPlan() {
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

    public PlanDetail getPlanDetail() {
        return planDetail;
    }

    public void setPlanDetail(PlanDetail planDetail) {
        this.planDetail = planDetail;
    }

    public Set<MealPlanOption> getMealOptions() {
        return mealOptions;
    }

    public void setMealOptions(Set<MealPlanOption> mealOptions) {
        this.mealOptions = mealOptions;
    }
}