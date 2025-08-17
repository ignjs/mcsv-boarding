package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.PlanDetail;
import com.eleuthera.mcsv_boarding.repositories.PlanDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad PlanDetail.
 * Contiene la lógica de negocio para la gestión de detalles de planes.
 */
@Service
public class PlanDetailService {

    @Autowired
    private PlanDetailRepository planDetailRepository;

    public PlanDetail save(PlanDetail planDetail) {
        return planDetailRepository.save(planDetail);
    }

    public Optional<PlanDetail> findById(UUID id) {
        return planDetailRepository.findById(id);
    }

    public List<PlanDetail> findAll() {
        return planDetailRepository.findAll();
    }

    public void deleteById(UUID id) {
        planDetailRepository.deleteById(id);
    }
}