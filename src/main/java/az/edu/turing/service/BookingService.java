package az.edu.turing.service;

import az.edu.turing.model.dto.BookingDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    List<BookingDto> getAllBookings();

    Optional<BookingDto> findBookingById(Long id);

    BookingDto addBooking(BookingDto booking);

    BookingDto updateBooking(BookingDto booking);

    void deleteBooking(BookingDto booking);

    List<BookingDto> findBookingsByPassenger(String firstName, String lastName);

}
