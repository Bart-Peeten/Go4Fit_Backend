package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface ReservationService {

    Iterable<Reservation> getAllReservations();
    Reservation getReservationById(long id);
    Reservation addNewReservation(Reservation reservation);
    Iterable<Reservation> getReservationByDate(Date date);
    Iterable<Reservation> getReservationByDateAndTime(Date date, Time time);
    List<String> getNamesFromReservationByDateAndTime(Date date, Time time);
}
