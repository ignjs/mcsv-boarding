package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.DailyRecordExerciseStatusDto;
import com.eleuthera.mcsv_boarding.models.DailyRecordExerciseStatus;
import com.eleuthera.mcsv_boarding.services.DailyRecordExerciseStatusService;
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
 * Controlador REST para la entidad DailyRecordExerciseStatus.
 */
@RestController
@RequestMapping("/api/exercise-statuses")
@Tag(name = "Daily Record Exercise Status", description = "Gestión del estado de los ejercicios en los registros diarios")
public class DailyRecordExerciseStatusController {

    @Autowired
    private DailyRecordExerciseStatusService exerciseStatusService;

    @Operation(summary = "Obtener todos los estados de ejercicio", description = "Devuelve una lista de todos los estados de ejercicio registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de estados de ejercicio", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordExerciseStatusDto.class)))
    @GetMapping
    public List<DailyRecordExerciseStatusDto> getAllExerciseStatuses() {
        return exerciseStatusService.findAll().stream()
                .map(DailyRecordExerciseStatusDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un estado de ejercicio por ID", description = "Busca un estado de ejercicio específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Estado de ejercicio encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordExerciseStatusDto.class)))
    @ApiResponse(responseCode = "404", description = "Estado de ejercicio no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<DailyRecordExerciseStatusDto> getExerciseStatusById(@Parameter(description = "ID único del estado del ejercicio") @PathVariable UUID id) {
        Optional<DailyRecordExerciseStatus> statusOptional = exerciseStatusService.findById(id);
        return statusOptional.map(status -> ResponseEntity.ok(new DailyRecordExerciseStatusDto(status)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo estado de ejercicio", description = "Crea un nuevo estado de ejercicio para un registro diario.")
    @ApiResponse(responseCode = "201", description = "Estado de ejercicio creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordExerciseStatusDto.class)))
    @PostMapping
    public ResponseEntity<DailyRecordExerciseStatusDto> createExerciseStatus(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO del estado del ejercicio a crear") @RequestBody DailyRecordExerciseStatusDto statusDto) {
        DailyRecordExerciseStatus status = new DailyRecordExerciseStatus();
        status.setExerciseType(statusDto.getExerciseType());
        status.setCompleted(statusDto.isCompleted());
        DailyRecordExerciseStatus savedStatus = exerciseStatusService.save(status);
        return new ResponseEntity<>(new DailyRecordExerciseStatusDto(savedStatus), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un estado de ejercicio existente", description = "Actualiza la información de un estado de ejercicio basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Estado de ejercicio actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordExerciseStatusDto.class)))
    @ApiResponse(responseCode = "404", description = "Estado de ejercicio no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<DailyRecordExerciseStatusDto> updateExerciseStatus(@Parameter(description = "ID del estado del ejercicio a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados del estado del ejercicio") @RequestBody DailyRecordExerciseStatusDto statusDto) {
        Optional<DailyRecordExerciseStatus> statusOptional = exerciseStatusService.findById(id);
        if (statusOptional.isPresent()) {
            DailyRecordExerciseStatus status = statusOptional.get();
            status.setExerciseType(statusDto.getExerciseType());
            status.setCompleted(statusDto.isCompleted());
            DailyRecordExerciseStatus updatedStatus = exerciseStatusService.save(status);
            return ResponseEntity.ok(new DailyRecordExerciseStatusDto(updatedStatus));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un estado de ejercicio por ID", description = "Elimina un estado de ejercicio de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Estado de ejercicio eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Estado de ejercicio no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExerciseStatus(@Parameter(description = "ID del estado del ejercicio a eliminar") @PathVariable UUID id) {
        if (exerciseStatusService.findById(id).isPresent()) {
            exerciseStatusService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}