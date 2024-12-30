package az.edu.turing.model.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FlightDto {

    private Long id;
    private String destination;
    private LocalDateTime departureTime;
    private int availableSeats;

    public FlightDto(Long id, String destination, LocalDateTime departureTime, int availableSeats) {
        this.id = id;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "FlightDto{id=%d, destination='%s', departureTime=%s, availableSeats=%d}"
                .formatted(id, destination, departureTime, availableSeats);
    }
}
