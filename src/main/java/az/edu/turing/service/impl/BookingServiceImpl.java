package az.edu.turing.service.impl;

import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.domain.repository.BookingRepository;
import az.edu.turing.mapper.BookingMapper;
import az.edu.turing.model.dto.BookingDto;
import org.springframework.stereotype.Service;
import az.edu.turing.service.BookingService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<BookingEntity> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookingDto> findBookingById(Long id) {
        Optional<BookingEntity> booking = bookingRepository.findById(id);
        return booking.map(bookingMapper::toDto);
    }

    @Override
    public BookingDto addBooking(BookingDto booking) {
        BookingEntity bookingEntity = bookingMapper.toEntity(booking);
        BookingEntity savedBooking = bookingRepository.save(bookingEntity);
        return bookingMapper.toDto(savedBooking);
    }

    @Override
    public BookingDto updateBooking(BookingDto booking) {
        BookingEntity bookingEntity = bookingMapper.toEntity(booking);
        BookingEntity updatedBooking = bookingRepository.save(bookingEntity);
        return bookingMapper.toDto(updatedBooking);
    }

    @Override
    public void deleteBooking(BookingDto booking) {
        BookingEntity bookingEntity = bookingMapper.toEntity(booking);
        bookingRepository.delete(bookingEntity);
    }

    @Override
    public List<BookingDto> findBookingsByPassenger(String firstName, String lastName) {
        List<BookingEntity> bookings = bookingRepository.findByPassengerName(firstName, lastName);
        return bookings.stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }
}
