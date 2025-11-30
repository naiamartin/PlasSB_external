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

	// 2. Método para SUMAR capacidad (cuando llegan asignaciones)
	public RecyclingPlant addCapacity(float amount) {
	    RecyclingPlant current = getCapacityForDate(LocalDate.now());
	    float newCapacity = current.getCurrent_capacity() + amount;
	    
	    // Verificar que no exceda la capacidad total
	    if (newCapacity > current.getTotal_capacity()) {
	        throw new IllegalArgumentException(
	            String.format("Capacity exceeded: %.2f + %.2f = %.2f > %.2f", 
	                current.getCurrent_capacity(), amount, newCapacity, current.getTotal_capacity())
	        );
	    }
	    
	    current.setCurrent_capacity(newCapacity);
	    return plantRepository.save(current);
	}

	// 3. Método para RESTAR capacidad (cuando se procesa material)
	public RecyclingPlant subtractCapacity(float amount) {
	    RecyclingPlant current = getCapacityForDate(LocalDate.now());
	    float newCapacity = current.getCurrent_capacity() - amount;
	    
	    if (newCapacity < 0) {
	        throw new IllegalArgumentException("Capacity cannot be negative");
	    }
	    
	    current.setCurrent_capacity(newCapacity);
	    return plantRepository.save(current);
	}

	//Método para capacidad de fecha específica
	public RecyclingPlant getCapacityForDate(LocalDate date) {
	    Optional<RecyclingPlant> record = plantRepository.findByDate(date);
	    
	    if (record.isPresent()) {
	        return record.get();
	    } else {
	        // Crear registro por defecto si no existe
	        RecyclingPlant newRecord = new RecyclingPlant(date, 800.0f);
	        return plantRepository.save(newRecord);
	    }
	}
	public RecyclingPlant updateCapacity(RecyclingPlant plant) {
	    return plantRepository.save(plant);
	}
}
