package plasSB.dao;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import plasSB.entity.RecyclingPlant;



@Repository
public interface RecyclingPlantRepository extends JpaRepository<RecyclingPlant, LocalDate> {
	Optional<RecyclingPlant> findByDate(LocalDate date);

    @Query("SELECT rp FROM RecyclingPlant rp ORDER BY rp.date DESC LIMIT 1")
    Optional<RecyclingPlant> findMostRecent();
}
