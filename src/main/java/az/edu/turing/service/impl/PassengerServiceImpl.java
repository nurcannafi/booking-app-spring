package az.edu.turing.service.impl;

import az.edu.turing.domain.entity.PassengerEntity;
import az.edu.turing.domain.repository.PassengerRepository;
import az.edu.turing.mapper.PassengerMapper;
import az.edu.turing.model.dto.PassengerDto;
import org.springframework.stereotype.Service;
import az.edu.turing.service.PassengerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public List<PassengerDto> findAllPassengers() {
        List<PassengerEntity> passengers = passengerRepository.findAll();
        return passengers.stream()
                .map(passengerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PassengerDto> findPassengerById(Long id) {
        Optional<PassengerEntity> passenger = passengerRepository.findById(id);
        return passenger.map(passengerMapper::toDto);
    }

    @Override
    public PassengerDto addPassenger(PassengerDto passenger) {
        PassengerEntity passengerEntity = passengerMapper.toEntity(passenger);
        PassengerEntity savedPassengerEntity = passengerRepository.save(passengerEntity);
        return passengerMapper.toDto(savedPassengerEntity);
    }

    @Override
    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}
