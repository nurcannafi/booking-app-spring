package az.edu.turing.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDto {

    private Long id;
    private FlightDto flight;
    private LocalDateTime bookingTime;

    @NotNull(message = "Passengers list cannot be null")
    @NotEmpty(message = "Passengers list cannot be empty")
    private List<PassengerDto> passengers;

    public BookingDto(Long id, FlightDto flight, LocalDateTime bookingTime) {
        this.id = id;
        this.flight = flight;
        this.bookingTime = bookingTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFlight(FlightDto flight) {
        this.flight = flight;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setPassengers(List<PassengerDto> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "BookingDto{id=%d, flight=%s, bookingTime=%s, passengers=%s}"
                .formatted(id, flight, bookingTime, passengers);
    }
}
