package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.PlanDto;
import com.eleuthera.mcsv_boarding.models.Plan;
import com.eleuthera.mcsv_boarding.services.PlanService;
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
 * Controlador REST para la entidad Plan.
 */
@RestController
@RequestMapping("/api/plans")
@Tag(name = "Plans", description = "Gestión de los planes de salud y bienestar")
public class PlanController {

    @Autowired
    private PlanService planService;

    @Operation(summary = "Obtener todos los planes", description = "Devuelve una lista de todos los planes de salud y bienestar.")
    @ApiResponse(responseCode = "200", description = "Lista de planes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanDto.class)))
    @GetMapping
    public List<PlanDto> getAllPlans() {
        return planService.findAll().stream()
                .map(PlanDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un plan por ID", description = "Busca un plan específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Plan encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanDto.class)))
    @ApiResponse(responseCode = "404", description = "Plan no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PlanDto> getPlanById(@Parameter(description = "ID único del plan") @PathVariable UUID id) {
        Optional<Plan> planOptional = planService.findById(id);
        return planOptional.map(plan -> ResponseEntity.ok(new PlanDto(plan)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo plan", description = "Crea un nuevo plan de salud y bienestar.")
    @ApiResponse(responseCode = "201", description = "Plan creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanDto.class)))
    @PostMapping
    public ResponseEntity<PlanDto> createPlan(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO del plan a crear") @RequestBody PlanDto planDto) {
        Plan plan = new Plan();
        plan.setName(planDto.getName());
        plan.setDescription(planDto.getDescription());
        plan.setDurationInDays(planDto.getDurationInDays());
        Plan savedPlan = planService.save(plan);
        return new ResponseEntity<>(new PlanDto(savedPlan), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un plan existente", description = "Actualiza la información de un plan basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Plan actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanDto.class)))
    @ApiResponse(responseCode = "404", description = "Plan no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<PlanDto> updatePlan(@Parameter(description = "ID del plan a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados del plan") @RequestBody PlanDto planDto) {
        Optional<Plan> planOptional = planService.findById(id);
        if (planOptional.isPresent()) {
            Plan plan = planOptional.get();
            plan.setName(planDto.getName());
            plan.setDescription(planDto.getDescription());
            plan.setDurationInDays(planDto.getDurationInDays());
            Plan updatedPlan = planService.save(plan);
            return ResponseEntity.ok(new PlanDto(updatedPlan));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un plan por ID", description = "Elimina un plan de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Plan eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Plan no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@Parameter(description = "ID del plan a eliminar") @PathVariable UUID id) {
        if (planService.findById(id).isPresent()) {
            planService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}