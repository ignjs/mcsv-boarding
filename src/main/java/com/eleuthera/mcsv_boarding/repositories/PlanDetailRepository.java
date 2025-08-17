package com.eleuthera.mcsv_boarding.repositories;

import com.eleuthera.mcsv_boarding.models.PlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PlanDetailRepository extends JpaRepository<PlanDetail, UUID> {
}