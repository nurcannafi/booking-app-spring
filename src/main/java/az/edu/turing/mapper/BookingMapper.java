package az.edu.turing.mapper;

import az.edu.turing.domain.entity.BookingEntity;
import az.edu.turing.model.dto.BookingDto;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper implements EntityMapper<BookingEntity, BookingDto> {

    private final FlightMapper flightMapper = new FlightMapper();

    @Override
    public BookingEntity toEntity(BookingDto bookingDto) {
        return new BookingEntity(
                flightMapper.toEntity(bookingDto.getFlight()),
                bookingDto.getBookingTime()
        );
    }

    @Override
    public BookingDto toDto(BookingEntity bookingEntity) {
        return new BookingDto(
                bookingEntity.getId(),
                flightMapper.toDto(bookingEntity.getFlight()),
                bookingEntity.getBookingTime()
        );
    }
}
