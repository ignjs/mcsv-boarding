package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.PatientNutritionalRequirements;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientNutritionalRequirementsRepository extends JpaRepository<PatientNutritionalRequirements, UUID> {
}