package az.edu.turing.service;

import az.edu.turing.model.dto.PassengerDto;

import java.util.List;
import java.util.Optional;

public interface PassengerService {

    List<PassengerDto> findAllPassengers();

    Optional<PassengerDto> findPassengerById(Long id);

    PassengerDto addPassenger(PassengerDto passenger);

    void deletePassenger(Long id);
}
