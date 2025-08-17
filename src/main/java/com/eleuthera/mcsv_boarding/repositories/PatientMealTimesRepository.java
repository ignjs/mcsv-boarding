package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.PatientMealTimes;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientMealTimesRepository extends JpaRepository<PatientMealTimes, UUID> {
}