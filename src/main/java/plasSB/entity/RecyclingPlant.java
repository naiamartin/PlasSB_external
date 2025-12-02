package plasSB.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
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
    
    public RecyclingPlant() {}
    
    public RecyclingPlant(String plantName, float totalCapacity, float currentCapacity) {
		super();
		this.plantName = plantName;
		this.totalCapacity = totalCapacity;
		this.currentCapacity = currentCapacity;
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

	@Override
	public int hashCode() {
		return Objects.hash(currentCapacity, plantName, totalCapacity);
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
		return Float.floatToIntBits(currentCapacity) == Float.floatToIntBits(other.currentCapacity)
				&& Objects.equals(plantName, other.plantName)
				&& Float.floatToIntBits(totalCapacity) == Float.floatToIntBits(other.totalCapacity);
	}

	
    
    
}