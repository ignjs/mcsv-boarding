package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.PatientDailyPortions;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientDailyPortionsRepository extends JpaRepository<PatientDailyPortions, UUID> {
}