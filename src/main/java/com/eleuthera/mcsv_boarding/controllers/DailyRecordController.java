package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.DailyRecordDTO;
import com.eleuthera.mcsv_boarding.models.DailyRecord;
import com.eleuthera.mcsv_boarding.services.DailyRecordService;
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
 * Controlador REST para la entidad DailyRecord.
 */
@RestController
@RequestMapping("/api/daily-records")
@Tag(name = "Daily Records", description = "Gestión de registros diarios del paciente")
public class DailyRecordController {

    @Autowired
    private DailyRecordService dailyRecordService;

    @Operation(summary = "Obtener todos los registros diarios", description = "Devuelve una lista de todos los registros diarios del paciente.")
    @ApiResponse(responseCode = "200", description = "Lista de registros diarios", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordDTO.class)))
    @GetMapping
    public List<DailyRecordDTO> getAllDailyRecords() {
        return dailyRecordService.findAll().stream()
                .map(DailyRecordDTO::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un registro diario por ID", description = "Busca un registro diario específico usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Registro diario encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordDTO.class)))
    @ApiResponse(responseCode = "404", description = "Registro diario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<DailyRecordDTO> getDailyRecordById(@Parameter(description = "ID único del registro diario") @PathVariable UUID id) {
        Optional<DailyRecord> recordOptional = dailyRecordService.findById(id);
        return recordOptional.map(record -> ResponseEntity.ok(new DailyRecordDTO(record)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo registro diario", description = "Crea un nuevo registro diario para un paciente.")
    @ApiResponse(responseCode = "201", description = "Registro diario creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordDTO.class)))
    @PostMapping
    public ResponseEntity<DailyRecordDTO> createDailyRecord(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO del registro diario a crear") @RequestBody DailyRecordDTO recordDto) {
        DailyRecord record = new DailyRecord();
        record.setDate(recordDto.getDate());
        record.setCalories(recordDto.getCalories());
        DailyRecord savedRecord = dailyRecordService.save(record);
        return new ResponseEntity<>(new DailyRecordDTO(savedRecord), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un registro diario existente", description = "Actualiza la información de un registro diario basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Registro diario actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyRecordDTO.class)))
    @ApiResponse(responseCode = "404", description = "Registro diario no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<DailyRecordDTO> updateDailyRecord(@Parameter(description = "ID del registro diario a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados del registro diario") @RequestBody DailyRecordDTO recordDto) {
        Optional<DailyRecord> recordOptional = dailyRecordService.findById(id);
        if (recordOptional.isPresent()) {
            DailyRecord record = recordOptional.get();
            record.setDate(recordDto.getDate());
            record.setCalories(recordDto.getCalories());
            DailyRecord updatedRecord = dailyRecordService.save(record);
            return ResponseEntity.ok(new DailyRecordDTO(updatedRecord));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar un registro diario por ID", description = "Elimina un registro diario de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Registro diario eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Registro diario no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailyRecord(@Parameter(description = "ID del registro diario a eliminar") @PathVariable UUID id) {
        if (dailyRecordService.findById(id).isPresent()) {
            dailyRecordService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
