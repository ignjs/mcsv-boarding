package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CollectionIdMutability;

@Entity
@Table(name = "patient_profiles")
public class PatientProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private User user;

    @OneToMany(mappedBy = "patientProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PatientNutritionalRequirements> nutritionalRequirements;

    @OneToMany(mappedBy = "patientProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PatientFrequencies> frequencies;

    @OneToMany(mappedBy = "patientProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PatientPhysicalActivity> physicalActivities;

    @OneToMany(mappedBy = "patientProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PatientExpectedGoals> expectedGoals;

    @OneToMany(mappedBy = "patientProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DailyRecord> dailyRecords;

    @OneToMany(mappedBy = "patientProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Plan> plans;

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

    public Set<PatientNutritionalRequirements> getNutritionalRequirements() {
        return nutritionalRequirements;
    }

    public void setNutritionalRequirements(Set<PatientNutritionalRequirements> nutritionalRequirements) {
        this.nutritionalRequirements = nutritionalRequirements;
    }

    public Set<PatientFrequencies> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(Set<PatientFrequencies> frequencies) {
        this.frequencies = frequencies;
    }

    public Set<PatientPhysicalActivity> getPhysicalActivities() {
        return physicalActivities;
    }

    public void setPhysicalActivities(Set<PatientPhysicalActivity> physicalActivities) {
        this.physicalActivities = physicalActivities;
    }

    public Set<PatientExpectedGoals> getExpectedGoals() {
        return expectedGoals;
    }

    public void setExpectedGoals(Set<PatientExpectedGoals> expectedGoals) {
        this.expectedGoals = expectedGoals;
    }

    public Set<DailyRecord> getDailyRecords() {
        return dailyRecords;
    }

    public void setDailyRecords(Set<DailyRecord> dailyRecords) {
        this.dailyRecords = dailyRecords;
    }

    public Set<Plan> getPlans() {
        return plans;
    }

    public void setPlans(Set<Plan> plans) {
        this.plans = plans;
    }
}