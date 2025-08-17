package com.eleuthera.mcsv_boarding.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.eleuthera.mcsv_boarding.models.DailyRecord;

/**
 * Data Transfer Object para la entidad DailyRecord.
 */
public class DailyRecordDTO {

    private UUID id;
    private LocalDate date;
    private double calories;

    /**
     * Constructor vacío.
     */
    public DailyRecordDTO() {
    }

    /**
     * Constructor que mapea una entidad DailyRecord a un DTO.
     * @param dailyRecord La entidad DailyRecord de la que se obtendrán los datos.
     */
    public DailyRecordDTO(DailyRecord dailyRecord) {
        this.id = dailyRecord.getId();
        this.date = dailyRecord.getDate();
        this.calories = dailyRecord.getCalories();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}