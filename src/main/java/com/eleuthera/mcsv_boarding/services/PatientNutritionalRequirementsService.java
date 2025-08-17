package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.PatientNutritionalRequirements;
import com.eleuthera.mcsv_boarding.repositories.PatientNutritionalRequirementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad PatientNutritionalRequirements.
 * Contiene la lógica de negocio para la gestión de requerimientos nutricionales.
 */
@Service
public class PatientNutritionalRequirementsService {

    @Autowired
    private PatientNutritionalRequirementsRepository requirementsRepository;

    public PatientNutritionalRequirements save(PatientNutritionalRequirements requirements) {
        return requirementsRepository.save(requirements);
    }

    public Optional<PatientNutritionalRequirements> findById(UUID id) {
        return requirementsRepository.findById(id);
    }

    public List<PatientNutritionalRequirements> findAll() {
        return requirementsRepository.findAll();
    }

    public void deleteById(UUID id) {
        requirementsRepository.deleteById(id);
    }
}