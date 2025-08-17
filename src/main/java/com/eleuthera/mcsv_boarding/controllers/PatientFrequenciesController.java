package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.PatientFrequenciesDto;
import com.eleuthera.mcsv_boarding.models.PatientFrequencies;
import com.eleuthera.mcsv_boarding.services.PatientFrequenciesService;
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
 * Controlador REST para la entidad PatientFrequencies.
 */
@RestController
@RequestMapping("/api/patient-frequencies")
@Tag(name = "Patient Frequencies", description = "Gestión de frecuencias (defecación, micción) del paciente")
public class PatientFrequenciesController {

    @Autowired
    private PatientFrequenciesService frequenciesService;

    @Operation(summary = "Obtener todas las frecuencias del paciente", description = "Devuelve una lista de todas las frecuencias de defecación y micción registradas para un paciente.")
    @ApiResponse(responseCode = "200", description = "Lista de frecuencias del paciente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientFrequenciesDto.class)))
    @GetMapping
    public List<PatientFrequenciesDto> getAllFrequencies() {
        return frequenciesService.findAll().stream()
                .map(PatientFrequenciesDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener una frecuencia del paciente por ID", description = "Busca una frecuencia específica del paciente usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Frecuencia del paciente encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientFrequenciesDto.class)))
    @ApiResponse(responseCode = "404", description = "Frecuencia del paciente no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<PatientFrequenciesDto> getFrequencyById(@Parameter(description = "ID único de la frecuencia del paciente") @PathVariable UUID id) {
        Optional<PatientFrequencies> freqOptional = frequenciesService.findById(id);
        return freqOptional.map(freq -> ResponseEntity.ok(new PatientFrequenciesDto(freq)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva frecuencia del paciente", description = "Crea un nuevo registro de frecuencias para un paciente.")
    @ApiResponse(responseCode = "201", description = "Frecuencia del paciente creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientFrequenciesDto.class)))
    @PostMapping
    public ResponseEntity<PatientFrequenciesDto> createFrequency(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO de la frecuencia del paciente a crear") @RequestBody PatientFrequenciesDto freqDto) {
        PatientFrequencies freq = new PatientFrequencies();
        freq.setDefecationFrequency(freqDto.getDefecationFrequency());
        freq.setUrinationFrequency(freqDto.getUrinationFrequency());
        PatientFrequencies savedFreq = frequenciesService.save(freq);
        return new ResponseEntity<>(new PatientFrequenciesDto(savedFreq), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una frecuencia del paciente existente", description = "Actualiza la información de una frecuencia del paciente basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Frecuencia del paciente actualizada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientFrequenciesDto.class)))
    @ApiResponse(responseCode = "404", description = "Frecuencia del paciente no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<PatientFrequenciesDto> updateFrequency(@Parameter(description = "ID de la frecuencia del paciente a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados de la frecuencia del paciente") @RequestBody PatientFrequenciesDto freqDto) {
        Optional<PatientFrequencies> freqOptional = frequenciesService.findById(id);
        if (freqOptional.isPresent()) {
            PatientFrequencies freq = freqOptional.get();
            freq.setDefecationFrequency(freqDto.getDefecationFrequency());
            freq.setUrinationFrequency(freqDto.getUrinationFrequency());
            PatientFrequencies updatedFreq = frequenciesService.save(freq);
            return ResponseEntity.ok(new PatientFrequenciesDto(updatedFreq));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar una frecuencia del paciente por ID", description = "Elimina una frecuencia del paciente de la base de datos de forma permanente.")
    @ApiResponse(responseCode = "204", description = "Frecuencia del paciente eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Frecuencia del paciente no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFrequency(@Parameter(description = "ID de la frecuencia del paciente a eliminar") @PathVariable UUID id) {
        if (frequenciesService.findById(id).isPresent()) {
            frequenciesService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}