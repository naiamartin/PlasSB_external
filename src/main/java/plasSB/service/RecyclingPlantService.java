package plasSB.service;

import org.springframework.stereotype.Service;


import plasSB.entity.RecyclingPlant;
import plasSB.dao.RecyclingPlantRepository;
import java.util.List;

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
	
	//Gets allocated dumpsters, packages and tons(NotificationDTO) for a plant from Ecoembes and updates capacity
	public void receiveNotification(String plant_name, int dumpsters, int packages, float tons) {
		RecyclingPlant rp = plantRepository.findByPlantName(plant_name);
		if (rp == null) {
			throw new RuntimeException("Plant not found: " + plant_name);
		}
		
		rp.setAllocatedDumpsters(rp.getAllocatedDumpsters() + dumpsters);
		rp.setAllocatedPackages(rp.getAllocatedPackages() + packages);
		rp.setAllocatedTons(rp.getAllocatedTons() + tons);
		
		float newCapacity = rp.getCurrentCapacity() - tons;
		if (newCapacity < 0) {
			throw new IllegalArgumentException("Insufficient capacity. Available: " 
				+ rp.getCurrentCapacity() + ", Required: " + tons);
		}
		
		rp.setCurrentCapacity(newCapacity);
		System.out.println("Updated capacity for plant " + plant_name + ": " + newCapacity);
		plantRepository.save(rp);
	}
	
	// Return all plants
	public List<RecyclingPlant> getAllPlants() {
		return plantRepository.findAll();
	}
    

}