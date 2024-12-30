package az.edu.turing.controller;

import az.edu.turing.exception.EntityNotFoundException;
import az.edu.turing.model.dto.BookingDto;
import az.edu.turing.service.BookingService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<BookingDto> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            throw new EntityNotFoundException("Booking not found");
        }
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable("id") Long id) {
        return bookingService.findBookingById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found by id: " + id));
    }

    @PostMapping
    public ResponseEntity<BookingDto> addBooking(@RequestBody BookingDto bookingDto) throws BadRequestException {
        if (bookingDto.getPassengers() == null || bookingDto.getPassengers().isEmpty()) {
            throw new BadRequestException("Booking must have at least one passenger");
        }
        BookingDto savedBooking = bookingService.addBooking(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
    }

    @PutMapping
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto bookingDto) throws BadRequestException {
        if (bookingDto.getPassengers() == null || bookingDto.getPassengers().isEmpty()) {
            throw new BadRequestException("Booking must have at least one passenger");
        }
        if (bookingDto.getId() == null) {
            throw new BadRequestException("Booking must have an id");
        }
        bookingService.findBookingById(bookingDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Booking not found by id: " + bookingDto.getId()));
        BookingDto updatedBooking = bookingService.updateBooking(bookingDto);
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(BookingDto bookingDto) {
        bookingService.findBookingById(bookingDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Booking not found"));
        bookingService.deleteBooking(bookingDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookingDto>> findBookingsByPassengers(@RequestParam String firstName,
                                                                     @RequestParam String lastName) {
        List<BookingDto> bookings = bookingService.findBookingsByPassenger(firstName, lastName);
        if (bookings.isEmpty()) {
            throw new EntityNotFoundException("Booking not found");
        }
        return ResponseEntity.ok(bookings);
    }
}
