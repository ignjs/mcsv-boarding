package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.PatientExampleMealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientExampleMealPlanRepository extends JpaRepository<PatientExampleMealPlan, UUID> {
}