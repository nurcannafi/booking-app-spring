package az.edu.turing.mapper;

import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.model.dto.PassengerDto;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper implements EntityMapper<PassengerEntity, PassengerDto> {

    @Override
    public PassengerEntity toEntity(PassengerDto passengerDto) {
        return new PassengerEntity(
                passengerDto.getFirstName(),
                passengerDto.getLastName(),
                passengerDto.getAge()
        );
    }

    @Override
    public PassengerDto toDto(PassengerEntity passengerEntity) {
        return new PassengerDto(
                passengerEntity.getFirstName(),
                passengerEntity.getLastName(),
                passengerEntity.getAge()
        );
    }
}
