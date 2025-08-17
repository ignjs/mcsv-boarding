package com.eleuthera.mcsv_boarding.dtos;

import com.eleuthera.mcsv_boarding.models.PlanDetail;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para la entidad PlanDetail.
 */
public class PlanDetailDto {

    private UUID id;
    private String dayName;
    private LocalDate planDate;

    /**
     * Constructor vacío.
     */
    public PlanDetailDto() {
    }

    /**
     * Constructor que mapea una entidad PlanDetail a un DTO.
     * @param planDetail La entidad PlanDetail de la que se obtendrán los datos.
     */
    public PlanDetailDto(PlanDetail planDetail) {
        this.id = planDetail.getId();
        this.dayName = planDetail.getDayName();
        this.planDate = planDetail.getPlanDate();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public LocalDate getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDate planDate) {
        this.planDate = planDate;
    }
}