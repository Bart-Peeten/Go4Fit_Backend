package be.appelicious.interfaces;

import be.appelicious.domain.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();
}
