package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.PlanDetailDto;
import com.eleuthera.mcsv_boarding.models.PlanDetail;
import com.eleuthera.mcsv_boarding.services.PlanDetailService;
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
 * Controlador REST para la entidad PlanDetail.
 */
@RestController
@RequestMapping("/api/plan-details")
@Tag(name = "Plan Details", description = "Gestión de los detalles de los planes")
public class PlanDetailController {

    @Autowired
    private PlanDetailService planDetailService;

    @Operation(summary = "Obtener todos los detalles de los planes", description = "Devuelve una lista de todos los detalles de los planes registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de detalles de los planes", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanDetailDto.class)))
    @GetMapping
    public List<PlanDetailDto> getAllPlanDetails() {
        return planDetailService.findAll().stream()
                .map(PlanDetailDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un detalle de plan por ID", description = "Busca un detalle de plan específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Detalle de plan encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanDetailDto.class)))
    @ApiResponse(responseCode = "404", description = "Detalle de plan no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PlanDetailDto> getPlanDetailById(@Parameter(description = "ID único del detalle del plan") @PathVariable UUID id) {
        Optional<PlanDetail> detailOptional = planDetailService.findById(id);
        return detailOptional.map(detail -> ResponseEntity.ok(new PlanDetailDto(detail)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo detalle de plan", description = "Crea un nuevo detalle para un plan.")
    @ApiResponse(responseCode = "201", description = "Detalle de plan creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanDetailDto.class)))
    @PostMapping
    public ResponseEntity<PlanDetailDto> createPlanDetail(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO del detalle del plan a crear") @RequestBody PlanDetailDto detailDto) {
        PlanDetail detail = new PlanDetail();
        detail.setDayName(detailDto.getDayName());
        detail.setPlanDate(detailDto.getPlanDate());
        PlanDetail savedDetail = planDetailService.save(detail);
        return new ResponseEntity<>(new PlanDetailDto(savedDetail), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un detalle de plan existente", description = "Actualiza la información de un detalle de plan basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Detalle de plan actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PlanDetailDto.class)))
    @ApiResponse(responseCode = "404", description = "Detalle de plan no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<PlanDetailDto> updatePlanDetail(@Parameter(description = "ID del detalle del plan a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados del detalle del plan") @RequestBody PlanDetailDto detailDto) {
        Optional<PlanDetail> detailOptional = planDetailService.findById(id);
        if (detailOptional.isPresent()) {
            PlanDetail detail = detailOptional.get();
            detail.setDayName(detailDto.getDayName());
            detail.setPlanDate(detailDto.getPlanDate());
            PlanDetail updatedDetail = planDetailService.save(detail);
            return ResponseEntity.ok(new PlanDetailDto(updatedDetail));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un detalle de plan por ID", description = "Elimina un detalle de plan de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Detalle de plan eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Detalle de plan no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanDetail(@Parameter(description = "ID del detalle del plan a eliminar") @PathVariable UUID id) {
        if (planDetailService.findById(id).isPresent()) {
            planDetailService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}