package az.edu.turing.service.impl;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.domain.repository.FlightRepository;
import az.edu.turing.mapper.FlightMapper;
import az.edu.turing.model.dto.FlightDto;
import org.springframework.stereotype.Service;
import az.edu.turing.service.FlightService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }


    @Override
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightDto> searchFlights(String destination, LocalDateTime departureTime) {
        List<FlightEntity> flights = flightRepository.findByDestinationAndDepartureTime(destination, departureTime);
        return flights.stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto addFlight(FlightDto flight) {
        FlightEntity flightEntity = flightMapper.toEntity(flight);
        FlightEntity savedFlight = flightRepository.save(flightEntity);
        return flightMapper.toDto(savedFlight);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
