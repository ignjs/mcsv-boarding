package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.dtos.PatientProfileDTO;
import com.eleuthera.mcsv_boarding.dtos.UserDTO;
import com.eleuthera.mcsv_boarding.models.*;
import com.eleuthera.mcsv_boarding.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para manejar el flujo de registro y onboarding de pacientes.
 * Este servicio orquesta la creación de un nuevo usuario y su perfil de paciente.
 */
@Service
public class OnboardingService {

    private final UserRepository userRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final PatientNutritionalRequirementsRepository nutritionalRequirementsRepository;
    private final PatientFrequenciesRepository frequenciesRepository;
    private final PatientPhysicalActivityRepository physicalActivityRepository;
    private final PatientExpectedGoalsRepository expectedGoalsRepository;

    @Autowired
    public OnboardingService(UserRepository userRepository,
                             PatientProfileRepository patientProfileRepository,
                             PatientNutritionalRequirementsRepository nutritionalRequirementsRepository,
                             PatientFrequenciesRepository frequenciesRepository,
                             PatientPhysicalActivityRepository physicalActivityRepository,
                             PatientExpectedGoalsRepository expectedGoalsRepository) {
        this.userRepository = userRepository;
        this.patientProfileRepository = patientProfileRepository;
        this.nutritionalRequirementsRepository = nutritionalRequirementsRepository;
        this.frequenciesRepository = frequenciesRepository;
        this.physicalActivityRepository = physicalActivityRepository;
        this.expectedGoalsRepository = expectedGoalsRepository;
    }

    /**
     * Procesa el flujo completo de registro y onboarding de un nuevo paciente.
     * @param userDTO Objeto DTO con los datos del usuario.
     * @param profileDTO Objeto DTO con los datos del perfil del paciente.
     * @param nutritionalRequirements Requerimientos nutricionales del paciente.
     * @param frequencies Frecuencias del paciente.
     * @param physicalActivity Actividad física del paciente.
     * @param expectedGoals Objetivos esperados del paciente.
     * @return El perfil de paciente creado.
     */
    @Transactional
    public PatientProfile onboardPatient(UserDTO userDTO, PatientProfileDTO profileDTO,
                                         PatientNutritionalRequirements nutritionalRequirements,
                                         PatientFrequencies frequencies,
                                         PatientPhysicalActivity physicalActivity,
                                         PatientExpectedGoals expectedGoals) {
        // 1. Crear y guardar el usuario
        User user = new User();
        user.setName(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);

        // 2. Crear y guardar el perfil del paciente
        PatientProfile patientProfile = new PatientProfile();
        patientProfile.setUser(user);
        patientProfile.setBirthdate(profileDTO.getBirthdate());
        patientProfile.setHeight(profileDTO.getHeight());
        patientProfile.setWeight(profileDTO.getWeight());
        patientProfile.setBodyMassIndex(profileDTO.getBodyMassIndex());
        patientProfile.setNutritionalStatus(profileDTO.getNutritionalStatus());
        patientProfile.setPathologies(profileDTO.getPathologies());
        patientProfile.setMedications(profileDTO.getMedications());
        patientProfileRepository.save(patientProfile);

        // 3. Registrar la información nutricional, de frecuencias, actividad física y objetivos
        nutritionalRequirements.setPatientProfile(patientProfile);
        nutritionalRequirementsRepository.save(nutritionalRequirements);

        frequencies.setPatientProfile(patientProfile);
        frequenciesRepository.save(frequencies);

        physicalActivity.setPatientProfile(patientProfile);
        physicalActivityRepository.save(physicalActivity);

        expectedGoals.setPatientProfile(patientProfile);
        expectedGoalsRepository.save(expectedGoals);

        return patientProfile;
    }
}