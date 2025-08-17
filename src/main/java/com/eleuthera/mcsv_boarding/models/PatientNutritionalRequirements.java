package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "patient_nutritional_requirements")
public class PatientNutritionalRequirements {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private double energyKcal;
    private double proteinsGrams;
    private double lipidsGrams;
    private double carbohydratesGrams;
    private double waterVolume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_profile_id")
    private PatientProfile patientProfile;

    public PatientNutritionalRequirements() {
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

    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }
}