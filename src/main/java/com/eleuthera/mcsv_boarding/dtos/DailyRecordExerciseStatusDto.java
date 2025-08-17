package com.eleuthera.mcsv_boarding.dtos;

import com.eleuthera.mcsv_boarding.models.DailyRecordExerciseStatus;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para la entidad DailyRecordExerciseStatus.
 */
public class DailyRecordExerciseStatusDto {

    private UUID id;
    private String exerciseType;
    private boolean completed;

    /**
     * Constructor vacío.
     */
    public DailyRecordExerciseStatusDto() {
    }

    /**
     * Constructor que mapea una entidad DailyRecordExerciseStatus a un DTO.
     * @param exerciseStatus La entidad DailyRecordExerciseStatus de la que se obtendrán los datos.
     */
    public DailyRecordExerciseStatusDto(DailyRecordExerciseStatus exerciseStatus) {
        this.id = exerciseStatus.getId();
        this.exerciseType = exerciseStatus.getExerciseType();
        this.completed = exerciseStatus.isCompleted();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}