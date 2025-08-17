// Archivo: src/main/java/com/eleuthera/mcsv_boarding/dto/MealPlanOptionDto.java
package com.eleuthera.mcsv_boarding.dtos;

import com.eleuthera.mcsv_boarding.models.MealPlanOption;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para la entidad MealPlanOption.
 * Esta clase se utiliza para transferir datos entre las capas de la aplicación,
 * desacoplando la capa de la base de datos de la capa de presentación/API.
 */
public class MealPlanOptionDto {

    private UUID id;
    private String optionDescription;

    /**
     * Constructor vacío.
     */
    public MealPlanOptionDto() {
    }

    /**
     * Constructor que mapea una entidad MealPlanOption a un DTO.
     * @param mealPlanOption La entidad MealPlanOption de la que se obtendrán los datos.
     */
    public MealPlanOptionDto(MealPlanOption mealPlanOption) {
        this.id = mealPlanOption.getId();
        this.optionDescription = mealPlanOption.getOptionDescription();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }
}