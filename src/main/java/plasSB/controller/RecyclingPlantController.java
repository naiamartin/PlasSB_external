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
    
    private final RecyclingPlantService recyclingPlantService; // ← Cambiado el nombre
    
    public RecyclingPlantController(RecyclingPlantService recyclingPlantService) {
        this.recyclingPlantService = recyclingPlantService;
    }
    
    @GetMapping("/capacity")
    public RecyclingPlant getCapacityForDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        return recyclingPlantService.getCapacityForDate(date);
    }
    
    @GetMapping("/capacity/current")
    public RecyclingPlant getCurrentCapacity() {
        return recyclingPlantService.getCurrentCapacity();
    }
    
    @PostMapping("/assignments")
    public String receiveAssignment(@RequestBody AssignmentRequestDTO assignment) {
        float capacityReduction = assignment.getPackageCount();
        recyclingPlantService.reduceCapacity(capacityReduction);
        
        return String.format(
            "Assignment received: %d dumpsters, %d packages assigned by %s",
            assignment.getDumpsterCount(), 
            assignment.getPackageCount(), 
            assignment.getAssignedBy()
        );
    }
    

    
    @GetMapping("/health")
    public String healthCheck() {
        return "PlasSB Server is running!";
    }
}
