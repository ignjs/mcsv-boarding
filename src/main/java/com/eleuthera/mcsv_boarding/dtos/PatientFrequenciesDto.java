package com.eleuthera.mcsv_boarding.dtos;

import com.eleuthera.mcsv_boarding.models.PatientFrequencies;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) para la entidad PatientFrequencies.
 */
public class PatientFrequenciesDto {

    private UUID id;
    private String defecationFrequency;
    private String urinationFrequency;

    /**
     * Constructor vacío.
     */
    public PatientFrequenciesDto() {
    }

    /**
     * Constructor que mapea una entidad PatientFrequencies a un DTO.
     * @param frequencies La entidad PatientFrequencies de la que se obtendrán los datos.
     */
    public PatientFrequenciesDto(PatientFrequencies frequencies) {
        this.id = frequencies.getId();
        this.defecationFrequency = frequencies.getDefecationFrequency();
        this.urinationFrequency = frequencies.getUrinationFrequency();
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDefecationFrequency() {
        return defecationFrequency;
    }

    public void setDefecationFrequency(String defecationFrequency) {
        this.defecationFrequency = defecationFrequency;
    }

    public String getUrinationFrequency() {
        return urinationFrequency;
    }

    public void setUrinationFrequency(String urinationFrequency) {
        this.urinationFrequency = urinationFrequency;
    }
}