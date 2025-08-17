package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "physical_activity_routines")
public class PhysicalActivityRoutine {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physical_activity_id", referencedColumnName = "id")
    private PatientPhysicalActivity physicalActivity;

    @Column(name = "activity")
    private String activity;

    @Column(name = "duration_min")
    private Integer durationMin;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PatientPhysicalActivity getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(PatientPhysicalActivity physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Integer getDurationMin() {
        return durationMin;
    }

    public void setDurationMin(Integer durationMin) {
        this.durationMin = durationMin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}
