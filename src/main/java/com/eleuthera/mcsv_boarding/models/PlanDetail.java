package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "plan_details")
public class PlanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String dayName;
    private LocalDate planDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @OneToMany(mappedBy = "planDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PatientExampleMealPlan> mealPlans;

    public PlanDetail() {
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public LocalDate getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDate planDate) {
        this.planDate = planDate;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Set<PatientExampleMealPlan> getMealPlans() {
        return mealPlans;
    }

    public void setMealPlans(Set<PatientExampleMealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }
}