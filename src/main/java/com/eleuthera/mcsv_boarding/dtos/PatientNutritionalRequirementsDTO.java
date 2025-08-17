package com.eleuthera.mcsv_boarding.dtos;

import java.util.UUID;

import com.eleuthera.mcsv_boarding.models.PatientNutritionalRequirements;

public class PatientNutritionalRequirementsDTO {

    private UUID id;
    private double energyKcal;
    private double proteinsGrams;
    private double lipidsGrams;
    private double carbohydratesGrams;
    private double waterVolume;

    /**
     * Constructor vacío.
     */
    public PatientNutritionalRequirementsDTO() {
    }

    /**
     * Constructor que mapea una entidad PatientNutritionalRequirements a un DTO.
     * @param requirements La entidad PatientNutritionalRequirements de la que se obtendrán los datos.
     */
    public PatientNutritionalRequirementsDTO(PatientNutritionalRequirements requirements) {
        this.id = requirements.getId();
        this.energyKcal = requirements.getEnergyKcal();
        this.proteinsGrams = requirements.getProteinsGrams();
        this.lipidsGrams = requirements.getLipidsGrams();
        this.carbohydratesGrams = requirements.getCarbohydratesGrams();
        this.waterVolume = requirements.getWaterVolume();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getEnergyKcal() {
        return energyKcal;
    }

    public void setEnergyKcal(double energyKcal) {
        this.energyKcal = energyKcal;
    }

    public double getProteinsGrams() {
        return proteinsGrams;
    }

    public void setProteinsGrams(double proteinsGrams) {
        this.proteinsGrams = proteinsGrams;
    }

    public double getLipidsGrams() {
        return lipidsGrams;
    }

    public void setLipidsGrams(double lipidsGrams) {
        this.lipidsGrams = lipidsGrams;
    }

    public double getCarbohydratesGrams() {
        return carbohydratesGrams;
    }

    public void setCarbohydratesGrams(double carbohydratesGrams) {
        this.carbohydratesGrams = carbohydratesGrams;
    }

    public double getWaterVolume() {
        return waterVolume;
    }

    public void setWaterVolume(double waterVolume) {
        this.waterVolume = waterVolume;
    }
}