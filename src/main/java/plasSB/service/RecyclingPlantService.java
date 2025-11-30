package plasSB.service;

import org.springframework.stereotype.Service;


import plasSB.entity.RecyclingPlant;
import plasSB.dao.RecyclingPlantRepository;
import java.time.LocalDate;

import java.util.Optional;


@Service
public class RecyclingPlantService {
    
    private final RecyclingPlantRepository plantRepository;
    
    public RecyclingPlantService(RecyclingPlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }
    
    public RecyclingPlant getCapacityForDate(LocalDate date) {
        Optional<RecyclingPlant> record = plantRepository.findByDate(date);
        
        if (record.isPresent()) {
            return record.get();
        } else {
            // Crear registro por defecto si no existe
            return createDefaultCapacity(date);
        }
    }
    
    public RecyclingPlant getCurrentCapacity() {
        return plantRepository.findMostRecent()
                .orElseGet(() -> createDefaultCapacity(LocalDate.now()));
    }
    
    public RecyclingPlant updateCapacityForDate(LocalDate date, float currentCapacity) {
        Optional<RecyclingPlant> existingRecord = plantRepository.findByDate(date);
        
        if (existingRecord.isPresent()) {
            RecyclingPlant record = existingRecord.get();
            record.setCurrent_capacity(currentCapacity);
            return plantRepository.save(record);
        } else {
            RecyclingPlant newRecord = new RecyclingPlant(date, currentCapacity);
            return plantRepository.save(newRecord);
        }
    }
    
    public RecyclingPlant reduceCapacity(float amount) {
        RecyclingPlant current = getCurrentCapacity();
        float newCapacity = current.getCurrent_capacity() - amount;
        
        if (newCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        
        current.setCurrent_capacity(newCapacity);
        return plantRepository.save(current);
    }
    
  
    
    private RecyclingPlant createDefaultCapacity(LocalDate date) {
        float defaultCapacity = 1200.0f; // 80% de 1500
        RecyclingPlant newRecord = new RecyclingPlant(date, defaultCapacity);
        return plantRepository.save(newRecord);
    }
}
