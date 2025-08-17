package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.PatientPhysicalActivityDto;
import com.eleuthera.mcsv_boarding.models.PatientPhysicalActivity;
import com.eleuthera.mcsv_boarding.services.PatientPhysicalActivityService;
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
 * Controlador REST para la entidad PatientPhysicalActivity.
 */
@RestController
@RequestMapping("/api/physical-activities")
@Tag(name = "Physical Activities", description = "Gestión de la actividad física del paciente")
public class PatientPhysicalActivityController {

    @Autowired
    private PatientPhysicalActivityService physicalActivityService;

    @Operation(summary = "Obtener todas las actividades físicas", description = "Devuelve una lista de todas las actividades físicas registradas para un paciente.")
    @ApiResponse(responseCode = "200", description = "Lista de actividades físicas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientPhysicalActivityDto.class)))
    @GetMapping
    public List<PatientPhysicalActivityDto> getAllPhysicalActivities() {
        return physicalActivityService.findAll().stream()
                .map(PatientPhysicalActivityDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener una actividad física por ID", description = "Busca una actividad física específica usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Actividad física encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientPhysicalActivityDto.class)))
    @ApiResponse(responseCode = "404", description = "Actividad física no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<PatientPhysicalActivityDto> getPhysicalActivityById(@Parameter(description = "ID único de la actividad física") @PathVariable UUID id) {
        Optional<PatientPhysicalActivity> activityOptional = physicalActivityService.findById(id);
        return activityOptional.map(activity -> ResponseEntity.ok(new PatientPhysicalActivityDto(activity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva actividad física", description = "Crea un nuevo registro de actividad física para un paciente.")
    @ApiResponse(responseCode = "201", description = "Actividad física creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientPhysicalActivityDto.class)))
    @PostMapping
    public ResponseEntity<PatientPhysicalActivityDto> createPhysicalActivity(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO de la actividad física a crear") @RequestBody PatientPhysicalActivityDto activityDto) {
        PatientPhysicalActivity activity = new PatientPhysicalActivity();
        activity.setRoutineName(activityDto.getRoutineName());
        activity.setRoutineDescription(activityDto.getRoutineDescription());
        PatientPhysicalActivity savedActivity = physicalActivityService.save(activity);
        return new ResponseEntity<>(new PatientPhysicalActivityDto(savedActivity), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una actividad física existente", description = "Actualiza la información de una actividad física basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Actividad física actualizada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientPhysicalActivityDto.class)))
    @ApiResponse(responseCode = "404", description = "Actividad física no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<PatientPhysicalActivityDto> updatePhysicalActivity(@Parameter(description = "ID de la actividad física a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados de la actividad física") @RequestBody PatientPhysicalActivityDto activityDto) {
        Optional<PatientPhysicalActivity> activityOptional = physicalActivityService.findById(id);
        if (activityOptional.isPresent()) {
            PatientPhysicalActivity activity = activityOptional.get();
            activity.setRoutineName(activityDto.getRoutineName());
            activity.setRoutineDescription(activityDto.getRoutineDescription());
            PatientPhysicalActivity updatedActivity = physicalActivityService.save(activity);
            return ResponseEntity.ok(new PatientPhysicalActivityDto(updatedActivity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una actividad física por ID", description = "Elimina una actividad física de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Actividad física eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Actividad física no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhysicalActivity(@Parameter(description = "ID de la actividad física a eliminar") @PathVariable UUID id) {
        if (physicalActivityService.findById(id).isPresent()) {
            physicalActivityService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}