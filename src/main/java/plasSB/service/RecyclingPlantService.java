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

	public RecyclingPlant updateCapacity(String plant_name, float amount) {
		RecyclingPlant rp = plantRepository.findByPlantName(plant_name);
        if (rp == null) {
            throw new RuntimeException("Plant not found: " + plant_name);
        }
        
        float newCapacity = rp.getCurrentCapacity() - amount;
        if (newCapacity < 0) {
            throw new IllegalArgumentException("Insufficient capacity. Available: " 
                + rp.getCurrentCapacity() + ", Required: " + amount);
        }
        
        rp.setCurrentCapacity(newCapacity);
        return plantRepository.save(rp);
	}

	public float getCapacity(String plant_name) {
		RecyclingPlant rp = plantRepository.findByPlantName(plant_name);
	    if (rp == null) {
	        throw new RuntimeException("Plant not found: " + plant_name);
	    }
	    return rp.getCurrentCapacity();
	}
	    


}
