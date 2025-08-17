package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "patient_frequencies")
public class PatientFrequencies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String defecationFrequency;
    private String urinationFrequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_profile_id")
    private PatientProfile patientProfile;

    public PatientFrequencies() {
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDefecationFrequency() {
        return defecationFrequency;
    }

    public void setDefecationFrequency(String defecationFrequency) {
        this.defecationFrequency = defecationFrequency;
    }

    public String getUrinationFrequency() {
        return urinationFrequency;
    }

    public void setUrinationFrequency(String urinationFrequency) {
        this.urinationFrequency = urinationFrequency;
    }

    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }
}