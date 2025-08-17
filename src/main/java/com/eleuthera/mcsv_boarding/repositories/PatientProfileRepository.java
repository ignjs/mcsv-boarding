package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, UUID> {
}