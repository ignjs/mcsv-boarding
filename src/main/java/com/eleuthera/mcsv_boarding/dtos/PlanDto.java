package com.eleuthera.mcsv_boarding.dtos;

import com.eleuthera.mcsv_boarding.models.Plan;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para la entidad Plan.
 */
public class PlanDto {

    private UUID id;
    private String name;
    private String description;
    private int durationInDays;

    /**
     * Constructor vacío.
     */
    public PlanDto() {
    }

    /**
     * Constructor que mapea una entidad Plan a un DTO.
     * @param plan La entidad Plan de la que se obtendrán los datos.
     */
    public PlanDto(Plan plan) {
        this.id = plan.getId();
        this.name = plan.getName();
        this.description = plan.getDescription();
        this.durationInDays = plan.getDurationInDays();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }
}