package com.eleuthera.mcsv_boarding.controllers;

import com.eleuthera.mcsv_boarding.dtos.MealPlanOptionDto;
import com.eleuthera.mcsv_boarding.models.MealPlanOption;
import com.eleuthera.mcsv_boarding.services.MealPlanOptionService;
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
 * Controlador REST para la entidad MealPlanOption.
 */
@RestController
@RequestMapping("/api/meal-plan-options")
@Tag(name = "Meal Plan Options", description = "Gestión de las opciones de planes de comidas")
public class MealPlanOptionController {

    @Autowired
    private MealPlanOptionService mealPlanOptionService;

    @Operation(summary = "Obtener todas las opciones de planes de comidas", description = "Devuelve una lista de todas las opciones de planes de comidas registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de opciones de planes de comidas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealPlanOptionDto.class)))
    @GetMapping
    public List<MealPlanOptionDto> getAllMealPlanOptions() {
        return mealPlanOptionService.findAll().stream()
                .map(MealPlanOptionDto::new)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener una opción de plan de comidas por ID", description = "Busca una opción de plan de comidas específica usando su ID único.")
    @ApiResponse(responseCode = "200", description = "Opción de plan de comidas encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealPlanOptionDto.class)))
    @ApiResponse(responseCode = "404", description = "Opción de plan de comidas no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<MealPlanOptionDto> getMealPlanOptionById(@Parameter(description = "ID único de la opción de plan de comidas") @PathVariable UUID id) {
        Optional<MealPlanOption> optionOptional = mealPlanOptionService.findById(id);
        return optionOptional.map(option -> ResponseEntity.ok(new MealPlanOptionDto(option)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva opción de plan de comidas", description = "Crea una nueva opción para un plan de comidas.")
    @ApiResponse(responseCode = "201", description = "Opción de plan de comidas creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealPlanOptionDto.class)))
    @PostMapping
    public ResponseEntity<MealPlanOptionDto> createMealPlanOption(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO de la opción de plan de comidas a crear") @RequestBody MealPlanOptionDto optionDto) {
        MealPlanOption option = new MealPlanOption();
        option.setOptionDescription(optionDto.getOptionDescription());
        MealPlanOption savedOption = mealPlanOptionService.save(option);
        return new ResponseEntity<>(new MealPlanOptionDto(savedOption), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una opción de plan de comidas existente", description = "Actualiza la información de una opción de plan de comidas basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Opción de plan de comidas actualizada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealPlanOptionDto.class)))
    @ApiResponse(responseCode = "404", description = "Opción de plan de comidas no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<MealPlanOptionDto> updateMealPlanOption(@Parameter(description = "ID de la opción de plan de comidas a actualizar") @PathVariable UUID id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "DTO con los datos actualizados de la opción de plan de comidas") @RequestBody MealPlanOptionDto optionDto) {
        Optional<MealPlanOption> optionOptional = mealPlanOptionService.findById(id);
        if (optionOptional.isPresent()) {
            MealPlanOption option = optionOptional.get();
            option.setOptionDescription(optionDto.getOptionDescription());
            MealPlanOption updatedOption = mealPlanOptionService.save(option);
            return ResponseEntity.ok(new MealPlanOptionDto(updatedOption));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealPlanOption(@PathVariable UUID id) {
        if (mealPlanOptionService.findById(id).isPresent()) {
            mealPlanOptionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}