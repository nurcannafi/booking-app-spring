package az.edu.turing.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    private LocalDateTime bookingTime;

    @OneToMany(mappedBy = "bookings", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PassengerEntity> passengers;

    public BookingEntity() {
    }

    public BookingEntity(FlightEntity flight, LocalDateTime bookingTime) {
        this.flight = flight;
        this.bookingTime = bookingTime;
    }

}
