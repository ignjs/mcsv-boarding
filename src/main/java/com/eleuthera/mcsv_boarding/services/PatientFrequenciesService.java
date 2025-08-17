package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.PatientFrequencies;
import com.eleuthera.mcsv_boarding.repositories.PatientFrequenciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad PatientFrequencies.
 * Contiene la lógica de negocio para la gestión de frecuencias del paciente.
 */
@Service
public class PatientFrequenciesService {

    @Autowired
    private PatientFrequenciesRepository frequenciesRepository;

    public PatientFrequencies save(PatientFrequencies frequencies) {
        return frequenciesRepository.save(frequencies);
    }

    public Optional<PatientFrequencies> findById(UUID id) {
        return frequenciesRepository.findById(id);
    }

    public List<PatientFrequencies> findAll() {
        return frequenciesRepository.findAll();
    }

    public void deleteById(UUID id) {
        frequenciesRepository.deleteById(id);
    }
}