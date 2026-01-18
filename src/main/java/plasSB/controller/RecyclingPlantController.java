package plasSB.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import plasSB.dto.NotificationDTO;
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
    public float getCurrentCapacity(@RequestParam("plant_name") String plant_name) {
        return recyclingPlantService.getCapacity(plant_name);
    }

    
    // POST allocation notification
    @PostMapping("/allocation")
    public ResponseEntity<?> recieveAllocation(
    		@RequestParam("plant_name") String plant_name,
            @RequestBody NotificationDTO notification) {
        try {
            recyclingPlantService.receiveNotification(
                plant_name,
                notification.getDumpsters(),
                notification.getPackages(),
                notification.getTons()
            );
            System.out.println("Notification received at PlasSB for plant: " + plant_name +
				"Dumpsters: " + notification.getDumpsters() + ", " +
				"Packages: " + notification.getPackages() + ", " +
				"Tons: " + notification.getTons());
            System.out.println("Updated capacity: " + recyclingPlantService.getCapacity(plant_name));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
    @GetMapping("/health")
    public String healthCheck() {
        return "PlasSB Server is running!";
    }
}