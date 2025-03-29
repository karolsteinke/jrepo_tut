package sk.bookings.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sk.bookings.models.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking,Long> {
    //Booking = entity type; Long = type of the id
    //implementation not required = Spring auto-completes it
    //H2 database will start automatically
}
