package plasSB.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "recycling_plant_capacity")
public class RecyclingPlant {
    
    @Id
    @Column(name = "date", nullable = false)
    private LocalDate date;  
    
    @Column(name = "plant_name", nullable = false)
    private String plant_name = "PlasSB Ltd.";  
    
    @Column(name = "total_capacity", nullable = false)
    private float total_capacity = 1500.0f;  
    
    @Column(name = "current_capacity", nullable = false)
    private float current_capacity;  
    
    public RecyclingPlant() {}
    
    public RecyclingPlant(LocalDate date, float current_capacity) {
        this.date = date;
        this.current_capacity = current_capacity;
    }
    
    public RecyclingPlant(LocalDate date, float current_capacity, float total_capacity) {
        this.date = date;
        this.current_capacity = current_capacity;
        this.total_capacity = total_capacity;
    }
    
    // Getters y Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getPlant_name() { return plant_name; }
    public void setPlant_name(String plant_name) { this.plant_name = plant_name; }
    
    public float getTotal_capacity() { return total_capacity; }
    public void setTotal_capacity(float total_capacity) { this.total_capacity = total_capacity; }
    
    public float getCurrent_capacity() { return current_capacity; }
    public void setCurrent_capacity(float current_capacity) { this.current_capacity = current_capacity; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecyclingPlant)) return false;
        RecyclingPlant that = (RecyclingPlant) o;
        return Objects.equals(date, that.date);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
    
    @Override
    public String toString() {
        return String.format("RecyclingPlant{date=%s, name='%s', current=%.2f, total=%.2f}", 
            date, plant_name, current_capacity, total_capacity);
    }
}