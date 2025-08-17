package com.eleuthera.mcsv_boarding.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "patient_daily_portions")
public class PatientDailyPortions {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_profile_id", referencedColumnName = "id")
    private PatientProfile patientProfile;

    @Column(name = "cereales")
    private Integer cereales;
    
    @Column(name = "frutas")
    private Integer frutas;
    
    @Column(name = "verduras_consumo_general")
    private Integer verdurasConsumoGeneral;
    
    @Column(name = "verduras_libre_consumo")
    private Integer verdurasLibreConsumo;
    
    @Column(name = "proteinas")
    private Integer proteinas;
    
    @Column(name = "lacteos_medios_en_grasa")
    private Integer lacteosMediosEnGrasa;
    
    @Column(name = "grasas")
    private Integer grasas;
    
    @Column(name = "alimentos_ricos_en_grasa")
    private Integer alimentosRicosEnGrasa;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    public Integer getCereales() {
        return cereales;
    }

    public void setCereales(Integer cereales) {
        this.cereales = cereales;
    }

    public Integer getFrutas() {
        return frutas;
    }

    public void setFrutas(Integer frutas) {
        this.frutas = frutas;
    }

    public Integer getVerdurasConsumoGeneral() {
        return verdurasConsumoGeneral;
    }

    public void setVerdurasConsumoGeneral(Integer verdurasConsumoGeneral) {
        this.verdurasConsumoGeneral = verdurasConsumoGeneral;
    }

    public Integer getVerdurasLibreConsumo() {
        return verdurasLibreConsumo;
    }

    public void setVerdurasLibreConsumo(Integer verdurasLibreConsumo) {
        this.verdurasLibreConsumo = verdurasLibreConsumo;
    }

    public Integer getProteinas() {
        return proteinas;
    }

    public void setProteinas(Integer proteinas) {
        this.proteinas = proteinas;
    }

    public Integer getLacteosMediosEnGrasa() {
        return lacteosMediosEnGrasa;
    }

    public void setLacteosMediosEnGrasa(Integer lacteosMediosEnGrasa) {
        this.lacteosMediosEnGrasa = lacteosMediosEnGrasa;
    }

    public Integer getGrasas() {
        return grasas;
    }

    public void setGrasas(Integer grasas) {
        this.grasas = grasas;
    }

    public Integer getAlimentosRicosEnGrasa() {
        return alimentosRicosEnGrasa;
    }

    public void setAlimentosRicosEnGrasa(Integer alimentosRicosEnGrasa) {
        this.alimentosRicosEnGrasa = alimentosRicosEnGrasa;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}
