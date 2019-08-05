package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByDateAndTime(LocalDate date, LocalTime time);
    Long getNumberOfReservations(LocalDate date, LocalTime time);
    List<Reservation> getReservationById(long id);
    List<Reservation> getReservationByDate(LocalDate date);
    List<String> getNamesFromReservationByDateAndTime(LocalDate date, LocalTime time);
    Reservation addNewReservation(Reservation reservation);
}
