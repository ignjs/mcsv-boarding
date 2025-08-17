package com.eleuthera.mcsv_boarding.dtos;

import com.eleuthera.mcsv_boarding.models.PatientExpectedGoals;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para la entidad PatientExpectedGoals.
 */
public class PatientExpectedGoalsDto {

    private UUID id;
    private String goalDescription;

    /**
     * Constructor vacío.
     */
    public PatientExpectedGoalsDto() {
    }

    /**
     * Constructor que mapea una entidad PatientExpectedGoals a un DTO.
     * @param expectedGoals La entidad PatientExpectedGoals de la que se obtendrán los datos.
     */
    public PatientExpectedGoalsDto(PatientExpectedGoals expectedGoals) {
        this.id = expectedGoals.getId();
        this.goalDescription = expectedGoals.getGoalDescription();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }
}