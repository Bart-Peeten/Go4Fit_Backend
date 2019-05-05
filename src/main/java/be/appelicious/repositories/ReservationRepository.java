package be.appelicious.repositories;

import be.appelicious.domain.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Iterable<Reservation> findReservationsByDate(Date date);
    Iterable<Reservation> findReservationsByDateAndTime(Date date, Time time);
}
