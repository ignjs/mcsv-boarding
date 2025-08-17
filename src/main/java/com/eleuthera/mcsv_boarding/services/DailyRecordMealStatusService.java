package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.DailyRecordMealStatus;
import com.eleuthera.mcsv_boarding.repositories.DailyRecordMealStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad DailyRecordMealStatus.
 * Contiene la lógica de negocio para la gestión del estado de comidas.
 */
@Service
public class DailyRecordMealStatusService {

    @Autowired
    private DailyRecordMealStatusRepository mealStatusRepository;

    public DailyRecordMealStatus save(DailyRecordMealStatus mealStatus) {
        return mealStatusRepository.save(mealStatus);
    }

    public Optional<DailyRecordMealStatus> findById(UUID id) {
        return mealStatusRepository.findById(id);
    }

    public List<DailyRecordMealStatus> findAll() {
        return mealStatusRepository.findAll();
    }

    public void deleteById(UUID id) {
        mealStatusRepository.deleteById(id);
    }
}