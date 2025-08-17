package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.PatientExampleMealPlanDto;
import com.eleuthera.mcsv_boarding.models.PatientExampleMealPlan;
import com.eleuthera.mcsv_boarding.services.PatientExampleMealPlanService;
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
 * Controlador REST para la entidad PatientExampleMealPlan.
 */
@RestController
@RequestMapping("/api/example-meal-plans")
@Tag(name = "Example Meal Plans", description = "Gestión de los planes de comidas de ejemplo para pacientes")
public class PatientExampleMealPlanController {

    @Autowired
    private PatientExampleMealPlanService mealPlanService;

    @Operation(summary = "Obtener todos los planes de comidas de ejemplo", description = "Devuelve una lista de todos los planes de comidas de ejemplo registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de planes de comidas de ejemplo", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientExampleMealPlanDto.class)))
    @GetMapping
    public List<PatientExampleMealPlanDto> getAllMealPlans() {
        return mealPlanService.findAll().stream()
                .map(PatientExampleMealPlanDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un plan de comidas de ejemplo por ID", description = "Busca un plan de comidas de ejemplo específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Plan de comidas de ejemplo encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientExampleMealPlanDto.class)))
    @ApiResponse(responseCode = "404", description = "Plan de comidas de ejemplo no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PatientExampleMealPlanDto> getMealPlanById(@Parameter(description = "ID único del plan de comidas de ejemplo") @PathVariable UUID id) {
        Optional<PatientExampleMealPlan> planOptional = mealPlanService.findById(id);
        return planOptional.map(plan -> ResponseEntity.ok(new PatientExampleMealPlanDto(plan)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo plan de comidas de ejemplo", description = "Crea un nuevo plan de comidas de ejemplo.")
    @ApiResponse(responseCode = "201", description = "Plan de comidas de ejemplo creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientExampleMealPlanDto.class)))
    @PostMapping
    public ResponseEntity<PatientExampleMealPlanDto> createMealPlan(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO del plan de comidas de ejemplo a crear") @RequestBody PatientExampleMealPlanDto planDto) {
        PatientExampleMealPlan plan = new PatientExampleMealPlan();
        plan.setMealType(planDto.getMealType());
        plan.setMealDescription(planDto.getMealDescription());
        PatientExampleMealPlan savedPlan = mealPlanService.save(plan);
        return new ResponseEntity<>(new PatientExampleMealPlanDto(savedPlan), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un plan de comidas de ejemplo existente", description = "Actualiza la información de un plan de comidas de ejemplo basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Plan de comidas de ejemplo actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientExampleMealPlanDto.class)))
    @ApiResponse(responseCode = "404", description = "Plan de comidas de ejemplo no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<PatientExampleMealPlanDto> updateMealPlan(@Parameter(description = "ID del plan de comidas de ejemplo a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados del plan de comidas de ejemplo") @RequestBody PatientExampleMealPlanDto planDto) {
        Optional<PatientExampleMealPlan> planOptional = mealPlanService.findById(id);
        if (planOptional.isPresent()) {
            PatientExampleMealPlan plan = planOptional.get();
            plan.setMealType(planDto.getMealType());
            plan.setMealDescription(planDto.getMealDescription());
            PatientExampleMealPlan updatedPlan = mealPlanService.save(plan);
            return ResponseEntity.ok(new PatientExampleMealPlanDto(updatedPlan));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealPlan(@PathVariable UUID id) {
        if (mealPlanService.findById(id).isPresent()) {
            mealPlanService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}