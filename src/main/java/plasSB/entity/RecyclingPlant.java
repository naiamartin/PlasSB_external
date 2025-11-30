package plasSB.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recycling_plant")
public class RecyclingPlant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plant_id;
    
    @Column(nullable = false, unique = true)
    private String name = "PlasSB Ltd.";  
    
    @Column(nullable = false)
    private float total_capacity = 1500.0f;  
    
    @Column(nullable = false)
    private float current_capacity; 
    
    public RecyclingPlant() {}
    
    public RecyclingPlant(float current_capacity) {
        this.current_capacity = current_capacity;
    }
    
  
    public Long getPlant_id() { return plant_id; }
    public String getName() { return name; }
    public float getTotal_capacity() { return total_capacity; }
    public float getCurrent_capacity() { return current_capacity; }
    public void setCurrent_capacity(float current_capacity) { 
        this.current_capacity = current_capacity; 
    }
}
