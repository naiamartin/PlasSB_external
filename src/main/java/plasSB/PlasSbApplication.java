package plasSB;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import plasSB.dao.RecyclingPlantRepository;
import plasSB.entity.RecyclingPlant;



@SpringBootApplication
public class PlasSbApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlasSbApplication.class, args);
	}
	
	@Bean
    CommandLineRunner initDatabase(RecyclingPlantRepository repository) {
        return args -> {
            if (repository.findByPlantName("PlasSB") == null) {
                RecyclingPlant plant = new RecyclingPlant();
                plant.setPlantName("PlasSB");  
                plant.setTotalCapacity(10000.0f);  
                plant.setCurrentCapacity(7500.0f);  
                repository.save(plant);
                System.out.println(" PlasSB plant initialized with total capacity: 10000.0");
            } else {
                System.out.println(" PlasSB plant already exists. Current capacity: " 
                    + repository.findByPlantName("PlasSB").getCurrentCapacity());
            }
        };
    }
}
