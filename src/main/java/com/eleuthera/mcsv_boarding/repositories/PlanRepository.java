package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PlanRepository extends JpaRepository<Plan, UUID> {
}