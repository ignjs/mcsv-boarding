package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.DailyRecordExerciseStatus;
import com.eleuthera.mcsv_boarding.repositories.DailyRecordExerciseStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad DailyRecordExerciseStatus.
 * Contiene la lógica de negocio para la gestión del estado de ejercicios.
 */
@Service
public class DailyRecordExerciseStatusService {

    @Autowired
    private DailyRecordExerciseStatusRepository exerciseStatusRepository;

    public DailyRecordExerciseStatus save(DailyRecordExerciseStatus exerciseStatus) {
        return exerciseStatusRepository.save(exerciseStatus);
    }

    public Optional<DailyRecordExerciseStatus> findById(UUID id) {
        return exerciseStatusRepository.findById(id);
    }

    public List<DailyRecordExerciseStatus> findAll() {
        return exerciseStatusRepository.findAll();
    }

    public void deleteById(UUID id) {
        exerciseStatusRepository.deleteById(id);
    }
}