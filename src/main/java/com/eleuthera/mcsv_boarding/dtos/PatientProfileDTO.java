package com.eleuthera.mcsv_boarding.dtos;

import java.time.LocalDate;
import java.util.UUID;

import com.eleuthera.mcsv_boarding.models.PatientProfile;

/**
 * Data Transfer Object para la entidad PatientProfile.
 */
public class PatientProfileDTO {

    // Clase interna para representar el usuario de forma simple
    public static class UserDTO {
        private UUID id;
        private String name;
        private String email;

        public UserDTO(UUID id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public UUID getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    private UUID id;
    private LocalDate birthdate;
    private double height;
    private double weight;
    private double bodyMassIndex;
    private String nutritionalStatus;
    private String pathologies;
    private String medications;
    private String objective;
    private String physicalActivity;
    private UserDTO user; // Objeto anidado para los datos del usuario

    /**
     * Constructor vacío.
     */
    public PatientProfileDTO() {
    }

    /**
     * Constructor que mapea una entidad PatientProfile a un DTO.
     * 
     * @param patientProfile La entidad PatientProfile de la que se obtendrán los
     *                       datos.
     */
    public PatientProfileDTO(PatientProfile patientProfile) {
        // Mapea los datos del usuario al objeto anidado si la entidad User no es nula
        if (patientProfile.getUser() != null) {
            this.user = new UserDTO(
                    patientProfile.getUser().getId(),
                    patientProfile.getUser().getName(),
                    patientProfile.getUser().getEmail());
        }
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}