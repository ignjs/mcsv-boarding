package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.PatientNutritionalRequirementsDTO;
import com.eleuthera.mcsv_boarding.models.PatientNutritionalRequirements;
import com.eleuthera.mcsv_boarding.services.PatientNutritionalRequirementsService;
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
 * Controlador REST para la entidad PatientNutritionalRequirements.
 */
@RestController
@RequestMapping("/api/nutritional-requirements")
@Tag(name = "Nutritional Requirements", description = "Gestión de requerimientos nutricionales del paciente")
public class PatientNutritionalRequirementsController {

    @Autowired
    private PatientNutritionalRequirementsService requirementsService;

    @Operation(summary = "Obtener todos los requerimientos nutricionales", description = "Devuelve una lista de todos los requerimientos nutricionales del paciente.")
    @ApiResponse(responseCode = "200", description = "Lista de requerimientos nutricionales", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientNutritionalRequirementsDTO.class)))
    @GetMapping
    public List<PatientNutritionalRequirementsDTO> getAllRequirements() {
        return requirementsService.findAll().stream()
                .map(PatientNutritionalRequirementsDTO::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un requerimiento nutricional por ID", description = "Busca un requerimiento nutricional específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Requerimiento nutricional encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientNutritionalRequirementsDTO.class)))
    @ApiResponse(responseCode = "404", description = "Requerimiento nutricional no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PatientNutritionalRequirementsDTO> getRequirementById(@Parameter(description = "ID único del requerimiento nutricional") @PathVariable UUID id) {
        Optional<PatientNutritionalRequirements> reqOptional = requirementsService.findById(id);
        return reqOptional.map(req -> ResponseEntity.ok(new PatientNutritionalRequirementsDTO(req)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo requerimiento nutricional", description = "Crea un nuevo requerimiento nutricional para un paciente.")
    @ApiResponse(responseCode = "201", description = "Requerimiento nutricional creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientNutritionalRequirementsDTO.class)))
    @PostMapping
    public ResponseEntity<PatientNutritionalRequirementsDTO> createRequirement(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO del requerimiento nutricional a crear") @RequestBody PatientNutritionalRequirementsDTO reqDto) {
        PatientNutritionalRequirements req = new PatientNutritionalRequirements();
        req.setEnergyKcal(reqDto.getEnergyKcal());
        req.setProteinsGrams(reqDto.getProteinsGrams());
        req.setLipidsGrams(reqDto.getLipidsGrams());
        req.setCarbohydratesGrams(reqDto.getCarbohydratesGrams());
        req.setWaterVolume(reqDto.getWaterVolume());
        PatientNutritionalRequirements savedReq = requirementsService.save(req);
        return new ResponseEntity<>(new PatientNutritionalRequirementsDTO(savedReq), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un requerimiento nutricional existente", description = "Actualiza la información de un requerimiento nutricional basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Requerimiento nutricional actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientNutritionalRequirementsDTO.class)))
    @ApiResponse(responseCode = "404", description = "Requerimiento nutricional no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<PatientNutritionalRequirementsDTO> updateRequirement(@Parameter(description = "ID del requerimiento nutricional a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados del requerimiento nutricional") @RequestBody PatientNutritionalRequirementsDTO reqDto) {
        Optional<PatientNutritionalRequirements> reqOptional = requirementsService.findById(id);
        if (reqOptional.isPresent()) {
            PatientNutritionalRequirements req = reqOptional.get();
            req.setEnergyKcal(reqDto.getEnergyKcal());
            req.setProteinsGrams(reqDto.getProteinsGrams());
            req.setLipidsGrams(reqDto.getLipidsGrams());
            req.setCarbohydratesGrams(reqDto.getCarbohydratesGrams());
            req.setWaterVolume(reqDto.getWaterVolume());
            PatientNutritionalRequirements updatedReq = requirementsService.save(req);
            return ResponseEntity.ok(new PatientNutritionalRequirementsDTO(updatedReq));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un requerimiento nutricional por ID", description = "Elimina un requerimiento nutricional de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Requerimiento nutricional eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Requerimiento nutricional no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequirement(@Parameter(description = "ID del requerimiento nutricional a eliminar") @PathVariable UUID id) {
        if (requirementsService.findById(id).isPresent()) {
            requirementsService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}