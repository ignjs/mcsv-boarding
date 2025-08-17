package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.DailyRecordMealStatusDto;
import com.eleuthera.mcsv_boarding.models.DailyRecordMealStatus;
import com.eleuthera.mcsv_boarding.services.DailyRecordMealStatusService;
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
 * Controlador REST para la entidad DailyRecordMealStatus.
 */
@RestController
@RequestMapping("/api/meal-statuses")
@Tag(name = "Daily Record Meal Status", description = "Gestión del estado de las comidas en los registros diarios")
public class DailyRecordMealStatusController {

    @Autowired
    private DailyRecordMealStatusService mealStatusService;

    @Operation(summary = "Obtener todos los estados de comida", description = "Devuelve una lista de todos los estados de comida registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de estados de comida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordMealStatusDto.class)))
    @GetMapping
    public List<DailyRecordMealStatusDto> getAllMealStatuses() {
        return mealStatusService.findAll().stream()
                .map(DailyRecordMealStatusDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un estado de comida por ID", description = "Busca un estado de comida específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Estado de comida encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordMealStatusDto.class)))
    @ApiResponse(responseCode = "404", description = "Estado de comida no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<DailyRecordMealStatusDto> getMealStatusById(@Parameter(description = "ID único del estado de la comida") @PathVariable UUID id) {
        Optional<DailyRecordMealStatus> statusOptional = mealStatusService.findById(id);
        return statusOptional.map(status -> ResponseEntity.ok(new DailyRecordMealStatusDto(status)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo estado de comida", description = "Crea un nuevo estado de comida para un registro diario.")
    @ApiResponse(responseCode = "201", description = "Estado de comida creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordMealStatusDto.class)))
    @PostMapping
    public ResponseEntity<DailyRecordMealStatusDto> createMealStatus(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO del estado de comida a crear") @RequestBody DailyRecordMealStatusDto statusDto) {
        DailyRecordMealStatus status = new DailyRecordMealStatus();
        status.setMealType(statusDto.getMealType());
        status.setConsumed(statusDto.isConsumed());
        DailyRecordMealStatus savedStatus = mealStatusService.save(status);
        return new ResponseEntity<>(new DailyRecordMealStatusDto(savedStatus), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un estado de comida existente", description = "Actualiza la información de un estado de comida basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Estado de comida actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordMealStatusDto.class)))
    @ApiResponse(responseCode = "404", description = "Estado de comida no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<DailyRecordMealStatusDto> updateMealStatus(@Parameter(description = "ID del estado de la comida a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados del estado de la comida") @RequestBody DailyRecordMealStatusDto statusDto) {
        Optional<DailyRecordMealStatus> statusOptional = mealStatusService.findById(id);
        if (statusOptional.isPresent()) {
            DailyRecordMealStatus status = statusOptional.get();
            status.setMealType(statusDto.getMealType());
            status.setConsumed(statusDto.isConsumed());
            DailyRecordMealStatus updatedStatus = mealStatusService.save(status);
            return ResponseEntity.ok(new DailyRecordMealStatusDto(updatedStatus));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un estado de comida por ID", description = "Elimina un estado de comida de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Estado de comida eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Estado de comida no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealStatus(@Parameter(description = "ID del estado de la comida a eliminar") @PathVariable UUID id) {
        if (mealStatusService.findById(id).isPresent()) {
            mealStatusService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
