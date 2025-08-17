package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.PatientPhysicalActivity;
import com.eleuthera.mcsv_boarding.repositories.PatientPhysicalActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad PatientPhysicalActivity.
 * Contiene la lógica de negocio para la gestión de actividad física del paciente.
 */
@Service
public class PatientPhysicalActivityService {

    @Autowired
    private PatientPhysicalActivityRepository physicalActivityRepository;

    public PatientPhysicalActivity save(PatientPhysicalActivity physicalActivity) {
        return physicalActivityRepository.save(physicalActivity);
    }

    public Optional<PatientPhysicalActivity> findById(UUID id) {
        return physicalActivityRepository.findById(id);
    }

    public List<PatientPhysicalActivity> findAll() {
        return physicalActivityRepository.findAll();
    }

    public void deleteById(UUID id) {
        physicalActivityRepository.deleteById(id);
    }
}