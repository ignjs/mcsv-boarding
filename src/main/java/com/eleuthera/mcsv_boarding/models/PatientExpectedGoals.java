package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "patient_expected_goals")
public class PatientExpectedGoals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String goalDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_profile_id")
    private PatientProfile patientProfile;

    public PatientExpectedGoals() {
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }
}
