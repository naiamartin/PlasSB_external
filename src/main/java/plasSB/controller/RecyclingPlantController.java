package plasSB.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import plasSB.entity.RecyclingPlant;
import plasSB.service.RecyclingPlantService;


@RestController
@RequestMapping("/api/plants")
public class RecyclingPlantController {
    
    private final RecyclingPlantService recyclingPlantService;
    
    public RecyclingPlantController(RecyclingPlantService recyclingPlantService) {
        this.recyclingPlantService = recyclingPlantService;
    }
    
    
    // GET capacidad actual
    @GetMapping("/capacity/current")
    public float getCurrentCapacity(@RequestParam String plant_name) {
        return recyclingPlantService.getCapacity(plant_name);
    }
    
    //PUT capacidad nueva
    @PutMapping("/capacity/current")
    public ResponseEntity<?> updateCapacity(
            @RequestParam String plant_name,
            @RequestParam float amount) {
        try {
            RecyclingPlant updated = recyclingPlantService.updateCapacity(plant_name, amount);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/health")
    public String healthCheck() {
        return "PlasSB Server is running!";
    }
}
