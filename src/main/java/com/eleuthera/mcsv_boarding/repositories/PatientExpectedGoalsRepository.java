package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.PatientExpectedGoals;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientExpectedGoalsRepository extends JpaRepository<PatientExpectedGoals, UUID> {
}