package plasSB.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import plasSB.dto.AssignmentRequestDTO;
import plasSB.entity.RecyclingPlant;
import plasSB.service.RecyclingPlantService;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/plants")
public class RecyclingPlantController {
    
    private final RecyclingPlantService recyclingPlantService;
    
    public RecyclingPlantController(RecyclingPlantService recyclingPlantService) {
        this.recyclingPlantService = recyclingPlantService;
    }
    
    // Obtener capacidad para fecha específica
    @GetMapping("/capacity")
    public RecyclingPlant getCapacityForDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        return recyclingPlantService.getCapacityForDate(date);
    }
    
    // Obtener capacidad actual (hoy)
    @GetMapping("/capacity/current")
    public RecyclingPlant getCurrentCapacity() {
        return recyclingPlantService.getCapacityForDate(LocalDate.now());
    }
    
    // Recibir asignación - AÑADIR capacidad
    @PostMapping("/assignments")
    public String receiveAssignment(@RequestBody AssignmentRequestDTO assignment) {
        float capacityToAdd = assignment.getPackageCount() * 0.1f; // Calcular peso
        RecyclingPlant updatedPlant = recyclingPlantService.addCapacity(capacityToAdd);
        
        return String.format(
            "Assignment received: %d dumpsters, %d packages (%.2f tons) assigned by %s. New capacity: %.2f/%.2f tons",
            assignment.getDumpsterCount(), 
            assignment.getPackageCount(),
            capacityToAdd,
            assignment.getAssignedBy(),
            updatedPlant.getCurrent_capacity(),
            updatedPlant.getTotal_capacity()
        );
    }
    
    // Endpoint para RESTAR capacidad (cuando se procesa material)
    @PostMapping("/process")
    public String processMaterial(@RequestParam float amount) {
        RecyclingPlant updatedPlant = recyclingPlantService.subtractCapacity(amount);
        
        return String.format(
            "Processed %.2f tons. New capacity: %.2f/%.2f tons",
            amount,
            updatedPlant.getCurrent_capacity(),
            updatedPlant.getTotal_capacity()
        );
    }
    
    // Endpoint para ACTUALIZAR capacidad manualmente
    @PutMapping("/capacity")
    public RecyclingPlant updateCapacity(@RequestParam float newCapacity) {
        RecyclingPlant current = recyclingPlantService.getCapacityForDate(LocalDate.now());
        current.setCurrent_capacity(newCapacity);
        return recyclingPlantService.updateCapacity(current); 
    }
    
    @GetMapping("/health")
    public String healthCheck() {
        return "PlasSB Server is running!";
    }
}
