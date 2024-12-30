package az.edu.turing.controller;

import az.edu.turing.exception.EntityNotFoundException;
import az.edu.turing.model.dto.FlightDto;
import az.edu.turing.service.FlightService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<FlightDto> flights = flightService.getAllFlights();
        if (flights.isEmpty()) {
            throw new EntityNotFoundException("No flights found");
        }
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDto>> searchFlights(@RequestParam String destination,
                                                         @RequestParam String departureTime) throws BadRequestException {
        try {
            List<FlightDto> flights = flightService.searchFlights(destination, LocalDateTime.parse(departureTime));
            if (flights.isEmpty()) {
                throw new EntityNotFoundException("No flights found for the given date");
            }
            return ResponseEntity.ok(flights);
        } catch (Exception e) {
            throw new BadRequestException("Invalid departure time format");
        }

    }

    @PostMapping
    public ResponseEntity<FlightDto> addFlight(@RequestBody FlightDto flightDto) throws BadRequestException {
        if (flightDto.getAvailableSeats() < 0) {
            throw new BadRequestException("Available seats cannot be negative");
        }
        FlightDto newFlightDto = flightService.addFlight(flightDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFlightDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightDto> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Flight not found");
        }
        return ResponseEntity.noContent().build();
    }
}
