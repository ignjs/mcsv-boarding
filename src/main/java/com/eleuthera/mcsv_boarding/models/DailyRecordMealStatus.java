package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "daily_record_meal_statuses")
public class DailyRecordMealStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String mealType;
    private boolean consumed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_record_id")
    private DailyRecord dailyRecord;

    public DailyRecordMealStatus() {
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public DailyRecord getDailyRecord() {
        return dailyRecord;
    }

    public void setDailyRecord(DailyRecord dailyRecord) {
        this.dailyRecord = dailyRecord;
    }
}