package plasSB.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import plasSB.entity.RecyclingPlant;

@Repository
public interface RecyclingPlantRepository extends JpaRepository<RecyclingPlant, String> {
	RecyclingPlant findByPlantName(String plantName);
}
