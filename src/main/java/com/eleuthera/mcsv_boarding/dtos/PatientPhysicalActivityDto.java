package com.eleuthera.mcsv_boarding.dtos;

import com.eleuthera.mcsv_boarding.models.PatientPhysicalActivity;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para la entidad PatientPhysicalActivity.
 */
public class PatientPhysicalActivityDto {

    private UUID id;
    private String routineName;
    private String routineDescription;

    /**
     * Constructor vacío.
     */
    public PatientPhysicalActivityDto() {
    }

    /**
     * Constructor que mapea una entidad PatientPhysicalActivity a un DTO.
     * @param physicalActivity La entidad PatientPhysicalActivity de la que se obtendrán los datos.
     */
    public PatientPhysicalActivityDto(PatientPhysicalActivity physicalActivity) {
        this.id = physicalActivity.getId();
        this.routineName = physicalActivity.getRoutineName();
        this.routineDescription = physicalActivity.getRoutineDescription();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }

    public String getRoutineDescription() {
        return routineDescription;
    }

    public void setRoutineDescription(String routineDescription) {
        this.routineDescription = routineDescription;
    }
}