package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.MealPlanOption;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MealPlanOptionRepository extends JpaRepository<MealPlanOption, UUID> {
}