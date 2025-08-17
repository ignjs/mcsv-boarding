package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.DailyRecordMealStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DailyRecordMealStatusRepository extends JpaRepository<DailyRecordMealStatus, UUID> {
}