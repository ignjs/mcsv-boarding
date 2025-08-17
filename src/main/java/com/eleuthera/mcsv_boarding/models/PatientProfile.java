package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "patient_profiles")
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    @Column(name = "birth_date")
    private LocalDate birthdate;
    @Column(name = "evaluation_date")
    private LocalDate evaluationDate;
    @Column(name = "height_cm")
    private double height;
    @Column(name = "weight_kg")
    private double weight;
    @Column(name = "waist_circumference_cm")
    private Double waistCircumference;
    @Column(name = "bmi")
    private double bodyMassIndex;
    @Column(name = "nutritional_status")
    private String nutritionalStatus;
    @Column(name = "pathologies")
    private String pathologies;
    @Column(name = "medications")
    private String medications;
    @Column(name = "created_at")
    private LocalDate createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @JsonBackReference
    @JsonIgnore // Aislamos la serialización de este campo para evitar problemas con el proxy de Hibernate.
    private User user;

    public PatientProfile() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}