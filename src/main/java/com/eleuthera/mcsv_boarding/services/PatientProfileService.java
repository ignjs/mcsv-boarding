package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.PatientProfile;
import com.eleuthera.mcsv_boarding.repositories.PatientProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad PatientProfile.
 * Contiene la lógica de negocio para la gestión de perfiles de pacientes.
 */
@Service
public class PatientProfileService {

    @Autowired
    private PatientProfileRepository patientProfileRepository;

    /**
     * Guarda un nuevo perfil de paciente o actualiza uno existente.
     * @param patientProfile El objeto PatientProfile a guardar.
     * @return El perfil de paciente guardado.
     */
    public PatientProfile save(PatientProfile patientProfile) {
        return patientProfileRepository.save(patientProfile);
    }

    /**
     * Busca un perfil de paciente por su ID.
     * @param id El UUID del perfil de paciente.
     * @return Un Optional que contiene el perfil si se encuentra.
     */
    public Optional<PatientProfile> findById(UUID id) {
        return patientProfileRepository.findById(id);
    }

    /**
     * Obtiene una lista de todos los perfiles de pacientes.
     * @return Una lista de objetos PatientProfile.
     */
    public List<PatientProfile> findAll() {
        return patientProfileRepository.findAll();
    }

    /**
     * Elimina un perfil de paciente por su ID.
     * @param id El UUID del perfil de paciente a eliminar.
     */
    public void deleteById(UUID id) {
        patientProfileRepository.deleteById(id);
    }
}