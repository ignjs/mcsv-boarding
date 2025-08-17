package com.eleuthera.mcsv_boarding.dtos;

import com.eleuthera.mcsv_boarding.models.DailyRecordMealStatus;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para la entidad DailyRecordMealStatus.
 */
public class DailyRecordMealStatusDto {

    private UUID id;
    private String mealType;
    private boolean consumed;

    /**
     * Constructor vacío.
     */
    public DailyRecordMealStatusDto() {
    }

    /**
     * Constructor que mapea una entidad DailyRecordMealStatus a un DTO.
     * @param mealStatus La entidad DailyRecordMealStatus de la que se obtendrán los datos.
     */
    public DailyRecordMealStatusDto(DailyRecordMealStatus mealStatus) {
        this.id = mealStatus.getId();
        this.mealType = mealStatus.getMealType();
        this.consumed = mealStatus.isConsumed();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }
}