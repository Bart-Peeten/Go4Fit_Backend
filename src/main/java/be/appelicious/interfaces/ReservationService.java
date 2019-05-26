package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByDateAndTime(LocalDate date, LocalTime time);
    long getNumberOfReservations(LocalDate date, LocalTime time);
    List<Reservation> getReservationById(long id);
    List<Reservation> getReservationByDate(LocalDate date);
    List<String> getNamesFromReservationByDateAndTime(LocalDate date, LocalTime time);
    Reservation addNewReservation(Reservation reservation);
}
