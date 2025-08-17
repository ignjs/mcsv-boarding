package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.PatientProfileDTO;
import com.eleuthera.mcsv_boarding.models.PatientProfile;
import com.eleuthera.mcsv_boarding.services.PatientProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controlador REST para la entidad PatientProfile.
 */
@RestController
@RequestMapping("/api/patient-profiles")
@Tag(name = "Patient Profiles", description = "Gestión de perfiles de pacientes")
public class PatientProfileController {

    @Autowired
    private PatientProfileService patientProfileService;

    @Operation(summary = "Obtener todos los perfiles de pacientes", description = "Devuelve una lista de todos los perfiles de pacientes registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de perfiles de pacientes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientProfileDTO.class)))
    @GetMapping
    public List<PatientProfileDTO> getAllPatientProfiles() {
        return patientProfileService.findAll().stream()
                .map(PatientProfileDTO::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un perfil de paciente por ID", description = "Busca un perfil de paciente específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Perfil de paciente encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientProfileDTO.class)))
    @ApiResponse(responseCode = "404", description = "Perfil de paciente no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PatientProfileDTO> getPatientProfileById(@Parameter(description = "ID único del perfil del paciente") @PathVariable UUID id) {
        Optional<PatientProfile> profileOptional = patientProfileService.findById(id);
        return profileOptional.map(profile -> ResponseEntity.ok(new PatientProfileDTO(profile)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo perfil de paciente", description = "Crea un nuevo perfil de paciente en la base de datos.")
    @ApiResponse(responseCode = "201", description = "Perfil de paciente creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientProfileDTO.class)))
    @PostMapping
    public ResponseEntity<PatientProfileDTO> createPatientProfile(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO del perfil del paciente a crear") @RequestBody PatientProfileDTO profileDto) {
        PatientProfile profile = new PatientProfile();
        // Mapeo del DTO a la entidad
        profile.setBirthdate(profileDto.getBirthdate());
        profile.setHeight(profileDto.getHeight());
        profile.setWeight(profileDto.getWeight());
        profile.setBodyMassIndex(profileDto.getBodyMassIndex());
        profile.setNutritionalStatus(profileDto.getNutritionalStatus());
        profile.setPathologies(profileDto.getPathologies());
        profile.setMedications(profileDto.getMedications());


        PatientProfile savedProfile = patientProfileService.save(profile);
        return new ResponseEntity<>(new PatientProfileDTO(savedProfile), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un perfil de paciente existente", description = "Actualiza la información de un perfil de paciente basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Perfil de paciente actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientProfileDTO.class)))
    @ApiResponse(responseCode = "404", description = "Perfil de paciente no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<PatientProfileDTO> updatePatientProfile(@Parameter(description = "ID del perfil del paciente a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados del perfil del paciente") @RequestBody PatientProfileDTO profileDto) {
        Optional<PatientProfile> profileOptional = patientProfileService.findById(id);
        if (profileOptional.isPresent()) {
            PatientProfile profile = profileOptional.get();
            profile.setBirthdate(profileDto.getBirthdate());
            profile.setHeight(profileDto.getHeight());
            profile.setWeight(profileDto.getWeight());
            profile.setBodyMassIndex(profileDto.getBodyMassIndex());
            profile.setNutritionalStatus(profileDto.getNutritionalStatus());
            profile.setPathologies(profileDto.getPathologies());
            profile.setMedications(profileDto.getMedications());

            PatientProfile updatedProfile = patientProfileService.save(profile);
            return ResponseEntity.ok(new PatientProfileDTO(updatedProfile));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un perfil de paciente por ID", description = "Elimina un perfil de paciente de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Perfil de paciente eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Perfil de paciente no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientProfile(@Parameter(description = "ID del perfil del paciente a eliminar") @PathVariable UUID id) {
        if (patientProfileService.findById(id).isPresent()) {
            patientProfileService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}