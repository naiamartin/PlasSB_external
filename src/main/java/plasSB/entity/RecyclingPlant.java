package plasSB.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "recycling_plant_capacity")
public class RecyclingPlant {
    
    @Id 
    
    @Column(name = "plant_name", nullable = false)
    private String plantName;  
    
    @Column(name = "total_capacity", nullable = false)
    private float totalCapacity;  
    
    @Column(name = "current_capacity", nullable = false)
    private float currentCapacity; 
    
    @Column(name = "allocated_dumpsters", nullable = false)
    private int allocatedDumpsters;
    
    @Column(name = "allocated_packages", nullable = false)
    private int allocatedPackages;
    
    
    public RecyclingPlant() {}
    
    public RecyclingPlant(String plantName, float totalCapacity, float currentCapacity) {
		super();
		this.plantName = plantName;
		this.totalCapacity = totalCapacity;
		this.currentCapacity = currentCapacity;
	}
    
    

	public RecyclingPlant(String plantName, float totalCapacity, float currentCapacity, int allocatedDumpsters,
			int allocatedPackages) {
		super();
		this.plantName = plantName;
		this.totalCapacity = totalCapacity;
		this.currentCapacity = currentCapacity;
		this.allocatedDumpsters = allocatedDumpsters;
		this.allocatedPackages = allocatedPackages;
	}

	// Getters y Setters

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public float getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(float totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public float getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(float currentCapacity) {
		this.currentCapacity = currentCapacity;
	}
	
	

	public int getAllocatedDumpsters() {
		return allocatedDumpsters;
	}

	public void setAllocatedDumpsters(int allocatedDumpsters) {
		this.allocatedDumpsters = allocatedDumpsters;
	}

	public int getAllocatedPackages() {
		return allocatedPackages;
	}

	public void setAllocatedPackages(int allocatedPackages) {
		this.allocatedPackages = allocatedPackages;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allocatedDumpsters, allocatedPackages, currentCapacity, plantName,
				totalCapacity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecyclingPlant other = (RecyclingPlant) obj;
		return allocatedDumpsters == other.allocatedDumpsters && allocatedPackages == other.allocatedPackages
				&& Float.floatToIntBits(currentCapacity) == Float.floatToIntBits(other.currentCapacity)
				&& Objects.equals(plantName, other.plantName)
				&& Float.floatToIntBits(totalCapacity) == Float.floatToIntBits(other.totalCapacity);
	}

	
    
    
}