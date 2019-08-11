package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Bart Peeten
 * */

public interface ReservationService {

    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByDateAndTime(LocalDate date, LocalTime time);
    Long getNumberOfReservations(LocalDate date, LocalTime time);
    List<Reservation> getReservationById(long id);
    List<Reservation> getReservationByDate(LocalDate date);
    List<String> getNamesFromReservationByDateAndTime(LocalDate date, LocalTime time);
    List<List<String>> getReservationNamesForGivenWeek(List<LocalDate> dates);
    Reservation addNewReservation(Reservation reservation);
    Reservation removeUserFromReservation(String firstname, String lastname, LocalDate date, LocalTime time);
    List<Integer> getReservationNumbersForGivenWeek(List<LocalDate> datesOfWeek);
    Reservation addNewReservationWithOnlyFullName(String firstname, String lastname, LocalDate date, LocalTime time);
    List<Boolean> getIsParticipantReserved(String firstname, String lastname, List<LocalDate> datesOfWeek);
}
