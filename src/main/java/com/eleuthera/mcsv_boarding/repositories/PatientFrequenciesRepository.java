package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.PatientFrequencies;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientFrequenciesRepository extends JpaRepository<PatientFrequencies, UUID> {
}