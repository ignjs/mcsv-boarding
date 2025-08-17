package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.PatientPhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientPhysicalActivityRepository extends JpaRepository<PatientPhysicalActivity, UUID> {
}