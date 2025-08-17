package com.eleuthera.mcsv_boarding.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.eleuthera.mcsv_boarding.models.PatientProfile;

/**
 * Data Transfer Object para la entidad PatientProfile.
 */
public class PatientProfileDTO {

    private UUID id;
    private String name;
    private String gender;
    private LocalDate birthdate;
    private double height;
    private double weight;
    private double bodyMassIndex;
    private String nutritionalStatus;
    private String pathologies;
    private String medications;
    private String objective;
    private String physicalActivity;

    /**
     * Constructor vacío.
     */
    public PatientProfileDTO() {
    }

    /**
     * Constructor que mapea una entidad PatientProfile a un DTO.
     * @param patientProfile La entidad PatientProfile de la que se obtendrán los datos.
     */
    public PatientProfileDTO(PatientProfile patientProfile) {
        this.id = patientProfile.getId();
        this.birthdate = patientProfile.getBirthdate();
        this.height = patientProfile.getHeight();
        this.weight = patientProfile.getWeight();
        this.bodyMassIndex = patientProfile.getBodyMassIndex();
        this.nutritionalStatus = patientProfile.getNutritionalStatus();
        this.pathologies = patientProfile.getPathologies();
        this.medications = patientProfile.getMedications();
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBodyMassIndex() {
        return bodyMassIndex;
    }

    public void setBodyMassIndex(double bodyMassIndex) {
        this.bodyMassIndex = bodyMassIndex;
    }

    public String getNutritionalStatus() {
        return nutritionalStatus;
    }

    public void setNutritionalStatus(String nutritionalStatus) {
        this.nutritionalStatus = nutritionalStatus;
    }

    public String getPathologies() {
        return pathologies;
    }

    public void setPathologies(String pathologies) {
        this.pathologies = pathologies;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(String physicalActivity) {
        this.physicalActivity = physicalActivity;
    }
}