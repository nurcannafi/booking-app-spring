package az.edu.turing.domain.repository;

import az.edu.turing.domain.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

    List<FlightEntity> findByDestination(String destination);

    List<FlightEntity> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);

    List<FlightEntity> findByDestinationAndDepartureTime(String destination, LocalDateTime departureTime);

    @Query("SELECT f FROM FlightEntity f WHERE f.availableSeats >= :seats")
    List<FlightEntity> findAvailableSeats(@Param("seats") int seats);

}
