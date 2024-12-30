package az.edu.turing.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "flights")
public class FlightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private LocalDateTime departureTime;
    private int availableSeats;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingEntity> bookings;

    public FlightEntity() {

    }

    public FlightEntity(String destination, LocalDateTime departureTime, int availableSeats) {
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

}
