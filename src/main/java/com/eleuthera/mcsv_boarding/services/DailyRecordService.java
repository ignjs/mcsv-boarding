package com.eleuthera.mcsv_boarding.services;

import com.eleuthera.mcsv_boarding.models.DailyRecord;
import com.eleuthera.mcsv_boarding.repositories.DailyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Servicio para la entidad DailyRecord.
 * Contiene la lógica de negocio para la gestión de registros diarios.
 */
@Service
public class DailyRecordService {

    @Autowired
    private DailyRecordRepository dailyRecordRepository;

    /**
     * Guarda un nuevo registro diario o actualiza uno existente.
     * @param dailyRecord El objeto DailyRecord a guardar.
     * @return El registro diario guardado.
     */
    public DailyRecord save(DailyRecord dailyRecord) {
        return dailyRecordRepository.save(dailyRecord);
    }

    /**
     * Busca un registro diario por su ID.
     * @param id El UUID del registro diario.
     * @return Un Optional que contiene el registro si se encuentra.
     */
    public Optional<DailyRecord> findById(UUID id) {
        return dailyRecordRepository.findById(id);
    }

    /**
     * Obtiene una lista de todos los registros diarios.
     * @return Una lista de objetos DailyRecord.
     */
    public List<DailyRecord> findAll() {
        return dailyRecordRepository.findAll();
    }

    /**
     * Elimina un registro diario por su ID.
     * @param id El UUID del registro diario a eliminar.
     */
    public void deleteById(UUID id) {
        dailyRecordRepository.deleteById(id);
    }
}
