package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.PatientExpectedGoals;
import com.eleuthera.mcsv_boarding.repositories.PatientExpectedGoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad PatientExpectedGoals.
 * Contiene la lógica de negocio para la gestión de objetivos del paciente.
 */
@Service
public class PatientExpectedGoalsService {

    @Autowired
    private PatientExpectedGoalsRepository expectedGoalsRepository;

    public PatientExpectedGoals save(PatientExpectedGoals expectedGoals) {
        return expectedGoalsRepository.save(expectedGoals);
    }

    public Optional<PatientExpectedGoals> findById(UUID id) {
        return expectedGoalsRepository.findById(id);
    }

    public List<PatientExpectedGoals> findAll() {
        return expectedGoalsRepository.findAll();
    }

    public void deleteById(UUID id) {
        expectedGoalsRepository.deleteById(id);
    }
}