package sk.bookings.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import sk.bookings.models.Apartment;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment,Long> {
    //Apartment = entity type; Long = type of the id
    //implementation not required = Spring auto-completes it
    //H2 database will start automatically

    //JPQL query
    @Query("select a from Apartment a " +
        "where a.capacity >= :numGuests and " +
        "not exists (select b from a.bookings b " +
        "where :startDay < b.toDate and :endDay > b.fromDate) " +
        "order by a.capacity")
    List<Apartment> getFreeApartments(
        @Param("numGuests") Integer numGuests,
        @Param("startDay") LocalDate startDay,
        @Param("endDay") LocalDate endDay);
}
