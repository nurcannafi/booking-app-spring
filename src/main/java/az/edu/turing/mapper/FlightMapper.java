package az.edu.turing.mapper;

import az.edu.turing.domain.entity.FlightEntity;
import az.edu.turing.model.dto.FlightDto;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper implements EntityMapper<FlightEntity, FlightDto> {

    @Override
    public FlightEntity toEntity(FlightDto flightDto) {
        return new FlightEntity(
                flightDto.getDestination(),
                flightDto.getDepartureTime(),
                flightDto.getAvailableSeats()
        );
    }

    @Override
    public FlightDto toDto(FlightEntity flightEntity) {
        return new FlightDto(
                flightEntity.getId(),
                flightEntity.getDestination(),
                flightEntity.getDepartureTime(),
                flightEntity.getAvailableSeats()
        );
    }
}
