package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "daily_record_exercise_statuses")
public class DailyRecordExerciseStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String exerciseType;
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_record_id")
    private DailyRecord dailyRecord;

    public DailyRecordExerciseStatus() {
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public DailyRecord getDailyRecord() {
        return dailyRecord;
    }

    public void setDailyRecord(DailyRecord dailyRecord) {
        this.dailyRecord = dailyRecord;
    }
}