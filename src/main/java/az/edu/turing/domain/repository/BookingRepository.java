package az.edu.turing.domain.repository;

import az.edu.turing.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query("SELECT b FROM BookingEntity b JOIN b.passengers p WHERE p.firstName = :firstName AND p.lastName = :lastName")
    List<BookingEntity> findByPassengerName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    List<BookingEntity> findByFlightId(Long flightId);

    List<BookingEntity> findByBookingTimeBetween(LocalDateTime start, LocalDateTime end);
}
