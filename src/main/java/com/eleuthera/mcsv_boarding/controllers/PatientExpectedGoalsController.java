package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.PatientExpectedGoalsDto;
import com.eleuthera.mcsv_boarding.models.PatientExpectedGoals;
import com.eleuthera.mcsv_boarding.services.PatientExpectedGoalsService;
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
 * Controlador REST para la entidad PatientExpectedGoals.
 */
@RestController
@RequestMapping("/api/patient-goals")
@Tag(name = "Patient Expected Goals", description = "Gestión de los objetivos esperados del paciente")
public class PatientExpectedGoalsController {

    @Autowired
    private PatientExpectedGoalsService expectedGoalsService;

    @Operation(summary = "Obtener todos los objetivos esperados", description = "Devuelve una lista de todos los objetivos esperados de los pacientes.")
    @ApiResponse(responseCode = "200", description = "Lista de objetivos esperados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientExpectedGoalsDto.class)))
    @GetMapping
    public List<PatientExpectedGoalsDto> getAllGoals() {
        return expectedGoalsService.findAll().stream()
                .map(PatientExpectedGoalsDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un objetivo esperado por ID", description = "Busca un objetivo esperado específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Objetivo esperado encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientExpectedGoalsDto.class)))
    @ApiResponse(responseCode = "404", description = "Objetivo esperado no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PatientExpectedGoalsDto> getGoalById(@Parameter(description = "ID único del objetivo esperado") @PathVariable UUID id) {
        Optional<PatientExpectedGoals> goalsOptional = expectedGoalsService.findById(id);
        return goalsOptional.map(goals -> ResponseEntity.ok(new PatientExpectedGoalsDto(goals)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo objetivo esperado", description = "Crea un nuevo objetivo esperado para un paciente.")
    @ApiResponse(responseCode = "201", description = "Objetivo esperado creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientExpectedGoalsDto.class)))
    @PostMapping
    public ResponseEntity<PatientExpectedGoalsDto> createGoal(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO del objetivo esperado a crear") @RequestBody PatientExpectedGoalsDto goalsDto) {
        PatientExpectedGoals goals = new PatientExpectedGoals();
        goals.setGoalDescription(goalsDto.getGoalDescription());
        PatientExpectedGoals savedGoals = expectedGoalsService.save(goals);
        return new ResponseEntity<>(new PatientExpectedGoalsDto(savedGoals), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un objetivo esperado existente", description = "Actualiza la información de un objetivo esperado basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Objetivo esperado actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientExpectedGoalsDto.class)))
    @ApiResponse(responseCode = "404", description = "Objetivo esperado no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<PatientExpectedGoalsDto> updateGoal(@Parameter(description = "ID del objetivo esperado a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados del objetivo esperado") @RequestBody PatientExpectedGoalsDto goalsDto) {
        Optional<PatientExpectedGoals> goalsOptional = expectedGoalsService.findById(id);
        if (goalsOptional.isPresent()) {
            PatientExpectedGoals goals = goalsOptional.get();
            goals.setGoalDescription(goalsDto.getGoalDescription());
            PatientExpectedGoals updatedGoals = expectedGoalsService.save(goals);
            return ResponseEntity.ok(new PatientExpectedGoalsDto(updatedGoals));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un objetivo esperado por ID", description = "Elimina un objetivo esperado de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Objetivo esperado eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Objetivo esperado no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@Parameter(description = "ID del objetivo esperado a eliminar") @PathVariable UUID id) {
        if (expectedGoalsService.findById(id).isPresent()) {
            expectedGoalsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}