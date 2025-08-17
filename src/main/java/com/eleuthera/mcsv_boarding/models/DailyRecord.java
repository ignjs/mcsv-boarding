package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Set;

@Entity
@Table(name = "daily_records")
public class DailyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDate date;
    private double calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_profile_id")
    private PatientProfile patientProfile;

    @OneToMany(mappedBy = "dailyRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DailyRecordMealStatus> mealStatuses;

    @OneToMany(mappedBy = "dailyRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DailyRecordExerciseStatus> exerciseStatuses;

    public DailyRecord() {
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

    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    public Set<DailyRecordMealStatus> getMealStatuses() {
        return mealStatuses;
    }

    public void setMealStatuses(Set<DailyRecordMealStatus> mealStatuses) {
        this.mealStatuses = mealStatuses;
    }

    public Set<DailyRecordExerciseStatus> getExerciseStatuses() {
        return exerciseStatuses;
    }

    public void setExerciseStatuses(Set<DailyRecordExerciseStatus> exerciseStatuses) {
        this.exerciseStatuses = exerciseStatuses;
    }
}