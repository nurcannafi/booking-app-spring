package az.edu.turing.service;

import az.edu.turing.model.dto.FlightDto;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    List<FlightDto> getAllFlights();

    List<FlightDto> searchFlights(String destination, LocalDateTime departureTime);

    FlightDto addFlight(FlightDto flight);

    void deleteFlight(Long id);
}
